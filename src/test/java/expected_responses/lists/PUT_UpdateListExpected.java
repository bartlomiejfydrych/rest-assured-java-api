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
                "pos": 140737488338944,
                "subscribed": true
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

    // --------------
    // NEGATIVE TESTS
    // --------------


}
