package utils;

import org.testng.annotations.DataProvider;

public class DataUtils {
	
	@DataProvider(parallel=!true)
	public String[][] getPatientData(){
		
		String[][] excelPatientData = readPatientCredentials.getExcelData();
		
		return excelPatientData;
		
	}
	
	@DataProvider(parallel=!true)
	public String[][] getDoctorData(){
		
		String[][] excelDoctorData = readDoctorCredentials.getExcelData();
		
		return excelDoctorData;
		
	}

}
