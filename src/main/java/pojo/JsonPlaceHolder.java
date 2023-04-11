package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.RandomDataGenerator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true,setterPrefix = "set")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class JsonPlaceHolder {

    private int id;
    private int userId ;
    private String title ;
    private String body ;
}
