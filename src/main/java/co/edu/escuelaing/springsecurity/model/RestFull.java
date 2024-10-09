package co.edu.escuelaing.springsecurity.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestFull {
    private String name;

    private Long time;

    private boolean isUsed;

    @Override
    public String toString() {
        return "RestFull{" +
                "name='" + name + '\'' +
                ", time=" + time +
                ", isUsed=" + isUsed +
                '}';
    }

}
