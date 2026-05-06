package mytcp;

import java.io.Serializable;

public interface Response extends Serializable {
    Object output();
    long executionTime();
}
