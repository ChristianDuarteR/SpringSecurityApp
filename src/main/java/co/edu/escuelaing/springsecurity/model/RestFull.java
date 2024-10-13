package co.edu.escuelaing.springsecurity.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "restfull")
public class RestFull {
    @Id
    private String id;

    private String name;

    private Long time;

    private boolean isUsed;

    @Override
    public String toString() {
        return "RestFull{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", isUsed=" + isUsed +
                '}';
    }
}
