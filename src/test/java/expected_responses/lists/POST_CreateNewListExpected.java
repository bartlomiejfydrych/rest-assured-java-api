package expected_responses.lists;

public class POST_CreateNewListExpected {

    // ==========================================================================================================
    // BASE EXPECTED RESPONSE
    // ==========================================================================================================

    public static final String BaseExpectedPostNewListResponse = """
            {
                "id": "6911e9efe126090464690389",
                "name": "List - Hane, Connelly and Kassulke | Number: 10683416399700",
                "closed": false,
                "color": null,
                "idBoard": "6911e9ecafaaf88ac20110e3",
                "pos": 140737488404480,
                "type": null,
                "datasource": {
                    "filter": false
                },
                "limits": {
            
                }
            }
            """;

    // ==========================================================================================================
    // POSITIVE TESTS
    // ==========================================================================================================

    public static final String P1ExpectedPostNewListResponse = """
            {
                "id": "6910765aa97d9ce82acadb01",
                "name": "v-Fk;ŃE|aBXń}Ąs#\\\\ś^*APiZGhŹ2YH&rź!V</dC\\\\_IMlQxW3OŁp:RÓjnŚą>{TUcbęzKL)Jw\\\\q'?óN\\"f0=9tmĆeu~o`5ż6[Ę,4łg.@71Sć+Ż %$D]y8(",
                "closed": false,
                "color": null,
                "idBoard": "69107658350dd9087e49b953",
                "pos": 140737488338944,
                "type": null,
                "datasource": {
                    "filter": false
                },
                "limits": {
            
                }
            }
            """;
    public static final String P2ExpectedPostNewListResponse = """
            {
                "id": "6910d15bd730c6cd5839ac12",
                "name": "i",
                "closed": false,
                "color": null,
                "idBoard": "6910d15ad0326151bc318066",
                "pos": 140737488338944,
                "type": null,
                "datasource": {
                    "filter": false
                },
                "limits": {
            
                }
            }
            """;
    public static final String P3ExpectedPostNewListResponse = """
            {
                "id": "6910d7c30275b76b27757fef",
                "name": "List - Reichel-Kozey | Number: 18702478352300",
                "closed": false,
                "color": null,
                "idBoard": "6910d7c1a641860dd36f0171",
                "pos": 140737488322560,
                "type": null,
                "datasource": {
                    "filter": false
                },
                "limits": {
            
                }
            }
            """;
    public static final String P4ExpectedPostNewListResponse2 = """
            {
                "id": "6911e9efa810a289134cc475",
                "name": "List - Yost-Lueilwitz | Number: 10683416165900",
                "closed": false,
                "color": null,
                "idBoard": "6911e9ecafaaf88ac20110e3",
                "pos": 140737488322560,
                "type": null,
                "datasource": {
                    "filter": false
                },
                "limits": {
            
                }
            }
            """;
    public static final String P4ExpectedPostNewListResponse3 = """
            {
                "id": "6911e9efe126090464690389",
                "name": "List - Hane, Connelly and Kassulke | Number: 10683416399700",
                "closed": false,
                "color": null,
                "idBoard": "6911e9ecafaaf88ac20110e3",
                "pos": 140737488404480,
                "type": null,
                "datasource": {
                    "filter": false
                },
                "limits": {
            
                }
            }
            """;
    public static final String P4ExpectedPostNewListResponse4 = """
            {
                "id": "6911e9f03dd88ace85387e0b",
                "name": "List - Hodkiewicz and Sons | Number: 10683416554400",
                "closed": false,
                "color": null,
                "idBoard": "6911e9ecafaaf88ac20110e3",
                "pos": 140737488330752,
                "type": null,
                "datasource": {
                    "filter": false
                },
                "limits": {
            
                }
            }
            """;
    public static final String P5ExpectedPostNewListResponse = """
            {
                "id": "691226fd0e8ceb863ac474df",
                "name": "List - Lehner, Schoen and Cummings | Number: 7229809635700",
                "closed": false,
                "color": null,
                "idBoard": "691226fbd263f8b50b10040b",
                "pos": 140737488338944,
                "type": null,
                "datasource": {
                    "filter": false
                },
                "limits": {
            
                }
            }
            """;
    public static final String P6ExpectedPostNewListResponse = """
            {
                "id": "697e17e35b42445ea9997274",
                "name": "List - Cole Inc | Number: 10413870141900",
                "closed": false,
                "color": null,
                "idBoard": "697e17e1ab308565bc964594",
                "pos": 140737488322560,
                "type": null,
                "datasource": {
                    "filter": false
                },
                "limits": {
            
                }
            }
            """;

    // ==========================================================================================================
    // NEGATIVE TESTS
    // ==========================================================================================================

    // ----
    // name
    // ----

    public static final String expectedPostNewListResponseInvalidName = "invalid value for name";

    // -------
    // idBoard
    // -------

    public static final String expectedPostNewListResponseInvalidIdBoard = "invalid value for idBoard";

    // ------------
    // idListSource
    // ------------

    public static final String expectedPostNewListResponseInvalidIdListSource = """
            {
                "message": "Invalid objectId",
                "error": "ERROR"
            }
            """;

    // ---
    // pos
    // ---

    public static final String expectedPostNewListResponseInvalidPos = """
            {
                "message": "Invalid position.",
                "error": "ERROR"
            }
            """;
}
