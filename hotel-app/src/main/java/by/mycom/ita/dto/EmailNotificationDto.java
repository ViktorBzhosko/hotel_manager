package by.mycom.ita.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailNotificationDto {

    private Long userId;
    private String message;

}
