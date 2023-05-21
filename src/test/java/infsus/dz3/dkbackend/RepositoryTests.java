package infsus.dz3.dkbackend;

import infsus.dz3.dkbackend.model.Medication;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Medication.class)
public class RepositoryTests {
}
