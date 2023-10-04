package project.community.home;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class NewsDto {
    private String title;
    private String url;
}
