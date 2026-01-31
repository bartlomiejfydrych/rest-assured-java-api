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
    public static final String P3ExpectedPostLabelResponse = """
            {
                "id": "697e0526af1c81809e9fd9fb",
                "idBoard": "697e0526af1c81809e9fd9c7",
                "name": "",
                "color": "purple",
                "uses": 0,
                "limits": {
            
                }
            }
            """;
    public static final String P4ExpectedPostLabelResponse = """
            {
                "id": "697e0526af1c81809e9fd9fb",
                "idBoard": "697e0526af1c81809e9fd9c7",
                "name": "",
                "color": "purple",
                "uses": 0,
                "limits": {
            
                }
            }
            """;
    public static final String P5ExpectedPostLabelResponse = """
            {
                "id": "697e0ba5ddc9cc71e60b8919",
                "idBoard": "697e0ba4623b0dc258c9d096",
                "name": "A",
                "color": null,
                "uses": 0,
                "limits": {
            
                }
            }
            """;
    public static final String P6ExpectedPostLabelResponse = """
            {
                "id": "697e0cb3d47bb67363ba51b1",
                "idBoard": "697e0cb20c544abcb92df180",
                "name": "L",
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
    public static final String expectedPostLabelResponseInvalidColor = """
            {
                "message": "invalid value for color",
                "error": "ERROR"
            }
            """;
}
