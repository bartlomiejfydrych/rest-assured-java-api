package endpoints.utils.providers;

import endpoints.utils.NamedEndpoint;

import java.util.stream.Stream;

import static endpoints.labels.DEL_DeleteLabelEndpoint.deleteLabel;
import static endpoints.labels.GET_GetLabelEndpoint.getLabel;
import static endpoints.labels.LabelsBaseEndpoint.ENDPOINT_LABELS;
import static endpoints.labels.POST_CreateLabelEndpoint.createLabel;
import static endpoints.labels.PUT_UpdateLabelEndpoint.updateLabel;
import static enums.query_parameters.labels.LabelBaseQueryParameters.COLOR;
import static enums.query_parameters.labels.LabelBaseQueryParameters.NAME;

public class LabelsEndpointsProvider {

    private static final String DUMMY_ID = "dummyId";
    private static final String ENDPOINT = ENDPOINT_LABELS;

    public static Stream<NamedEndpoint> all() {
        return Stream.of(
                new NamedEndpoint("DELETE " + ENDPOINT + "/{id}", spec -> deleteLabel(DUMMY_ID, spec)),
                new NamedEndpoint("GET " + ENDPOINT + "/{id}", spec -> getLabel(DUMMY_ID, spec)),
                new NamedEndpoint("POST " + ENDPOINT, spec -> createLabel(null, NAME.key(), COLOR.key(), spec)),
                new NamedEndpoint("PUT " + ENDPOINT + "/{id}", spec -> updateLabel(DUMMY_ID, null, spec))
        );
    }
}
