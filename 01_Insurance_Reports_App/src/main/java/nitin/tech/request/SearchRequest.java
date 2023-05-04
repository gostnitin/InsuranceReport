package nitin.tech.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SearchRequest {
	
	private String planName;
	private String planStatus;
	private String gender;
	private String startDate;  //yyy-MM-dd
	private String endDate;

}
