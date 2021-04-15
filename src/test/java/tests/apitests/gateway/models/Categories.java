package tests.apitests.gateway.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Categories {
    List<String> categories;
}
