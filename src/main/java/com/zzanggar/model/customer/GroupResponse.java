package com.zzanggar.model.customer;

import lombok.Data;

import java.util.List;

@Data
public class GroupResponse {
    private List<String> businessCategory;

    private List<String> position;

    private List<String> department;

    private List<String> finalCreator;
}
