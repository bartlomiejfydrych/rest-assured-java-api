package providers;

import com.github.javafaker.Faker;

import java.util.Random;

public final class ProviderRandom {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    private static final boolean SEED_PROVIDED = System.getProperty("test.seed") != null;
    private static final long SEED = Long.getLong("test.seed", System.currentTimeMillis());

    private static final Random RANDOM = new Random(SEED);
    private static final Faker FAKER = new Faker(RANDOM);

    // ==========================================================================================================
    // STATIC INITIALIZATION BLOCK
    // ==========================================================================================================

    static {
        System.out.println("=========================================");
        System.out.println("---------");
        System.out.println("TEST SEED");
        System.out.println("---------");

        if (SEED_PROVIDED) {
            System.out.println("Using PROVIDED test.seed = " + SEED);
        } else {
            System.out.println("Using GENERATED test.seed = " + SEED);
        }

        System.out.println("FOR COPY:");
        System.out.println("mvn test -Dtest.seed=" + SEED);
        System.out.println("=========================================");
        System.out.println();
    }

    // ==========================================================================================================
    // CONSTRUCTOR
    // ==========================================================================================================

    private ProviderRandom() {
    }

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    // ------
    // RANDOM
    // ------

    public static Random random() {
        return RANDOM;
    }

    // -----
    // FAKER
    // -----

    public static Faker faker() {
        return FAKER;
    }

    // --------------
    // SEED IN RAPORT
    // --------------

    public static long seed() {
        return SEED;
    }
}
