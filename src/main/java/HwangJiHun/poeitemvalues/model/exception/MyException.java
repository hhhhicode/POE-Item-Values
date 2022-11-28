package HwangJiHun.poeitemvalues.model.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Data
@RequiredArgsConstructor
public class MyException {

    private Long id;
    private final int projectId;
    private final Timestamp date;
    private final String errorMessage;
}
