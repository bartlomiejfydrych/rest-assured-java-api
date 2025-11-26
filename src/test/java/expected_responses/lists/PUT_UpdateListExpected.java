package expected_responses.lists;

public class PUT_UpdateListExpected {

    // ----------------------
    // BASE EXPECTED RESPONSE
    // ----------------------

    public static final String BaseExpectedPutUpdateListResponse = """
            {
                "id": "6918c0e928328bf5ad423af0",
                "name": "Updated list name",
                "closed": true,
                "color": null,
                "idBoard": "6918c0e725eebf9505f994b3",
                "pos": 140737488338944
            }
            """;

    // --------------
    // POSITIVE TESTS
    // --------------

    public static final String P1ExpectedPutUpdateListResponse = """
            {
                "id": "6918c0e928328bf5ad423af0",
                "name": "ZECŚ]bQźŻ.ĘV-żX8N$rPę7ś^R}v3OĄJ&ą#ił\\\\GYB|()xŁ_%>Ń{dFAyuIagŹńom5=S01U,TzK+k46/sLl<wć?W@~j;óMthDf9eq n'\\\\:H\\"Ć`\\\\[cpÓ2!*",
                "closed": true,
                "color": null,
                "idBoard": "6918c0e725eebf9505f994b3",
                "pos": 140737488338944,
                "subscribed": true
            }
            """;
    public static final String P2ExpectedPutUpdateListResponse = """
            {
                "id": "691db362c5d7fd2fb9c41945",
                "name": "T",
                "closed": false,
                "color": null,
                "idBoard": "691db35fd3304f386d3de1ee",
                "pos": 140737488338944,
                "subscribed": false
            }
            """;
    public static final String P3ExpectedPutUpdateListResponse = """
            {
                "id": "691db99bde7565a4a2c0a593",
                "name": "List - Hauck-Homenick | Number: 14697739891200",
                "closed": false,
                "color": null,
                "idBoard": "691db99a4e5a030526097e99",
                "pos": 140737488322560
            }
            """;
    public static final String P4ExpectedPutUpdateListResponse = """
            {
                "id": "69219dd492700ac6492df0d7",
                "name": "List - Nitzsche-Conn | Number: 4552335270400",
                "closed": false,
                "color": null,
                "idBoard": "69219dd2b9e5614adfdfb4ff",
                "pos": 140737488338944
            }
            """;
    public static final String P6ExpectedPutUpdateListResponse = """
            {
                "id": "69219dd492700ac6492df0d7",
                "name": "List - Nitzsche-Conn | Number: 4552335270400",
                "closed": false,
                "color": null,
                "idBoard": "69219dd2b9e5614adfdfb4ff",
                "pos": 140737488338944
            }
            """;
    public static final String P7ExpectedPutUpdateListResponse = """
            {
                "id": "6927625ef7931f7df1313da3",
                "name": "List - Oberbrunner, VonRueden and Steuber | Number: 15265030532500",
                "closed": false,
                "color": null,
                "idBoard": "6927625cc73d3c914a1a24e8",
                "pos": 140737488338944
            }
            """;

    // --------------
    // NEGATIVE TESTS
    // --------------

    public static final String expectedPutUpdateListResponseInvalidId = "invalid id";
    public static final String expectedPutUpdateListResponseInvalidBoardId = """
            {
                "message": "invalid id",
                "error": "BAD_REQUEST_ERROR"
            }
            """;
    public static final String expectedPutUpdateListResponseBoardNotFound = """
            {
                "message": "Board not found.",
                "error": "BOARD_NOT_FOUND"
            }
            """;
    public static final String expectedPutUpdateListResponseInvalidPosition = """
            {
                "message": "Invalid position.",
                "error": "ERROR"
            }
            """;
}
