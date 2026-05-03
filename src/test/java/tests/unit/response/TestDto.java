package tests.unit.response;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

class TestDto {

    @NotNull
    private String name;

    // Required by Jackson
    public TestDto() {
    }

    TestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class NestedDto {

    @NotNull
    private String value;

    public NestedDto() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

class ParentDto {

    @NotNull
    @Valid
    private NestedDto nested;

    public ParentDto() {
    }

    public NestedDto getNested() {
        return nested;
    }

    public void setNested(NestedDto nested) {
        this.nested = nested;
    }
}

class ValidatedDto {

    @NotNull
    @Size(min = 3)
    private String name;

    @Pattern(regexp = "\\d+")
    private String number;

    public ValidatedDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
