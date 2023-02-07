package ru.job4j.lombok;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

@Builder(builderMethodName = "of")
@ToString

public class Permission {
    @Getter
    private int id;
    private String name;
    @Singular("rules")
    private List<String> rules;
}
