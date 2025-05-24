package utils;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ObjectComparator {

    private ObjectComparator() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    @FunctionalInterface
    public interface Getter<T, R> extends Function<T, R>, Serializable {
    }

    @FunctionalInterface
    public interface GetterChainComponent<T, R> extends Function<T, R>, Serializable {
    }

    // Pomocnicza metoda do podawania getterów bez rzutowania
    public static <T, R> GetterChainComponent<T, R> getter(GetterChainComponent<T, R> g) {
        return g;
    }

    @SafeVarargs
    public static <T> void compareObjectsWithIgnoredFields(
            T actual,
            T expected,
            GetterChain<T>... ignoredGetterChains
    ) {
        List<String> ignoredPaths = Arrays.stream(ignoredGetterChains)
                .map(GetterChain::toFieldPath)
                .toList();

        RecursiveComparisonConfiguration config = new RecursiveComparisonConfiguration();
        config.ignoreFields(ignoredPaths.toArray(new String[0]));

        Assertions.assertThat(actual)
                .usingRecursiveComparison(config)
                .isEqualTo(expected);
    }

    public static <T> GetterChain<T> getters(GetterChainComponent<?, ?>... chain) {
        return new GetterChain<>(chain);
    }

    public static class GetterChain<T> {
        private final List<GetterChainComponent<?, ?>> getters;

        public GetterChain(GetterChainComponent<?, ?>... getters) {
            this.getters = Arrays.asList(getters);
        }

        public String toFieldPath() {
            return getters.stream()
                    .map(ObjectComparator::extractFieldName)
                    .collect(Collectors.joining("."));
        }
    }

    private static String extractFieldName(Function<?, ?> getter) {
        try {
            Method writeReplace = getter.getClass().getDeclaredMethod("writeReplace");
            writeReplace.setAccessible(true);
            SerializedLambda lambda = (SerializedLambda) writeReplace.invoke(getter);
            String methodName = lambda.getImplMethodName();

            if (methodName.startsWith("get")) {
                return decapitalize(methodName.substring(3));
            } else if (methodName.startsWith("is")) {
                return decapitalize(methodName.substring(2));
            } else {
                throw new IllegalStateException("Unsupported getter method: " + methodName);
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not extract field name from lambda", e);
        }
    }

    private static String decapitalize(String name) {
        if (name == null || name.isEmpty()) return name;
        return Character.toLowerCase(name.charAt(0)) + name.substring(1);
    }

    public static <T> void compareObjectsIgnoringId(T actual, T expected) {
        compareObjectsWithIgnoredFields(actual, expected,
                getters(getter(ObjectComparator.<T>castGetter(ObjectComparator::getId)))
        );
    }

    // Pomocnicza metoda do rzutowania referencji do gettera
    @SuppressWarnings("unchecked")
    private static <T> GetterChainComponent<T, ?> castGetter(Function<?, ?> getter) {
        return (GetterChainComponent<T, ?>) getter;
    }

    // Zakładamy, że wszystkie obiekty mają metodę getId()
    private static Object getId(Object o) {
        try {
            Method method = o.getClass().getMethod("getId");
            return method.invoke(o);
        } catch (Exception e) {
            throw new RuntimeException("Could not call getId()", e);
        }
    }

    public static void compareObjects(Object actualObject, Object expectedObject, String... fieldsToIgnore) {
        if (fieldsToIgnore != null && fieldsToIgnore.length > 0) {
            RecursiveComparisonConfiguration config = new RecursiveComparisonConfiguration();
            config.ignoreFields(fieldsToIgnore);

            Assertions.assertThat(actualObject)
                    .usingRecursiveComparison(config)
                    .isEqualTo(expectedObject);
        } else {
            Assertions.assertThat(actualObject)
                    .usingRecursiveComparison()
                    .isEqualTo(expectedObject);
        }
    }
}
    /*

    Example of use:

    compareObjectsWithIgnoredFields(actualResponsePost, expectedResponsePost,
    getters(getter(POST_CreateBoardDto::getId)),
    getters(getter(POST_CreateBoardDto::getPrefs), getter(Prefs::isTemplate)),
    getters(getter(POST_CreateBoardDto::getName)),
    getters(getter(POST_CreateBoardDto::getShortUrl)),
    getters(getter(POST_CreateBoardDto::getUrl))
    );

    */

