package infsus.dz3.dkbackend;

import infsus.dz3.dkbackend.dto.ReportDto;
import infsus.dz3.dkbackend.model.Report;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class DkBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DkBackendApplication.class, args);
	}
	@Configuration
	public class AppConfiguration {
		@Bean
		public ModelMapper modelMapper() {
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//			modelMapper.createTypeMap(ReportDto.class,Report.class)
//					.addMappings(reportDto->reportDto.getDoctor().);
			return modelMapper;
		}
	}
}
