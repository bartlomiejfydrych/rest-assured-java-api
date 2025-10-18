package expected_responses.labels;

public class PUT_UpdateLabelExpected {

    // --------------
    // POSITIVE TESTS
    // --------------

    public static final String P1ExpectedPutLabelResponse = """
            {
                "id": "68f3727ceca87e58e1db7f37",
                "idBoard": "68f3727b519a2a57cdf7fec1",
                "name": "QćJsŃY4źwEv<Wf;DĄ:VL )TF?!0OoŁaNĘ68Ic(iC\\"hx\\\\śn+*u5K7ZŻUH1ÓŚ]Ćq^AlbSr{9\\\\|-',jŹ3ąg$%_>2}dmz@&X~łń#peę=P\\\\RóB[`Gkżty./M",
                "color": "black",
                "uses": 0
            }
            """;
    public static final String P2ExpectedPutLabelResponse = """
            {
                "id": "68f37e9768df3435f3db8e68",
                "idBoard": "68f37e9660e11c1df0d5f798",
                "name": "G",
                "color": "purple",
                "uses": 0
            }
            """;
    public static final String P5ExpectedPutLabelResponse = """
            {
                "id": "68f382a218087e7c6c962fcb",
                "idBoard": "68f382a1ac7a71ed4bd69d4a",
                "name": "",
                "color": null,
                "uses": 0
            }
            """;

    // --------------
    // NEGATIVE TESTS
    // --------------

    public static final String expectedPutLabelResponseInvalidColor = """
            {
                "message": "invalid value for color",
                "error": "ERROR"
            }
            """;
}
