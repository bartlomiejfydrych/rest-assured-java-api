package expected_responses.labels;

public class POST_CreateLabelExpected {

    // --------------
    // POSITIVE TESTS
    // --------------

    public static final String P1ExpectedPostLabelResponse = """
            {
                "id": "68bb100769a92fde56039562",
                "idBoard": "68bb10066ce03d7024e867e1",
                "name": "D=ż1Ńhd6Knq;'ńv40<pć2]łX~/*ŁśĆ+3o^C,&mlAy7\\\\L{są:W|iŚzTjÓxŹUfRk>(H}a- bVIóĄęOu!$wr8N.YEeG\\"?Ż\\\\F[`S_g#%MZ9Pt)\\\\cĘ@5BJźQ",
                "color": "green",
                "uses": 0,
                "limits": {
            
                }
            }
            """;
    public static final String P2ExpectedPostLabelResponse = """
            {
                "id": "68bb2ecf631f201252419d75",
                "idBoard": "68bb2ece4db17d2ecf779986",
                "name": "7",
                "color": null,
                "uses": 0,
                "limits": {
            
                }
            }
            """;

    // --------------
    // NEGATIVE TESTS
    // --------------

    public static final String expectedPostLabelResponseInvalidId = """
            {
                "message": "Invalid id",
                "error": "ERROR"
            }
            """;
}
