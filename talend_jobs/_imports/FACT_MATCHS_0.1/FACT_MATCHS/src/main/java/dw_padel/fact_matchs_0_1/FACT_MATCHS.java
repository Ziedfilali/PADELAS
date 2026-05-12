// ============================================================================
//
// Copyright (c) 2006-2015, Talend SA
//
// Ce code source a été automatiquement généré par_Talend Open Studio for Data Integration
// / Soumis à la Licence Apache, Version 2.0 (la "Licence") ;
// votre utilisation de ce fichier doit respecter les termes de la Licence.
// Vous pouvez obtenir une copie de la Licence sur
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Sauf lorsqu'explicitement prévu par la loi en vigueur ou accepté par écrit, le logiciel
// distribué sous la Licence est distribué "TEL QUEL",
// SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
// Consultez la Licence pour connaître la terminologie spécifique régissant les autorisations et
// les limites prévues par la Licence.


package dw_padel.fact_matchs_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;
 





@SuppressWarnings("unused")

/**
 * Job: FACT_MATCHS Purpose: <br>
 * Description:  <br>
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status 
 */
public class FACT_MATCHS implements TalendJob {

protected static void logIgnoredError(String message, Throwable cause) {
       System.err.println(message);
       if (cause != null) {
               cause.printStackTrace();
       }

}


	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}
	
	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	
	private final static String utf8Charset = "UTF-8";
	//contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String,String> propertyTypes = new java.util.HashMap<>();
		
		public PropertiesWithType(java.util.Properties properties){
			super(properties);
		}
		public PropertiesWithType(){
			super();
		}
		
		public void setContextType(String key, String type) {
			propertyTypes.put(key,type);
		}
	
		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}
	
	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();
	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties){
			super(properties);
		}
		public ContextProperties(){
			super();
		}

		public void synchronizeContext(){
			
		}
		
		//if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if(NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}
	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.
	public ContextProperties getContext() {
		return this.context;
	}
	private final String jobVersion = "0.1";
	private final String jobName = "FACT_MATCHS";
	private final String projectName = "DW_PADEL";
	public Integer errorCode = null;
	private String currentComponent = "";
	
		private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
        private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();
	
		private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
		private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
		private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
		public  final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();
	

private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";
	
	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(), new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}
	
	public void setDataSourceReferences(List serviceReferences) throws Exception{
		
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();
		
		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils.getServices(serviceReferences,  javax.sql.DataSource.class).entrySet()) {
                    dataSources.put(entry.getKey(), entry.getValue());
                    talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}


private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

public String getExceptionStackTrace() {
	if ("failure".equals(this.getStatus())) {
		errorMessagePS.flush();
		return baos.toString();
	}
	return null;
}

private Exception exception;

public Exception getException() {
	if ("failure".equals(this.getStatus())) {
		return this.exception;
	}
	return null;
}

private class TalendException extends Exception {

	private static final long serialVersionUID = 1L;

	private java.util.Map<String, Object> globalMap = null;
	private Exception e = null;
	private String currentComponent = null;
	private String virtualComponentName = null;
	
	public void setVirtualComponentName (String virtualComponentName){
		this.virtualComponentName = virtualComponentName;
	}

	private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
		this.currentComponent= errorComponent;
		this.globalMap = globalMap;
		this.e = e;
	}

	public Exception getException() {
		return this.e;
	}

	public String getCurrentComponent() {
		return this.currentComponent;
	}

	
    public String getExceptionCauseMessage(Exception e){
        Throwable cause = e;
        String message = null;
        int i = 10;
        while (null != cause && 0 < i--) {
            message = cause.getMessage();
            if (null == message) {
                cause = cause.getCause();
            } else {
                break;          
            }
        }
        if (null == message) {
            message = e.getClass().getName();
        }   
        return message;
    }

	@Override
	public void printStackTrace() {
		if (!(e instanceof TalendException || e instanceof TDieException)) {
			if(virtualComponentName!=null && currentComponent.indexOf(virtualComponentName+"_")==0){
				globalMap.put(virtualComponentName+"_ERROR_MESSAGE",getExceptionCauseMessage(e));
			}
			globalMap.put(currentComponent+"_ERROR_MESSAGE",getExceptionCauseMessage(e));
			System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
		}
		if (!(e instanceof TDieException)) {
			if(e instanceof TalendException){
				e.printStackTrace();
			} else {
				e.printStackTrace();
				e.printStackTrace(errorMessagePS);
				FACT_MATCHS.this.exception = e;
			}
		}
		if (!(e instanceof TalendException)) {
		try {
			for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
				if (m.getName().compareTo(currentComponent + "_error") == 0) {
					m.invoke(FACT_MATCHS.this, new Object[] { e , currentComponent, globalMap});
					break;
				}
			}

			if(!(e instanceof TDieException)){
			}
		} catch (Exception e) {
			this.e.printStackTrace();
		}
		}
	}
}

			public void tFileInputExcel_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBOutput_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_4_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_5_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_6_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_7_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tAdvancedHash_row2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tAdvancedHash_row4_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tAdvancedHash_row5_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tAdvancedHash_row6_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tAdvancedHash_row7_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tAdvancedHash_row3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tFileInputExcel_1_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
	






public static class factStruct implements routines.system.IPersistableRow<factStruct> {
    final static byte[] commonByteArrayLock_DW_PADEL_FACT_MATCHS = new byte[0];
    static byte[] commonByteArray_DW_PADEL_FACT_MATCHS = new byte[0];

	
			    public Integer date_key;

				public Integer getDate_key () {
					return this.date_key;
				}
				
			    public Integer tournament_id;

				public Integer getTournament_id () {
					return this.tournament_id;
				}
				
			    public Integer t1_p1_id;

				public Integer getT1_p1_id () {
					return this.t1_p1_id;
				}
				
			    public Integer t1_p2_id;

				public Integer getT1_p2_id () {
					return this.t1_p2_id;
				}
				
			    public Integer t2_p1_id;

				public Integer getT2_p1_id () {
					return this.t2_p1_id;
				}
				
			    public Integer t2_p2_id;

				public Integer getT2_p2_id () {
					return this.t2_p2_id;
				}
				
			    public Integer views;

				public Integer getViews () {
					return this.views;
				}
				
			    public Integer interactions;

				public Integer getInteractions () {
					return this.interactions;
				}
				
			    public Integer winners;

				public Integer getWinners () {
					return this.winners;
				}
				
			    public Integer won_on_1st_serve_t2;

				public Integer getWon_on_1st_serve_t2 () {
					return this.won_on_1st_serve_t2;
				}
				
			    public Integer won_on_2nd_serve_t2;

				public Integer getWon_on_2nd_serve_t2 () {
					return this.won_on_2nd_serve_t2;
				}
				
			    public Integer break_points_saved_t1;

				public Integer getBreak_points_saved_t1 () {
					return this.break_points_saved_t1;
				}
				
			    public String round;

				public String getRound () {
					return this.round;
				}
				
			    public Integer aces_t1;

				public Integer getAces_t1 () {
					return this.aces_t1;
				}
				
			    public Integer aces_t2;

				public Integer getAces_t2 () {
					return this.aces_t2;
				}
				
			    public Integer double_faults_t1;

				public Integer getDouble_faults_t1 () {
					return this.double_faults_t1;
				}
				
			    public Integer double_faults_t2;

				public Integer getDouble_faults_t2 () {
					return this.double_faults_t2;
				}
				
			    public Integer won_on_1st_serve_t1;

				public Integer getWon_on_1st_serve_t1 () {
					return this.won_on_1st_serve_t1;
				}
				
			    public Integer won_on_2nd_serve_t1;

				public Integer getWon_on_2nd_serve_t1 () {
					return this.won_on_2nd_serve_t1;
				}
				
			    public Integer service_games_t1;

				public Integer getService_games_t1 () {
					return this.service_games_t1;
				}
				
			    public Integer service_games_t2;

				public Integer getService_games_t2 () {
					return this.service_games_t2;
				}
				
			    public Integer won_on_1st_return_t1;

				public Integer getWon_on_1st_return_t1 () {
					return this.won_on_1st_return_t1;
				}
				
			    public Integer won_on_1st_return_t2;

				public Integer getWon_on_1st_return_t2 () {
					return this.won_on_1st_return_t2;
				}
				
			    public Integer won_on_2nd_return_t1;

				public Integer getWon_on_2nd_return_t1 () {
					return this.won_on_2nd_return_t1;
				}
				
			    public Integer won_on_2nd_return_t2;

				public Integer getWon_on_2nd_return_t2 () {
					return this.won_on_2nd_return_t2;
				}
				
			    public Integer return_games_t1;

				public Integer getReturn_games_t1 () {
					return this.return_games_t1;
				}
				
			    public Integer return_games_t2;

				public Integer getReturn_games_t2 () {
					return this.return_games_t2;
				}
				
			    public Integer total_won_on_serve_t1;

				public Integer getTotal_won_on_serve_t1 () {
					return this.total_won_on_serve_t1;
				}
				
			    public Integer total_won_on_serve_t2;

				public Integer getTotal_won_on_serve_t2 () {
					return this.total_won_on_serve_t2;
				}
				
			    public Integer total_won_on_return_t1;

				public Integer getTotal_won_on_return_t1 () {
					return this.total_won_on_return_t1;
				}
				
			    public Integer total_won_on_return_t2;

				public Integer getTotal_won_on_return_t2 () {
					return this.total_won_on_return_t2;
				}
				
			    public Integer match_info_added;

				public Integer getMatch_info_added () {
					return this.match_info_added;
				}
				
			    public Integer t1_s1;

				public Integer getT1_s1 () {
					return this.t1_s1;
				}
				
			    public Integer t2_s1;

				public Integer getT2_s1 () {
					return this.t2_s1;
				}
				
			    public Integer t1_s2;

				public Integer getT1_s2 () {
					return this.t1_s2;
				}
				
			    public Integer t2_s2;

				public Integer getT2_s2 () {
					return this.t2_s2;
				}
				
			    public Integer t1_s3;

				public Integer getT1_s3 () {
					return this.t1_s3;
				}
				
			    public Integer t2_s3;

				public Integer getT2_s3 () {
					return this.t2_s3;
				}
				


	private Integer readInteger(ObjectInputStream dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}
	
	private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}

	private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException{
		if(intNum == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeInt(intNum);
    	}
	}
	
	private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(intNum == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeInt(intNum);
    	}
	}

	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_FACT_MATCHS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_MATCHS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_FACT_MATCHS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_MATCHS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length, utf8Charset);
		}
		return strReturn;
	}

    private void writeString(String str, ObjectOutputStream dos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
    }
    
    private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
    }

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_MATCHS) {

        	try {

        		int length = 0;
		
						this.date_key = readInteger(dis);
					
						this.tournament_id = readInteger(dis);
					
						this.t1_p1_id = readInteger(dis);
					
						this.t1_p2_id = readInteger(dis);
					
						this.t2_p1_id = readInteger(dis);
					
						this.t2_p2_id = readInteger(dis);
					
						this.views = readInteger(dis);
					
						this.interactions = readInteger(dis);
					
						this.winners = readInteger(dis);
					
						this.won_on_1st_serve_t2 = readInteger(dis);
					
						this.won_on_2nd_serve_t2 = readInteger(dis);
					
						this.break_points_saved_t1 = readInteger(dis);
					
					this.round = readString(dis);
					
						this.aces_t1 = readInteger(dis);
					
						this.aces_t2 = readInteger(dis);
					
						this.double_faults_t1 = readInteger(dis);
					
						this.double_faults_t2 = readInteger(dis);
					
						this.won_on_1st_serve_t1 = readInteger(dis);
					
						this.won_on_2nd_serve_t1 = readInteger(dis);
					
						this.service_games_t1 = readInteger(dis);
					
						this.service_games_t2 = readInteger(dis);
					
						this.won_on_1st_return_t1 = readInteger(dis);
					
						this.won_on_1st_return_t2 = readInteger(dis);
					
						this.won_on_2nd_return_t1 = readInteger(dis);
					
						this.won_on_2nd_return_t2 = readInteger(dis);
					
						this.return_games_t1 = readInteger(dis);
					
						this.return_games_t2 = readInteger(dis);
					
						this.total_won_on_serve_t1 = readInteger(dis);
					
						this.total_won_on_serve_t2 = readInteger(dis);
					
						this.total_won_on_return_t1 = readInteger(dis);
					
						this.total_won_on_return_t2 = readInteger(dis);
					
						this.match_info_added = readInteger(dis);
					
						this.t1_s1 = readInteger(dis);
					
						this.t2_s1 = readInteger(dis);
					
						this.t1_s2 = readInteger(dis);
					
						this.t2_s2 = readInteger(dis);
					
						this.t1_s3 = readInteger(dis);
					
						this.t2_s3 = readInteger(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_MATCHS) {

        	try {

        		int length = 0;
		
						this.date_key = readInteger(dis);
					
						this.tournament_id = readInteger(dis);
					
						this.t1_p1_id = readInteger(dis);
					
						this.t1_p2_id = readInteger(dis);
					
						this.t2_p1_id = readInteger(dis);
					
						this.t2_p2_id = readInteger(dis);
					
						this.views = readInteger(dis);
					
						this.interactions = readInteger(dis);
					
						this.winners = readInteger(dis);
					
						this.won_on_1st_serve_t2 = readInteger(dis);
					
						this.won_on_2nd_serve_t2 = readInteger(dis);
					
						this.break_points_saved_t1 = readInteger(dis);
					
					this.round = readString(dis);
					
						this.aces_t1 = readInteger(dis);
					
						this.aces_t2 = readInteger(dis);
					
						this.double_faults_t1 = readInteger(dis);
					
						this.double_faults_t2 = readInteger(dis);
					
						this.won_on_1st_serve_t1 = readInteger(dis);
					
						this.won_on_2nd_serve_t1 = readInteger(dis);
					
						this.service_games_t1 = readInteger(dis);
					
						this.service_games_t2 = readInteger(dis);
					
						this.won_on_1st_return_t1 = readInteger(dis);
					
						this.won_on_1st_return_t2 = readInteger(dis);
					
						this.won_on_2nd_return_t1 = readInteger(dis);
					
						this.won_on_2nd_return_t2 = readInteger(dis);
					
						this.return_games_t1 = readInteger(dis);
					
						this.return_games_t2 = readInteger(dis);
					
						this.total_won_on_serve_t1 = readInteger(dis);
					
						this.total_won_on_serve_t2 = readInteger(dis);
					
						this.total_won_on_return_t1 = readInteger(dis);
					
						this.total_won_on_return_t2 = readInteger(dis);
					
						this.match_info_added = readInteger(dis);
					
						this.t1_s1 = readInteger(dis);
					
						this.t2_s1 = readInteger(dis);
					
						this.t1_s2 = readInteger(dis);
					
						this.t2_s2 = readInteger(dis);
					
						this.t1_s3 = readInteger(dis);
					
						this.t2_s3 = readInteger(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// Integer
				
						writeInteger(this.date_key,dos);
					
					// Integer
				
						writeInteger(this.tournament_id,dos);
					
					// Integer
				
						writeInteger(this.t1_p1_id,dos);
					
					// Integer
				
						writeInteger(this.t1_p2_id,dos);
					
					// Integer
				
						writeInteger(this.t2_p1_id,dos);
					
					// Integer
				
						writeInteger(this.t2_p2_id,dos);
					
					// Integer
				
						writeInteger(this.views,dos);
					
					// Integer
				
						writeInteger(this.interactions,dos);
					
					// Integer
				
						writeInteger(this.winners,dos);
					
					// Integer
				
						writeInteger(this.won_on_1st_serve_t2,dos);
					
					// Integer
				
						writeInteger(this.won_on_2nd_serve_t2,dos);
					
					// Integer
				
						writeInteger(this.break_points_saved_t1,dos);
					
					// String
				
						writeString(this.round,dos);
					
					// Integer
				
						writeInteger(this.aces_t1,dos);
					
					// Integer
				
						writeInteger(this.aces_t2,dos);
					
					// Integer
				
						writeInteger(this.double_faults_t1,dos);
					
					// Integer
				
						writeInteger(this.double_faults_t2,dos);
					
					// Integer
				
						writeInteger(this.won_on_1st_serve_t1,dos);
					
					// Integer
				
						writeInteger(this.won_on_2nd_serve_t1,dos);
					
					// Integer
				
						writeInteger(this.service_games_t1,dos);
					
					// Integer
				
						writeInteger(this.service_games_t2,dos);
					
					// Integer
				
						writeInteger(this.won_on_1st_return_t1,dos);
					
					// Integer
				
						writeInteger(this.won_on_1st_return_t2,dos);
					
					// Integer
				
						writeInteger(this.won_on_2nd_return_t1,dos);
					
					// Integer
				
						writeInteger(this.won_on_2nd_return_t2,dos);
					
					// Integer
				
						writeInteger(this.return_games_t1,dos);
					
					// Integer
				
						writeInteger(this.return_games_t2,dos);
					
					// Integer
				
						writeInteger(this.total_won_on_serve_t1,dos);
					
					// Integer
				
						writeInteger(this.total_won_on_serve_t2,dos);
					
					// Integer
				
						writeInteger(this.total_won_on_return_t1,dos);
					
					// Integer
				
						writeInteger(this.total_won_on_return_t2,dos);
					
					// Integer
				
						writeInteger(this.match_info_added,dos);
					
					// Integer
				
						writeInteger(this.t1_s1,dos);
					
					// Integer
				
						writeInteger(this.t2_s1,dos);
					
					// Integer
				
						writeInteger(this.t1_s2,dos);
					
					// Integer
				
						writeInteger(this.t2_s2,dos);
					
					// Integer
				
						writeInteger(this.t1_s3,dos);
					
					// Integer
				
						writeInteger(this.t2_s3,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// Integer
				
						writeInteger(this.date_key,dos);
					
					// Integer
				
						writeInteger(this.tournament_id,dos);
					
					// Integer
				
						writeInteger(this.t1_p1_id,dos);
					
					// Integer
				
						writeInteger(this.t1_p2_id,dos);
					
					// Integer
				
						writeInteger(this.t2_p1_id,dos);
					
					// Integer
				
						writeInteger(this.t2_p2_id,dos);
					
					// Integer
				
						writeInteger(this.views,dos);
					
					// Integer
				
						writeInteger(this.interactions,dos);
					
					// Integer
				
						writeInteger(this.winners,dos);
					
					// Integer
				
						writeInteger(this.won_on_1st_serve_t2,dos);
					
					// Integer
				
						writeInteger(this.won_on_2nd_serve_t2,dos);
					
					// Integer
				
						writeInteger(this.break_points_saved_t1,dos);
					
					// String
				
						writeString(this.round,dos);
					
					// Integer
				
						writeInteger(this.aces_t1,dos);
					
					// Integer
				
						writeInteger(this.aces_t2,dos);
					
					// Integer
				
						writeInteger(this.double_faults_t1,dos);
					
					// Integer
				
						writeInteger(this.double_faults_t2,dos);
					
					// Integer
				
						writeInteger(this.won_on_1st_serve_t1,dos);
					
					// Integer
				
						writeInteger(this.won_on_2nd_serve_t1,dos);
					
					// Integer
				
						writeInteger(this.service_games_t1,dos);
					
					// Integer
				
						writeInteger(this.service_games_t2,dos);
					
					// Integer
				
						writeInteger(this.won_on_1st_return_t1,dos);
					
					// Integer
				
						writeInteger(this.won_on_1st_return_t2,dos);
					
					// Integer
				
						writeInteger(this.won_on_2nd_return_t1,dos);
					
					// Integer
				
						writeInteger(this.won_on_2nd_return_t2,dos);
					
					// Integer
				
						writeInteger(this.return_games_t1,dos);
					
					// Integer
				
						writeInteger(this.return_games_t2,dos);
					
					// Integer
				
						writeInteger(this.total_won_on_serve_t1,dos);
					
					// Integer
				
						writeInteger(this.total_won_on_serve_t2,dos);
					
					// Integer
				
						writeInteger(this.total_won_on_return_t1,dos);
					
					// Integer
				
						writeInteger(this.total_won_on_return_t2,dos);
					
					// Integer
				
						writeInteger(this.match_info_added,dos);
					
					// Integer
				
						writeInteger(this.t1_s1,dos);
					
					// Integer
				
						writeInteger(this.t2_s1,dos);
					
					// Integer
				
						writeInteger(this.t1_s2,dos);
					
					// Integer
				
						writeInteger(this.t2_s2,dos);
					
					// Integer
				
						writeInteger(this.t1_s3,dos);
					
					// Integer
				
						writeInteger(this.t2_s3,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("date_key="+String.valueOf(date_key));
		sb.append(",tournament_id="+String.valueOf(tournament_id));
		sb.append(",t1_p1_id="+String.valueOf(t1_p1_id));
		sb.append(",t1_p2_id="+String.valueOf(t1_p2_id));
		sb.append(",t2_p1_id="+String.valueOf(t2_p1_id));
		sb.append(",t2_p2_id="+String.valueOf(t2_p2_id));
		sb.append(",views="+String.valueOf(views));
		sb.append(",interactions="+String.valueOf(interactions));
		sb.append(",winners="+String.valueOf(winners));
		sb.append(",won_on_1st_serve_t2="+String.valueOf(won_on_1st_serve_t2));
		sb.append(",won_on_2nd_serve_t2="+String.valueOf(won_on_2nd_serve_t2));
		sb.append(",break_points_saved_t1="+String.valueOf(break_points_saved_t1));
		sb.append(",round="+round);
		sb.append(",aces_t1="+String.valueOf(aces_t1));
		sb.append(",aces_t2="+String.valueOf(aces_t2));
		sb.append(",double_faults_t1="+String.valueOf(double_faults_t1));
		sb.append(",double_faults_t2="+String.valueOf(double_faults_t2));
		sb.append(",won_on_1st_serve_t1="+String.valueOf(won_on_1st_serve_t1));
		sb.append(",won_on_2nd_serve_t1="+String.valueOf(won_on_2nd_serve_t1));
		sb.append(",service_games_t1="+String.valueOf(service_games_t1));
		sb.append(",service_games_t2="+String.valueOf(service_games_t2));
		sb.append(",won_on_1st_return_t1="+String.valueOf(won_on_1st_return_t1));
		sb.append(",won_on_1st_return_t2="+String.valueOf(won_on_1st_return_t2));
		sb.append(",won_on_2nd_return_t1="+String.valueOf(won_on_2nd_return_t1));
		sb.append(",won_on_2nd_return_t2="+String.valueOf(won_on_2nd_return_t2));
		sb.append(",return_games_t1="+String.valueOf(return_games_t1));
		sb.append(",return_games_t2="+String.valueOf(return_games_t2));
		sb.append(",total_won_on_serve_t1="+String.valueOf(total_won_on_serve_t1));
		sb.append(",total_won_on_serve_t2="+String.valueOf(total_won_on_serve_t2));
		sb.append(",total_won_on_return_t1="+String.valueOf(total_won_on_return_t1));
		sb.append(",total_won_on_return_t2="+String.valueOf(total_won_on_return_t2));
		sb.append(",match_info_added="+String.valueOf(match_info_added));
		sb.append(",t1_s1="+String.valueOf(t1_s1));
		sb.append(",t2_s1="+String.valueOf(t2_s1));
		sb.append(",t1_s2="+String.valueOf(t1_s2));
		sb.append(",t2_s2="+String.valueOf(t2_s2));
		sb.append(",t1_s3="+String.valueOf(t1_s3));
		sb.append(",t2_s3="+String.valueOf(t2_s3));
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(factStruct other) {

		int returnValue = -1;
		
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}

public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_FACT_MATCHS = new byte[0];
    static byte[] commonByteArray_DW_PADEL_FACT_MATCHS = new byte[0];

	
			    public String tournament_name;

				public String getTournament_name () {
					return this.tournament_name;
				}
				
			    public Integer match_id;

				public Integer getMatch_id () {
					return this.match_id;
				}
				
			    public String round;

				public String getRound () {
					return this.round;
				}
				
			    public String winner;

				public String getWinner () {
					return this.winner;
				}
				
			    public String total_points_won_t1;

				public String getTotal_points_won_t1 () {
					return this.total_points_won_t1;
				}
				
			    public String total_points_won_t2;

				public String getTotal_points_won_t2 () {
					return this.total_points_won_t2;
				}
				
			    public String break_points_converted_t1;

				public String getBreak_points_converted_t1 () {
					return this.break_points_converted_t1;
				}
				
			    public String break_points_converted_t2;

				public String getBreak_points_converted_t2 () {
					return this.break_points_converted_t2;
				}
				
			    public Integer longest_streak_t1;

				public Integer getLongest_streak_t1 () {
					return this.longest_streak_t1;
				}
				
			    public Integer longest_streak_t2;

				public Integer getLongest_streak_t2 () {
					return this.longest_streak_t2;
				}
				
			    public Integer aces_t1;

				public Integer getAces_t1 () {
					return this.aces_t1;
				}
				
			    public Integer aces_t2;

				public Integer getAces_t2 () {
					return this.aces_t2;
				}
				
			    public Integer double_faults_t1;

				public Integer getDouble_faults_t1 () {
					return this.double_faults_t1;
				}
				
			    public Integer double_faults_t2;

				public Integer getDouble_faults_t2 () {
					return this.double_faults_t2;
				}
				
			    public String won_on_1st_serve_t1;

				public String getWon_on_1st_serve_t1 () {
					return this.won_on_1st_serve_t1;
				}
				
			    public String won_on_1st_serve_t2;

				public String getWon_on_1st_serve_t2 () {
					return this.won_on_1st_serve_t2;
				}
				
			    public String won_on_2nd_serve_t1;

				public String getWon_on_2nd_serve_t1 () {
					return this.won_on_2nd_serve_t1;
				}
				
			    public String won_on_2nd_serve_t2;

				public String getWon_on_2nd_serve_t2 () {
					return this.won_on_2nd_serve_t2;
				}
				
			    public Integer service_games_t1;

				public Integer getService_games_t1 () {
					return this.service_games_t1;
				}
				
			    public Integer service_games_t2;

				public Integer getService_games_t2 () {
					return this.service_games_t2;
				}
				
			    public String won_on_1st_return_t1;

				public String getWon_on_1st_return_t1 () {
					return this.won_on_1st_return_t1;
				}
				
			    public String won_on_1st_return_t2;

				public String getWon_on_1st_return_t2 () {
					return this.won_on_1st_return_t2;
				}
				
			    public String won_on_2nd_return_t1;

				public String getWon_on_2nd_return_t1 () {
					return this.won_on_2nd_return_t1;
				}
				
			    public String won_on_2nd_return_t2;

				public String getWon_on_2nd_return_t2 () {
					return this.won_on_2nd_return_t2;
				}
				
			    public Integer return_games_t1;

				public Integer getReturn_games_t1 () {
					return this.return_games_t1;
				}
				
			    public Integer return_games_t2;

				public Integer getReturn_games_t2 () {
					return this.return_games_t2;
				}
				
			    public String total_won_on_serve_t1;

				public String getTotal_won_on_serve_t1 () {
					return this.total_won_on_serve_t1;
				}
				
			    public String total_won_on_serve_t2;

				public String getTotal_won_on_serve_t2 () {
					return this.total_won_on_serve_t2;
				}
				
			    public String total_won_on_return_t1;

				public String getTotal_won_on_return_t1 () {
					return this.total_won_on_return_t1;
				}
				
			    public String total_won_on_return_t2;

				public String getTotal_won_on_return_t2 () {
					return this.total_won_on_return_t2;
				}
				
			    public String date;

				public String getDate () {
					return this.date;
				}
				
			    public String team1_player1_name;

				public String getTeam1_player1_name () {
					return this.team1_player1_name;
				}
				
			    public String team1_player2_name;

				public String getTeam1_player2_name () {
					return this.team1_player2_name;
				}
				
			    public String team2_player1_name;

				public String getTeam2_player1_name () {
					return this.team2_player1_name;
				}
				
			    public String team2_player2_name;

				public String getTeam2_player2_name () {
					return this.team2_player2_name;
				}
				
			    public Integer match_info_added;

				public Integer getMatch_info_added () {
					return this.match_info_added;
				}
				
			    public Integer t1_s1;

				public Integer getT1_s1 () {
					return this.t1_s1;
				}
				
			    public Integer t2_s1;

				public Integer getT2_s1 () {
					return this.t2_s1;
				}
				
			    public Integer t1_s2;

				public Integer getT1_s2 () {
					return this.t1_s2;
				}
				
			    public Integer t2_s2;

				public Integer getT2_s2 () {
					return this.t2_s2;
				}
				
			    public Integer t1_s3;

				public Integer getT1_s3 () {
					return this.t1_s3;
				}
				
			    public Integer t2_s3;

				public Integer getT2_s3 () {
					return this.t2_s3;
				}
				
			    public Integer views;

				public Integer getViews () {
					return this.views;
				}
				
			    public Integer interactions;

				public Integer getInteractions () {
					return this.interactions;
				}
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_FACT_MATCHS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_MATCHS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_FACT_MATCHS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_MATCHS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length, utf8Charset);
		}
		return strReturn;
	}

    private void writeString(String str, ObjectOutputStream dos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
    }
    
    private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
    }
	private Integer readInteger(ObjectInputStream dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}
	
	private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}

	private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException{
		if(intNum == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeInt(intNum);
    	}
	}
	
	private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(intNum == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeInt(intNum);
    	}
	}

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_MATCHS) {

        	try {

        		int length = 0;
		
					this.tournament_name = readString(dis);
					
						this.match_id = readInteger(dis);
					
					this.round = readString(dis);
					
					this.winner = readString(dis);
					
					this.total_points_won_t1 = readString(dis);
					
					this.total_points_won_t2 = readString(dis);
					
					this.break_points_converted_t1 = readString(dis);
					
					this.break_points_converted_t2 = readString(dis);
					
						this.longest_streak_t1 = readInteger(dis);
					
						this.longest_streak_t2 = readInteger(dis);
					
						this.aces_t1 = readInteger(dis);
					
						this.aces_t2 = readInteger(dis);
					
						this.double_faults_t1 = readInteger(dis);
					
						this.double_faults_t2 = readInteger(dis);
					
					this.won_on_1st_serve_t1 = readString(dis);
					
					this.won_on_1st_serve_t2 = readString(dis);
					
					this.won_on_2nd_serve_t1 = readString(dis);
					
					this.won_on_2nd_serve_t2 = readString(dis);
					
						this.service_games_t1 = readInteger(dis);
					
						this.service_games_t2 = readInteger(dis);
					
					this.won_on_1st_return_t1 = readString(dis);
					
					this.won_on_1st_return_t2 = readString(dis);
					
					this.won_on_2nd_return_t1 = readString(dis);
					
					this.won_on_2nd_return_t2 = readString(dis);
					
						this.return_games_t1 = readInteger(dis);
					
						this.return_games_t2 = readInteger(dis);
					
					this.total_won_on_serve_t1 = readString(dis);
					
					this.total_won_on_serve_t2 = readString(dis);
					
					this.total_won_on_return_t1 = readString(dis);
					
					this.total_won_on_return_t2 = readString(dis);
					
					this.date = readString(dis);
					
					this.team1_player1_name = readString(dis);
					
					this.team1_player2_name = readString(dis);
					
					this.team2_player1_name = readString(dis);
					
					this.team2_player2_name = readString(dis);
					
						this.match_info_added = readInteger(dis);
					
						this.t1_s1 = readInteger(dis);
					
						this.t2_s1 = readInteger(dis);
					
						this.t1_s2 = readInteger(dis);
					
						this.t2_s2 = readInteger(dis);
					
						this.t1_s3 = readInteger(dis);
					
						this.t2_s3 = readInteger(dis);
					
						this.views = readInteger(dis);
					
						this.interactions = readInteger(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_MATCHS) {

        	try {

        		int length = 0;
		
					this.tournament_name = readString(dis);
					
						this.match_id = readInteger(dis);
					
					this.round = readString(dis);
					
					this.winner = readString(dis);
					
					this.total_points_won_t1 = readString(dis);
					
					this.total_points_won_t2 = readString(dis);
					
					this.break_points_converted_t1 = readString(dis);
					
					this.break_points_converted_t2 = readString(dis);
					
						this.longest_streak_t1 = readInteger(dis);
					
						this.longest_streak_t2 = readInteger(dis);
					
						this.aces_t1 = readInteger(dis);
					
						this.aces_t2 = readInteger(dis);
					
						this.double_faults_t1 = readInteger(dis);
					
						this.double_faults_t2 = readInteger(dis);
					
					this.won_on_1st_serve_t1 = readString(dis);
					
					this.won_on_1st_serve_t2 = readString(dis);
					
					this.won_on_2nd_serve_t1 = readString(dis);
					
					this.won_on_2nd_serve_t2 = readString(dis);
					
						this.service_games_t1 = readInteger(dis);
					
						this.service_games_t2 = readInteger(dis);
					
					this.won_on_1st_return_t1 = readString(dis);
					
					this.won_on_1st_return_t2 = readString(dis);
					
					this.won_on_2nd_return_t1 = readString(dis);
					
					this.won_on_2nd_return_t2 = readString(dis);
					
						this.return_games_t1 = readInteger(dis);
					
						this.return_games_t2 = readInteger(dis);
					
					this.total_won_on_serve_t1 = readString(dis);
					
					this.total_won_on_serve_t2 = readString(dis);
					
					this.total_won_on_return_t1 = readString(dis);
					
					this.total_won_on_return_t2 = readString(dis);
					
					this.date = readString(dis);
					
					this.team1_player1_name = readString(dis);
					
					this.team1_player2_name = readString(dis);
					
					this.team2_player1_name = readString(dis);
					
					this.team2_player2_name = readString(dis);
					
						this.match_info_added = readInteger(dis);
					
						this.t1_s1 = readInteger(dis);
					
						this.t2_s1 = readInteger(dis);
					
						this.t1_s2 = readInteger(dis);
					
						this.t2_s2 = readInteger(dis);
					
						this.t1_s3 = readInteger(dis);
					
						this.t2_s3 = readInteger(dis);
					
						this.views = readInteger(dis);
					
						this.interactions = readInteger(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.tournament_name,dos);
					
					// Integer
				
						writeInteger(this.match_id,dos);
					
					// String
				
						writeString(this.round,dos);
					
					// String
				
						writeString(this.winner,dos);
					
					// String
				
						writeString(this.total_points_won_t1,dos);
					
					// String
				
						writeString(this.total_points_won_t2,dos);
					
					// String
				
						writeString(this.break_points_converted_t1,dos);
					
					// String
				
						writeString(this.break_points_converted_t2,dos);
					
					// Integer
				
						writeInteger(this.longest_streak_t1,dos);
					
					// Integer
				
						writeInteger(this.longest_streak_t2,dos);
					
					// Integer
				
						writeInteger(this.aces_t1,dos);
					
					// Integer
				
						writeInteger(this.aces_t2,dos);
					
					// Integer
				
						writeInteger(this.double_faults_t1,dos);
					
					// Integer
				
						writeInteger(this.double_faults_t2,dos);
					
					// String
				
						writeString(this.won_on_1st_serve_t1,dos);
					
					// String
				
						writeString(this.won_on_1st_serve_t2,dos);
					
					// String
				
						writeString(this.won_on_2nd_serve_t1,dos);
					
					// String
				
						writeString(this.won_on_2nd_serve_t2,dos);
					
					// Integer
				
						writeInteger(this.service_games_t1,dos);
					
					// Integer
				
						writeInteger(this.service_games_t2,dos);
					
					// String
				
						writeString(this.won_on_1st_return_t1,dos);
					
					// String
				
						writeString(this.won_on_1st_return_t2,dos);
					
					// String
				
						writeString(this.won_on_2nd_return_t1,dos);
					
					// String
				
						writeString(this.won_on_2nd_return_t2,dos);
					
					// Integer
				
						writeInteger(this.return_games_t1,dos);
					
					// Integer
				
						writeInteger(this.return_games_t2,dos);
					
					// String
				
						writeString(this.total_won_on_serve_t1,dos);
					
					// String
				
						writeString(this.total_won_on_serve_t2,dos);
					
					// String
				
						writeString(this.total_won_on_return_t1,dos);
					
					// String
				
						writeString(this.total_won_on_return_t2,dos);
					
					// String
				
						writeString(this.date,dos);
					
					// String
				
						writeString(this.team1_player1_name,dos);
					
					// String
				
						writeString(this.team1_player2_name,dos);
					
					// String
				
						writeString(this.team2_player1_name,dos);
					
					// String
				
						writeString(this.team2_player2_name,dos);
					
					// Integer
				
						writeInteger(this.match_info_added,dos);
					
					// Integer
				
						writeInteger(this.t1_s1,dos);
					
					// Integer
				
						writeInteger(this.t2_s1,dos);
					
					// Integer
				
						writeInteger(this.t1_s2,dos);
					
					// Integer
				
						writeInteger(this.t2_s2,dos);
					
					// Integer
				
						writeInteger(this.t1_s3,dos);
					
					// Integer
				
						writeInteger(this.t2_s3,dos);
					
					// Integer
				
						writeInteger(this.views,dos);
					
					// Integer
				
						writeInteger(this.interactions,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.tournament_name,dos);
					
					// Integer
				
						writeInteger(this.match_id,dos);
					
					// String
				
						writeString(this.round,dos);
					
					// String
				
						writeString(this.winner,dos);
					
					// String
				
						writeString(this.total_points_won_t1,dos);
					
					// String
				
						writeString(this.total_points_won_t2,dos);
					
					// String
				
						writeString(this.break_points_converted_t1,dos);
					
					// String
				
						writeString(this.break_points_converted_t2,dos);
					
					// Integer
				
						writeInteger(this.longest_streak_t1,dos);
					
					// Integer
				
						writeInteger(this.longest_streak_t2,dos);
					
					// Integer
				
						writeInteger(this.aces_t1,dos);
					
					// Integer
				
						writeInteger(this.aces_t2,dos);
					
					// Integer
				
						writeInteger(this.double_faults_t1,dos);
					
					// Integer
				
						writeInteger(this.double_faults_t2,dos);
					
					// String
				
						writeString(this.won_on_1st_serve_t1,dos);
					
					// String
				
						writeString(this.won_on_1st_serve_t2,dos);
					
					// String
				
						writeString(this.won_on_2nd_serve_t1,dos);
					
					// String
				
						writeString(this.won_on_2nd_serve_t2,dos);
					
					// Integer
				
						writeInteger(this.service_games_t1,dos);
					
					// Integer
				
						writeInteger(this.service_games_t2,dos);
					
					// String
				
						writeString(this.won_on_1st_return_t1,dos);
					
					// String
				
						writeString(this.won_on_1st_return_t2,dos);
					
					// String
				
						writeString(this.won_on_2nd_return_t1,dos);
					
					// String
				
						writeString(this.won_on_2nd_return_t2,dos);
					
					// Integer
				
						writeInteger(this.return_games_t1,dos);
					
					// Integer
				
						writeInteger(this.return_games_t2,dos);
					
					// String
				
						writeString(this.total_won_on_serve_t1,dos);
					
					// String
				
						writeString(this.total_won_on_serve_t2,dos);
					
					// String
				
						writeString(this.total_won_on_return_t1,dos);
					
					// String
				
						writeString(this.total_won_on_return_t2,dos);
					
					// String
				
						writeString(this.date,dos);
					
					// String
				
						writeString(this.team1_player1_name,dos);
					
					// String
				
						writeString(this.team1_player2_name,dos);
					
					// String
				
						writeString(this.team2_player1_name,dos);
					
					// String
				
						writeString(this.team2_player2_name,dos);
					
					// Integer
				
						writeInteger(this.match_info_added,dos);
					
					// Integer
				
						writeInteger(this.t1_s1,dos);
					
					// Integer
				
						writeInteger(this.t2_s1,dos);
					
					// Integer
				
						writeInteger(this.t1_s2,dos);
					
					// Integer
				
						writeInteger(this.t2_s2,dos);
					
					// Integer
				
						writeInteger(this.t1_s3,dos);
					
					// Integer
				
						writeInteger(this.t2_s3,dos);
					
					// Integer
				
						writeInteger(this.views,dos);
					
					// Integer
				
						writeInteger(this.interactions,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("tournament_name="+tournament_name);
		sb.append(",match_id="+String.valueOf(match_id));
		sb.append(",round="+round);
		sb.append(",winner="+winner);
		sb.append(",total_points_won_t1="+total_points_won_t1);
		sb.append(",total_points_won_t2="+total_points_won_t2);
		sb.append(",break_points_converted_t1="+break_points_converted_t1);
		sb.append(",break_points_converted_t2="+break_points_converted_t2);
		sb.append(",longest_streak_t1="+String.valueOf(longest_streak_t1));
		sb.append(",longest_streak_t2="+String.valueOf(longest_streak_t2));
		sb.append(",aces_t1="+String.valueOf(aces_t1));
		sb.append(",aces_t2="+String.valueOf(aces_t2));
		sb.append(",double_faults_t1="+String.valueOf(double_faults_t1));
		sb.append(",double_faults_t2="+String.valueOf(double_faults_t2));
		sb.append(",won_on_1st_serve_t1="+won_on_1st_serve_t1);
		sb.append(",won_on_1st_serve_t2="+won_on_1st_serve_t2);
		sb.append(",won_on_2nd_serve_t1="+won_on_2nd_serve_t1);
		sb.append(",won_on_2nd_serve_t2="+won_on_2nd_serve_t2);
		sb.append(",service_games_t1="+String.valueOf(service_games_t1));
		sb.append(",service_games_t2="+String.valueOf(service_games_t2));
		sb.append(",won_on_1st_return_t1="+won_on_1st_return_t1);
		sb.append(",won_on_1st_return_t2="+won_on_1st_return_t2);
		sb.append(",won_on_2nd_return_t1="+won_on_2nd_return_t1);
		sb.append(",won_on_2nd_return_t2="+won_on_2nd_return_t2);
		sb.append(",return_games_t1="+String.valueOf(return_games_t1));
		sb.append(",return_games_t2="+String.valueOf(return_games_t2));
		sb.append(",total_won_on_serve_t1="+total_won_on_serve_t1);
		sb.append(",total_won_on_serve_t2="+total_won_on_serve_t2);
		sb.append(",total_won_on_return_t1="+total_won_on_return_t1);
		sb.append(",total_won_on_return_t2="+total_won_on_return_t2);
		sb.append(",date="+date);
		sb.append(",team1_player1_name="+team1_player1_name);
		sb.append(",team1_player2_name="+team1_player2_name);
		sb.append(",team2_player1_name="+team2_player1_name);
		sb.append(",team2_player2_name="+team2_player2_name);
		sb.append(",match_info_added="+String.valueOf(match_info_added));
		sb.append(",t1_s1="+String.valueOf(t1_s1));
		sb.append(",t2_s1="+String.valueOf(t2_s1));
		sb.append(",t1_s2="+String.valueOf(t1_s2));
		sb.append(",t2_s2="+String.valueOf(t2_s2));
		sb.append(",t1_s3="+String.valueOf(t1_s3));
		sb.append(",t2_s3="+String.valueOf(t2_s3));
		sb.append(",views="+String.valueOf(views));
		sb.append(",interactions="+String.valueOf(interactions));
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(row1Struct other) {

		int returnValue = -1;
		
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}

public static class after_tFileInputExcel_1Struct implements routines.system.IPersistableRow<after_tFileInputExcel_1Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_FACT_MATCHS = new byte[0];
    static byte[] commonByteArray_DW_PADEL_FACT_MATCHS = new byte[0];

	
			    public String tournament_name;

				public String getTournament_name () {
					return this.tournament_name;
				}
				
			    public Integer match_id;

				public Integer getMatch_id () {
					return this.match_id;
				}
				
			    public String round;

				public String getRound () {
					return this.round;
				}
				
			    public String winner;

				public String getWinner () {
					return this.winner;
				}
				
			    public String total_points_won_t1;

				public String getTotal_points_won_t1 () {
					return this.total_points_won_t1;
				}
				
			    public String total_points_won_t2;

				public String getTotal_points_won_t2 () {
					return this.total_points_won_t2;
				}
				
			    public String break_points_converted_t1;

				public String getBreak_points_converted_t1 () {
					return this.break_points_converted_t1;
				}
				
			    public String break_points_converted_t2;

				public String getBreak_points_converted_t2 () {
					return this.break_points_converted_t2;
				}
				
			    public Integer longest_streak_t1;

				public Integer getLongest_streak_t1 () {
					return this.longest_streak_t1;
				}
				
			    public Integer longest_streak_t2;

				public Integer getLongest_streak_t2 () {
					return this.longest_streak_t2;
				}
				
			    public Integer aces_t1;

				public Integer getAces_t1 () {
					return this.aces_t1;
				}
				
			    public Integer aces_t2;

				public Integer getAces_t2 () {
					return this.aces_t2;
				}
				
			    public Integer double_faults_t1;

				public Integer getDouble_faults_t1 () {
					return this.double_faults_t1;
				}
				
			    public Integer double_faults_t2;

				public Integer getDouble_faults_t2 () {
					return this.double_faults_t2;
				}
				
			    public String won_on_1st_serve_t1;

				public String getWon_on_1st_serve_t1 () {
					return this.won_on_1st_serve_t1;
				}
				
			    public String won_on_1st_serve_t2;

				public String getWon_on_1st_serve_t2 () {
					return this.won_on_1st_serve_t2;
				}
				
			    public String won_on_2nd_serve_t1;

				public String getWon_on_2nd_serve_t1 () {
					return this.won_on_2nd_serve_t1;
				}
				
			    public String won_on_2nd_serve_t2;

				public String getWon_on_2nd_serve_t2 () {
					return this.won_on_2nd_serve_t2;
				}
				
			    public Integer service_games_t1;

				public Integer getService_games_t1 () {
					return this.service_games_t1;
				}
				
			    public Integer service_games_t2;

				public Integer getService_games_t2 () {
					return this.service_games_t2;
				}
				
			    public String won_on_1st_return_t1;

				public String getWon_on_1st_return_t1 () {
					return this.won_on_1st_return_t1;
				}
				
			    public String won_on_1st_return_t2;

				public String getWon_on_1st_return_t2 () {
					return this.won_on_1st_return_t2;
				}
				
			    public String won_on_2nd_return_t1;

				public String getWon_on_2nd_return_t1 () {
					return this.won_on_2nd_return_t1;
				}
				
			    public String won_on_2nd_return_t2;

				public String getWon_on_2nd_return_t2 () {
					return this.won_on_2nd_return_t2;
				}
				
			    public Integer return_games_t1;

				public Integer getReturn_games_t1 () {
					return this.return_games_t1;
				}
				
			    public Integer return_games_t2;

				public Integer getReturn_games_t2 () {
					return this.return_games_t2;
				}
				
			    public String total_won_on_serve_t1;

				public String getTotal_won_on_serve_t1 () {
					return this.total_won_on_serve_t1;
				}
				
			    public String total_won_on_serve_t2;

				public String getTotal_won_on_serve_t2 () {
					return this.total_won_on_serve_t2;
				}
				
			    public String total_won_on_return_t1;

				public String getTotal_won_on_return_t1 () {
					return this.total_won_on_return_t1;
				}
				
			    public String total_won_on_return_t2;

				public String getTotal_won_on_return_t2 () {
					return this.total_won_on_return_t2;
				}
				
			    public String date;

				public String getDate () {
					return this.date;
				}
				
			    public String team1_player1_name;

				public String getTeam1_player1_name () {
					return this.team1_player1_name;
				}
				
			    public String team1_player2_name;

				public String getTeam1_player2_name () {
					return this.team1_player2_name;
				}
				
			    public String team2_player1_name;

				public String getTeam2_player1_name () {
					return this.team2_player1_name;
				}
				
			    public String team2_player2_name;

				public String getTeam2_player2_name () {
					return this.team2_player2_name;
				}
				
			    public Integer match_info_added;

				public Integer getMatch_info_added () {
					return this.match_info_added;
				}
				
			    public Integer t1_s1;

				public Integer getT1_s1 () {
					return this.t1_s1;
				}
				
			    public Integer t2_s1;

				public Integer getT2_s1 () {
					return this.t2_s1;
				}
				
			    public Integer t1_s2;

				public Integer getT1_s2 () {
					return this.t1_s2;
				}
				
			    public Integer t2_s2;

				public Integer getT2_s2 () {
					return this.t2_s2;
				}
				
			    public Integer t1_s3;

				public Integer getT1_s3 () {
					return this.t1_s3;
				}
				
			    public Integer t2_s3;

				public Integer getT2_s3 () {
					return this.t2_s3;
				}
				
			    public Integer views;

				public Integer getViews () {
					return this.views;
				}
				
			    public Integer interactions;

				public Integer getInteractions () {
					return this.interactions;
				}
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_FACT_MATCHS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_MATCHS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_FACT_MATCHS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_MATCHS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length, utf8Charset);
		}
		return strReturn;
	}

    private void writeString(String str, ObjectOutputStream dos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
    }
    
    private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
    }
	private Integer readInteger(ObjectInputStream dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}
	
	private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}

	private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException{
		if(intNum == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeInt(intNum);
    	}
	}
	
	private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(intNum == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeInt(intNum);
    	}
	}

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_MATCHS) {

        	try {

        		int length = 0;
		
					this.tournament_name = readString(dis);
					
						this.match_id = readInteger(dis);
					
					this.round = readString(dis);
					
					this.winner = readString(dis);
					
					this.total_points_won_t1 = readString(dis);
					
					this.total_points_won_t2 = readString(dis);
					
					this.break_points_converted_t1 = readString(dis);
					
					this.break_points_converted_t2 = readString(dis);
					
						this.longest_streak_t1 = readInteger(dis);
					
						this.longest_streak_t2 = readInteger(dis);
					
						this.aces_t1 = readInteger(dis);
					
						this.aces_t2 = readInteger(dis);
					
						this.double_faults_t1 = readInteger(dis);
					
						this.double_faults_t2 = readInteger(dis);
					
					this.won_on_1st_serve_t1 = readString(dis);
					
					this.won_on_1st_serve_t2 = readString(dis);
					
					this.won_on_2nd_serve_t1 = readString(dis);
					
					this.won_on_2nd_serve_t2 = readString(dis);
					
						this.service_games_t1 = readInteger(dis);
					
						this.service_games_t2 = readInteger(dis);
					
					this.won_on_1st_return_t1 = readString(dis);
					
					this.won_on_1st_return_t2 = readString(dis);
					
					this.won_on_2nd_return_t1 = readString(dis);
					
					this.won_on_2nd_return_t2 = readString(dis);
					
						this.return_games_t1 = readInteger(dis);
					
						this.return_games_t2 = readInteger(dis);
					
					this.total_won_on_serve_t1 = readString(dis);
					
					this.total_won_on_serve_t2 = readString(dis);
					
					this.total_won_on_return_t1 = readString(dis);
					
					this.total_won_on_return_t2 = readString(dis);
					
					this.date = readString(dis);
					
					this.team1_player1_name = readString(dis);
					
					this.team1_player2_name = readString(dis);
					
					this.team2_player1_name = readString(dis);
					
					this.team2_player2_name = readString(dis);
					
						this.match_info_added = readInteger(dis);
					
						this.t1_s1 = readInteger(dis);
					
						this.t2_s1 = readInteger(dis);
					
						this.t1_s2 = readInteger(dis);
					
						this.t2_s2 = readInteger(dis);
					
						this.t1_s3 = readInteger(dis);
					
						this.t2_s3 = readInteger(dis);
					
						this.views = readInteger(dis);
					
						this.interactions = readInteger(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_MATCHS) {

        	try {

        		int length = 0;
		
					this.tournament_name = readString(dis);
					
						this.match_id = readInteger(dis);
					
					this.round = readString(dis);
					
					this.winner = readString(dis);
					
					this.total_points_won_t1 = readString(dis);
					
					this.total_points_won_t2 = readString(dis);
					
					this.break_points_converted_t1 = readString(dis);
					
					this.break_points_converted_t2 = readString(dis);
					
						this.longest_streak_t1 = readInteger(dis);
					
						this.longest_streak_t2 = readInteger(dis);
					
						this.aces_t1 = readInteger(dis);
					
						this.aces_t2 = readInteger(dis);
					
						this.double_faults_t1 = readInteger(dis);
					
						this.double_faults_t2 = readInteger(dis);
					
					this.won_on_1st_serve_t1 = readString(dis);
					
					this.won_on_1st_serve_t2 = readString(dis);
					
					this.won_on_2nd_serve_t1 = readString(dis);
					
					this.won_on_2nd_serve_t2 = readString(dis);
					
						this.service_games_t1 = readInteger(dis);
					
						this.service_games_t2 = readInteger(dis);
					
					this.won_on_1st_return_t1 = readString(dis);
					
					this.won_on_1st_return_t2 = readString(dis);
					
					this.won_on_2nd_return_t1 = readString(dis);
					
					this.won_on_2nd_return_t2 = readString(dis);
					
						this.return_games_t1 = readInteger(dis);
					
						this.return_games_t2 = readInteger(dis);
					
					this.total_won_on_serve_t1 = readString(dis);
					
					this.total_won_on_serve_t2 = readString(dis);
					
					this.total_won_on_return_t1 = readString(dis);
					
					this.total_won_on_return_t2 = readString(dis);
					
					this.date = readString(dis);
					
					this.team1_player1_name = readString(dis);
					
					this.team1_player2_name = readString(dis);
					
					this.team2_player1_name = readString(dis);
					
					this.team2_player2_name = readString(dis);
					
						this.match_info_added = readInteger(dis);
					
						this.t1_s1 = readInteger(dis);
					
						this.t2_s1 = readInteger(dis);
					
						this.t1_s2 = readInteger(dis);
					
						this.t2_s2 = readInteger(dis);
					
						this.t1_s3 = readInteger(dis);
					
						this.t2_s3 = readInteger(dis);
					
						this.views = readInteger(dis);
					
						this.interactions = readInteger(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.tournament_name,dos);
					
					// Integer
				
						writeInteger(this.match_id,dos);
					
					// String
				
						writeString(this.round,dos);
					
					// String
				
						writeString(this.winner,dos);
					
					// String
				
						writeString(this.total_points_won_t1,dos);
					
					// String
				
						writeString(this.total_points_won_t2,dos);
					
					// String
				
						writeString(this.break_points_converted_t1,dos);
					
					// String
				
						writeString(this.break_points_converted_t2,dos);
					
					// Integer
				
						writeInteger(this.longest_streak_t1,dos);
					
					// Integer
				
						writeInteger(this.longest_streak_t2,dos);
					
					// Integer
				
						writeInteger(this.aces_t1,dos);
					
					// Integer
				
						writeInteger(this.aces_t2,dos);
					
					// Integer
				
						writeInteger(this.double_faults_t1,dos);
					
					// Integer
				
						writeInteger(this.double_faults_t2,dos);
					
					// String
				
						writeString(this.won_on_1st_serve_t1,dos);
					
					// String
				
						writeString(this.won_on_1st_serve_t2,dos);
					
					// String
				
						writeString(this.won_on_2nd_serve_t1,dos);
					
					// String
				
						writeString(this.won_on_2nd_serve_t2,dos);
					
					// Integer
				
						writeInteger(this.service_games_t1,dos);
					
					// Integer
				
						writeInteger(this.service_games_t2,dos);
					
					// String
				
						writeString(this.won_on_1st_return_t1,dos);
					
					// String
				
						writeString(this.won_on_1st_return_t2,dos);
					
					// String
				
						writeString(this.won_on_2nd_return_t1,dos);
					
					// String
				
						writeString(this.won_on_2nd_return_t2,dos);
					
					// Integer
				
						writeInteger(this.return_games_t1,dos);
					
					// Integer
				
						writeInteger(this.return_games_t2,dos);
					
					// String
				
						writeString(this.total_won_on_serve_t1,dos);
					
					// String
				
						writeString(this.total_won_on_serve_t2,dos);
					
					// String
				
						writeString(this.total_won_on_return_t1,dos);
					
					// String
				
						writeString(this.total_won_on_return_t2,dos);
					
					// String
				
						writeString(this.date,dos);
					
					// String
				
						writeString(this.team1_player1_name,dos);
					
					// String
				
						writeString(this.team1_player2_name,dos);
					
					// String
				
						writeString(this.team2_player1_name,dos);
					
					// String
				
						writeString(this.team2_player2_name,dos);
					
					// Integer
				
						writeInteger(this.match_info_added,dos);
					
					// Integer
				
						writeInteger(this.t1_s1,dos);
					
					// Integer
				
						writeInteger(this.t2_s1,dos);
					
					// Integer
				
						writeInteger(this.t1_s2,dos);
					
					// Integer
				
						writeInteger(this.t2_s2,dos);
					
					// Integer
				
						writeInteger(this.t1_s3,dos);
					
					// Integer
				
						writeInteger(this.t2_s3,dos);
					
					// Integer
				
						writeInteger(this.views,dos);
					
					// Integer
				
						writeInteger(this.interactions,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.tournament_name,dos);
					
					// Integer
				
						writeInteger(this.match_id,dos);
					
					// String
				
						writeString(this.round,dos);
					
					// String
				
						writeString(this.winner,dos);
					
					// String
				
						writeString(this.total_points_won_t1,dos);
					
					// String
				
						writeString(this.total_points_won_t2,dos);
					
					// String
				
						writeString(this.break_points_converted_t1,dos);
					
					// String
				
						writeString(this.break_points_converted_t2,dos);
					
					// Integer
				
						writeInteger(this.longest_streak_t1,dos);
					
					// Integer
				
						writeInteger(this.longest_streak_t2,dos);
					
					// Integer
				
						writeInteger(this.aces_t1,dos);
					
					// Integer
				
						writeInteger(this.aces_t2,dos);
					
					// Integer
				
						writeInteger(this.double_faults_t1,dos);
					
					// Integer
				
						writeInteger(this.double_faults_t2,dos);
					
					// String
				
						writeString(this.won_on_1st_serve_t1,dos);
					
					// String
				
						writeString(this.won_on_1st_serve_t2,dos);
					
					// String
				
						writeString(this.won_on_2nd_serve_t1,dos);
					
					// String
				
						writeString(this.won_on_2nd_serve_t2,dos);
					
					// Integer
				
						writeInteger(this.service_games_t1,dos);
					
					// Integer
				
						writeInteger(this.service_games_t2,dos);
					
					// String
				
						writeString(this.won_on_1st_return_t1,dos);
					
					// String
				
						writeString(this.won_on_1st_return_t2,dos);
					
					// String
				
						writeString(this.won_on_2nd_return_t1,dos);
					
					// String
				
						writeString(this.won_on_2nd_return_t2,dos);
					
					// Integer
				
						writeInteger(this.return_games_t1,dos);
					
					// Integer
				
						writeInteger(this.return_games_t2,dos);
					
					// String
				
						writeString(this.total_won_on_serve_t1,dos);
					
					// String
				
						writeString(this.total_won_on_serve_t2,dos);
					
					// String
				
						writeString(this.total_won_on_return_t1,dos);
					
					// String
				
						writeString(this.total_won_on_return_t2,dos);
					
					// String
				
						writeString(this.date,dos);
					
					// String
				
						writeString(this.team1_player1_name,dos);
					
					// String
				
						writeString(this.team1_player2_name,dos);
					
					// String
				
						writeString(this.team2_player1_name,dos);
					
					// String
				
						writeString(this.team2_player2_name,dos);
					
					// Integer
				
						writeInteger(this.match_info_added,dos);
					
					// Integer
				
						writeInteger(this.t1_s1,dos);
					
					// Integer
				
						writeInteger(this.t2_s1,dos);
					
					// Integer
				
						writeInteger(this.t1_s2,dos);
					
					// Integer
				
						writeInteger(this.t2_s2,dos);
					
					// Integer
				
						writeInteger(this.t1_s3,dos);
					
					// Integer
				
						writeInteger(this.t2_s3,dos);
					
					// Integer
				
						writeInteger(this.views,dos);
					
					// Integer
				
						writeInteger(this.interactions,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("tournament_name="+tournament_name);
		sb.append(",match_id="+String.valueOf(match_id));
		sb.append(",round="+round);
		sb.append(",winner="+winner);
		sb.append(",total_points_won_t1="+total_points_won_t1);
		sb.append(",total_points_won_t2="+total_points_won_t2);
		sb.append(",break_points_converted_t1="+break_points_converted_t1);
		sb.append(",break_points_converted_t2="+break_points_converted_t2);
		sb.append(",longest_streak_t1="+String.valueOf(longest_streak_t1));
		sb.append(",longest_streak_t2="+String.valueOf(longest_streak_t2));
		sb.append(",aces_t1="+String.valueOf(aces_t1));
		sb.append(",aces_t2="+String.valueOf(aces_t2));
		sb.append(",double_faults_t1="+String.valueOf(double_faults_t1));
		sb.append(",double_faults_t2="+String.valueOf(double_faults_t2));
		sb.append(",won_on_1st_serve_t1="+won_on_1st_serve_t1);
		sb.append(",won_on_1st_serve_t2="+won_on_1st_serve_t2);
		sb.append(",won_on_2nd_serve_t1="+won_on_2nd_serve_t1);
		sb.append(",won_on_2nd_serve_t2="+won_on_2nd_serve_t2);
		sb.append(",service_games_t1="+String.valueOf(service_games_t1));
		sb.append(",service_games_t2="+String.valueOf(service_games_t2));
		sb.append(",won_on_1st_return_t1="+won_on_1st_return_t1);
		sb.append(",won_on_1st_return_t2="+won_on_1st_return_t2);
		sb.append(",won_on_2nd_return_t1="+won_on_2nd_return_t1);
		sb.append(",won_on_2nd_return_t2="+won_on_2nd_return_t2);
		sb.append(",return_games_t1="+String.valueOf(return_games_t1));
		sb.append(",return_games_t2="+String.valueOf(return_games_t2));
		sb.append(",total_won_on_serve_t1="+total_won_on_serve_t1);
		sb.append(",total_won_on_serve_t2="+total_won_on_serve_t2);
		sb.append(",total_won_on_return_t1="+total_won_on_return_t1);
		sb.append(",total_won_on_return_t2="+total_won_on_return_t2);
		sb.append(",date="+date);
		sb.append(",team1_player1_name="+team1_player1_name);
		sb.append(",team1_player2_name="+team1_player2_name);
		sb.append(",team2_player1_name="+team2_player1_name);
		sb.append(",team2_player2_name="+team2_player2_name);
		sb.append(",match_info_added="+String.valueOf(match_info_added));
		sb.append(",t1_s1="+String.valueOf(t1_s1));
		sb.append(",t2_s1="+String.valueOf(t2_s1));
		sb.append(",t1_s2="+String.valueOf(t1_s2));
		sb.append(",t2_s2="+String.valueOf(t2_s2));
		sb.append(",t1_s3="+String.valueOf(t1_s3));
		sb.append(",t2_s3="+String.valueOf(t2_s3));
		sb.append(",views="+String.valueOf(views));
		sb.append(",interactions="+String.valueOf(interactions));
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(after_tFileInputExcel_1Struct other) {

		int returnValue = -1;
		
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}
public void tFileInputExcel_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { //start the resume
				globalResumeTicket = true;


		tDBInput_2Process(globalMap);
		tDBInput_4Process(globalMap);
		tDBInput_5Process(globalMap);
		tDBInput_6Process(globalMap);
		tDBInput_7Process(globalMap);
		tDBInput_3Process(globalMap);

		row1Struct row1 = new row1Struct();
factStruct fact = new factStruct();





	
	/**
	 * [tDBOutput_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tDBOutput_1", false);
		start_Hash.put("tDBOutput_1", System.currentTimeMillis());
		
	
	currentComponent="tDBOutput_1";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"fact");
					}
				
		int tos_count_tDBOutput_1 = 0;
		



int nb_line_tDBOutput_1 = 0;
int nb_line_update_tDBOutput_1 = 0;
int nb_line_inserted_tDBOutput_1 = 0;
int nb_line_deleted_tDBOutput_1 = 0;
int nb_line_rejected_tDBOutput_1 = 0;

int deletedCount_tDBOutput_1=0;
int updatedCount_tDBOutput_1=0;
int insertedCount_tDBOutput_1=0;
int rowsToCommitCount_tDBOutput_1=0;
int rejectedCount_tDBOutput_1=0;
String dbschema_tDBOutput_1 = null;
String tableName_tDBOutput_1 = null;
boolean whetherReject_tDBOutput_1 = false;

java.util.Calendar calendar_tDBOutput_1 = java.util.Calendar.getInstance();
long year1_tDBOutput_1 = TalendDate.parseDate("yyyy-MM-dd","0001-01-01").getTime();
long year2_tDBOutput_1 = TalendDate.parseDate("yyyy-MM-dd","1753-01-01").getTime();
long year10000_tDBOutput_1 = TalendDate.parseDate("yyyy-MM-dd HH:mm:ss","9999-12-31 24:00:00").getTime();
long date_tDBOutput_1;

java.util.Calendar calendar_datetimeoffset_tDBOutput_1 = java.util.Calendar.getInstance(java.util.TimeZone.getTimeZone("UTC"));



	
java.sql.Connection conn_tDBOutput_1 = null;
String dbUser_tDBOutput_1 = null;
    dbschema_tDBOutput_1 = "";
    String driverClass_tDBOutput_1 = "net.sourceforge.jtds.jdbc.Driver";
	
    java.lang.Class.forName(driverClass_tDBOutput_1);
    String port_tDBOutput_1 = "1433";
    String dbname_tDBOutput_1 = "DW_padel" ;
    String url_tDBOutput_1 = "jdbc:jtds:sqlserver://" + "DESKTOP-QJ70MNR" ; 
    if (!"".equals(port_tDBOutput_1)) {
    	url_tDBOutput_1 += ":" + "1433";
    }
    if (!"".equals(dbname_tDBOutput_1)) {
				url_tDBOutput_1 += "//" + "DW_padel"; 
	
    }
    url_tDBOutput_1 += ";appName=" + projectName + ";" + "";
    dbUser_tDBOutput_1 = "Padelle";

 
	final String decryptedPassword_tDBOutput_1 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:YJhxKjRR9JlwuY4GFDIdFXzzd18dEBO7IQUaw47XKxKIDno=");

    String dbPwd_tDBOutput_1 = decryptedPassword_tDBOutput_1;	
    conn_tDBOutput_1 = java.sql.DriverManager.getConnection(url_tDBOutput_1,dbUser_tDBOutput_1,dbPwd_tDBOutput_1);
	
		resourceMap.put("conn_tDBOutput_1", conn_tDBOutput_1);
	
        conn_tDBOutput_1.setAutoCommit(false);
        int commitEvery_tDBOutput_1 = 10000;
        int commitCounter_tDBOutput_1 = 0;

   int batchSize_tDBOutput_1 = 10000;
   int batchSizeCounter_tDBOutput_1=0;

if(dbschema_tDBOutput_1 == null || dbschema_tDBOutput_1.trim().length() == 0) {
    tableName_tDBOutput_1 = "FACT_MATCHS";
} else {
    tableName_tDBOutput_1 = dbschema_tDBOutput_1 + "].[" + "FACT_MATCHS";
}
	int count_tDBOutput_1=0;

        String insert_tDBOutput_1 = "INSERT INTO [" + tableName_tDBOutput_1 + "] ([date_key],[tournament_id],[t1_p1_id],[t1_p2_id],[t2_p1_id],[t2_p2_id],[views],[interactions],[winners],[won_on_1st_serve_t2],[won_on_2nd_serve_t2],[break_points_saved_t1],[round],[aces_t1],[aces_t2],[double_faults_t1],[double_faults_t2],[won_on_1st_serve_t1],[won_on_2nd_serve_t1],[service_games_t1],[service_games_t2],[won_on_1st_return_t1],[won_on_1st_return_t2],[won_on_2nd_return_t1],[won_on_2nd_return_t2],[return_games_t1],[return_games_t2],[total_won_on_serve_t1],[total_won_on_serve_t2],[total_won_on_return_t1],[total_won_on_return_t2],[match_info_added],[t1_s1],[t2_s1],[t1_s2],[t2_s2],[t1_s3],[t2_s3]) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        java.sql.PreparedStatement pstmt_tDBOutput_1 = conn_tDBOutput_1.prepareStatement(insert_tDBOutput_1);
        resourceMap.put("pstmt_tDBOutput_1", pstmt_tDBOutput_1);


 



/**
 * [tDBOutput_1 begin ] stop
 */



	
	/**
	 * [tMap_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tMap_1", false);
		start_Hash.put("tMap_1", System.currentTimeMillis());
		
	
	currentComponent="tMap_1";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row1");
					}
				
		int tos_count_tMap_1 = 0;
		




// ###############################
// # Lookup's keys initialization
	
		org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct> tHash_Lookup_row2 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct>) 
				((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct>) 
					globalMap.get( "tHash_Lookup_row2" ))
					;					
					
	

row2Struct row2HashKey = new row2Struct();
row2Struct row2Default = new row2Struct();
	
		org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct> tHash_Lookup_row4 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct>) 
				((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct>) 
					globalMap.get( "tHash_Lookup_row4" ))
					;					
					
	

row4Struct row4HashKey = new row4Struct();
row4Struct row4Default = new row4Struct();
	
		org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct> tHash_Lookup_row5 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct>) 
				((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct>) 
					globalMap.get( "tHash_Lookup_row5" ))
					;					
					
	

row5Struct row5HashKey = new row5Struct();
row5Struct row5Default = new row5Struct();
	
		org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row6Struct> tHash_Lookup_row6 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row6Struct>) 
				((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row6Struct>) 
					globalMap.get( "tHash_Lookup_row6" ))
					;					
					
	

row6Struct row6HashKey = new row6Struct();
row6Struct row6Default = new row6Struct();
	
		org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row7Struct> tHash_Lookup_row7 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row7Struct>) 
				((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row7Struct>) 
					globalMap.get( "tHash_Lookup_row7" ))
					;					
					
	

row7Struct row7HashKey = new row7Struct();
row7Struct row7Default = new row7Struct();
	
		org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct> tHash_Lookup_row3 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct>) 
				((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct>) 
					globalMap.get( "tHash_Lookup_row3" ))
					;					
					
	

row3Struct row3HashKey = new row3Struct();
row3Struct row3Default = new row3Struct();
// ###############################        

// ###############################
// # Vars initialization
class  Var__tMap_1__Struct  {
}
Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
factStruct fact_tmp = new factStruct();
// ###############################

        
        



        









 



/**
 * [tMap_1 begin ] stop
 */



	
	/**
	 * [tFileInputExcel_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tFileInputExcel_1", false);
		start_Hash.put("tFileInputExcel_1", System.currentTimeMillis());
		
	
	currentComponent="tFileInputExcel_1";

	
		int tos_count_tFileInputExcel_1 = 0;
		

 
	final String decryptedPassword_tFileInputExcel_1 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:EFTFuxluyRVfrIcpYhFui6FP6vbz4WCNJqXreA==");
        String password_tFileInputExcel_1 = decryptedPassword_tFileInputExcel_1;
        if (password_tFileInputExcel_1.isEmpty()){
            password_tFileInputExcel_1 = null;
        }
			class RegexUtil_tFileInputExcel_1 {

		    	public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, String oneSheetName, boolean useRegex) {

			        java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();

			        if(useRegex){//this part process the regex issue

				        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(oneSheetName);
				        for (org.apache.poi.ss.usermodel.Sheet sheet : workbook) {
				            String sheetName = sheet.getSheetName();
				            java.util.regex.Matcher matcher = pattern.matcher(sheetName);
				            if (matcher.matches()) {
				            	if(sheet != null){
				                	list.add((org.apache.poi.xssf.usermodel.XSSFSheet) sheet);
				                }
				            }
				        }

			        }else{
			        	org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook.getSheet(oneSheetName);
		            	if(sheet != null){
		                	list.add(sheet);
		                }

			        }

			        return list;
			    }

			    public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, int index, boolean useRegex) {
			    	java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list =  new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
			    	org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook.getSheetAt(index);
	            	if(sheet != null){
	                	list.add(sheet);
	                }
			    	return list;
			    }

			}
		RegexUtil_tFileInputExcel_1 regexUtil_tFileInputExcel_1 = new RegexUtil_tFileInputExcel_1();

		Object source_tFileInputExcel_1 = "C:/pi/Matches.xlsx";
		org.apache.poi.xssf.usermodel.XSSFWorkbook workbook_tFileInputExcel_1 = null;

		if(source_tFileInputExcel_1 instanceof String){
			workbook_tFileInputExcel_1 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory.create(new java.io.File((String)source_tFileInputExcel_1), password_tFileInputExcel_1, true);
		} else if(source_tFileInputExcel_1 instanceof java.io.InputStream) {
     		workbook_tFileInputExcel_1 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory.create((java.io.InputStream)source_tFileInputExcel_1, password_tFileInputExcel_1);
		} else{
			workbook_tFileInputExcel_1 = null;
			throw new java.lang.Exception("The data source should be specified as Inputstream or File Path!");
		}
		try {

    	java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_tFileInputExcel_1 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
    	for(org.apache.poi.ss.usermodel.Sheet sheet_tFileInputExcel_1 : workbook_tFileInputExcel_1){
   			sheetList_tFileInputExcel_1.add((org.apache.poi.xssf.usermodel.XSSFSheet) sheet_tFileInputExcel_1);
    	}
    	if(sheetList_tFileInputExcel_1.size() <= 0){
            throw new RuntimeException("Special sheets not exist!");
        }

		java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_FilterNull_tFileInputExcel_1 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
		for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_FilterNull_tFileInputExcel_1 : sheetList_tFileInputExcel_1) {
			if(sheet_FilterNull_tFileInputExcel_1!=null && sheetList_FilterNull_tFileInputExcel_1.iterator()!=null && sheet_FilterNull_tFileInputExcel_1.iterator().hasNext()){
				sheetList_FilterNull_tFileInputExcel_1.add(sheet_FilterNull_tFileInputExcel_1);
			}
		}
		sheetList_tFileInputExcel_1 = sheetList_FilterNull_tFileInputExcel_1;
	if(sheetList_tFileInputExcel_1.size()>0){
		int nb_line_tFileInputExcel_1 = 0;

        int begin_line_tFileInputExcel_1 = 1;

        int footer_input_tFileInputExcel_1 = 0;

        int end_line_tFileInputExcel_1=0;
        for(org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_1:sheetList_tFileInputExcel_1){
			end_line_tFileInputExcel_1+=(sheet_tFileInputExcel_1.getLastRowNum()+1);
        }
        end_line_tFileInputExcel_1 -= footer_input_tFileInputExcel_1;
        int limit_tFileInputExcel_1 = -1;
        int start_column_tFileInputExcel_1 = 1-1;
        int end_column_tFileInputExcel_1 = -1;

        org.apache.poi.xssf.usermodel.XSSFRow row_tFileInputExcel_1 = null;
        org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_1 = sheetList_tFileInputExcel_1.get(0);
        int rowCount_tFileInputExcel_1 = 0;
        int sheetIndex_tFileInputExcel_1 = 0;
        int currentRows_tFileInputExcel_1 = (sheetList_tFileInputExcel_1.get(0).getLastRowNum()+1);

		//for the number format
        java.text.DecimalFormat df_tFileInputExcel_1 = new java.text.DecimalFormat("#.####################################");
        char decimalChar_tFileInputExcel_1 = df_tFileInputExcel_1.getDecimalFormatSymbols().getDecimalSeparator();
		
        for(int i_tFileInputExcel_1 = begin_line_tFileInputExcel_1; i_tFileInputExcel_1 < end_line_tFileInputExcel_1; i_tFileInputExcel_1++){

        	int emptyColumnCount_tFileInputExcel_1 = 0;

        	if (limit_tFileInputExcel_1 != -1 && nb_line_tFileInputExcel_1 >= limit_tFileInputExcel_1) {
        		break;
        	}

            while (i_tFileInputExcel_1 >= rowCount_tFileInputExcel_1 + currentRows_tFileInputExcel_1) {
                rowCount_tFileInputExcel_1 += currentRows_tFileInputExcel_1;
                sheet_tFileInputExcel_1 = sheetList_tFileInputExcel_1.get(++sheetIndex_tFileInputExcel_1);
                currentRows_tFileInputExcel_1 = (sheet_tFileInputExcel_1.getLastRowNum()+1);
            }
            globalMap.put("tFileInputExcel_1_CURRENT_SHEET",sheet_tFileInputExcel_1.getSheetName());
            if (rowCount_tFileInputExcel_1 <= i_tFileInputExcel_1) {
                row_tFileInputExcel_1 = sheet_tFileInputExcel_1.getRow(i_tFileInputExcel_1 - rowCount_tFileInputExcel_1);
            }
		    row1 = null;
					int tempRowLength_tFileInputExcel_1 = 44;
				
				int columnIndex_tFileInputExcel_1 = 0;
			
			String[] temp_row_tFileInputExcel_1 = new String[tempRowLength_tFileInputExcel_1];
			int excel_end_column_tFileInputExcel_1;
			if(row_tFileInputExcel_1==null){
				excel_end_column_tFileInputExcel_1=0;
			}else{
				excel_end_column_tFileInputExcel_1=row_tFileInputExcel_1.getLastCellNum();
			}
			int actual_end_column_tFileInputExcel_1;
			if(end_column_tFileInputExcel_1 == -1){
				actual_end_column_tFileInputExcel_1 = excel_end_column_tFileInputExcel_1;
			}
			else{
				actual_end_column_tFileInputExcel_1 = end_column_tFileInputExcel_1 >	excel_end_column_tFileInputExcel_1 ? excel_end_column_tFileInputExcel_1 : end_column_tFileInputExcel_1;
			}
			org.apache.poi.ss.formula.eval.NumberEval ne_tFileInputExcel_1 = null;
			for(int i=0;i<tempRowLength_tFileInputExcel_1;i++){
				if(i + start_column_tFileInputExcel_1 < actual_end_column_tFileInputExcel_1){
					org.apache.poi.ss.usermodel.Cell cell_tFileInputExcel_1 = row_tFileInputExcel_1.getCell(i + start_column_tFileInputExcel_1);
					if(cell_tFileInputExcel_1!=null){
					switch (cell_tFileInputExcel_1.getCellType()) {
                        case STRING:
                            temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1.getRichStringCellValue().getString();
                            break;
                        case NUMERIC:
                            if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell_tFileInputExcel_1)) {
									temp_row_tFileInputExcel_1[i] =cell_tFileInputExcel_1.getDateCellValue().toString();
                            } else {
                                temp_row_tFileInputExcel_1[i] = df_tFileInputExcel_1.format(cell_tFileInputExcel_1.getNumericCellValue());
                            }
                            break;
                        case BOOLEAN:
                            temp_row_tFileInputExcel_1[i] =String.valueOf(cell_tFileInputExcel_1.getBooleanCellValue());
                            break;
                        case FORMULA:
        					switch (cell_tFileInputExcel_1.getCachedFormulaResultType()) {
                                case STRING:
                                    temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1.getRichStringCellValue().getString();
                                    break;
                                case NUMERIC:
                                    if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell_tFileInputExcel_1)) {
											temp_row_tFileInputExcel_1[i] =cell_tFileInputExcel_1.getDateCellValue().toString();
                                    } else {
	                                    ne_tFileInputExcel_1 = new org.apache.poi.ss.formula.eval.NumberEval(cell_tFileInputExcel_1.getNumericCellValue());
										temp_row_tFileInputExcel_1[i] = ne_tFileInputExcel_1.getStringValue();
                                    }
                                    break;
                                case BOOLEAN:
                                    temp_row_tFileInputExcel_1[i] =String.valueOf(cell_tFileInputExcel_1.getBooleanCellValue());
                                    break;
                                default:
                            		temp_row_tFileInputExcel_1[i] = "";
                            }
                            break;
                        default:
                            temp_row_tFileInputExcel_1[i] = "";
                        }
                	}
                	else{
                		temp_row_tFileInputExcel_1[i]="";
                	}

				}else{
					temp_row_tFileInputExcel_1[i]="";
				}
			}
			boolean whetherReject_tFileInputExcel_1 = false;
			row1 = new row1Struct();
			int curColNum_tFileInputExcel_1 = -1;
			String curColName_tFileInputExcel_1 = "";
			try{
							columnIndex_tFileInputExcel_1 = 0;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "tournament_name";

				row1.tournament_name = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.tournament_name = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 1;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "match_id";

				row1.match_id = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row1.match_id = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 2;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "round";

				row1.round = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.round = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 3;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "winner";

				row1.winner = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.winner = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 4;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "total_points_won_t1";

				row1.total_points_won_t1 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.total_points_won_t1 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 5;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "total_points_won_t2";

				row1.total_points_won_t2 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.total_points_won_t2 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 6;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "break_points_converted_t1";

				row1.break_points_converted_t1 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.break_points_converted_t1 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 7;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "break_points_converted_t2";

				row1.break_points_converted_t2 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.break_points_converted_t2 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 8;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "longest_streak_t1";

				row1.longest_streak_t1 = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row1.longest_streak_t1 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 9;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "longest_streak_t2";

				row1.longest_streak_t2 = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row1.longest_streak_t2 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 10;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "aces_t1";

				row1.aces_t1 = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row1.aces_t1 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 11;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "aces_t2";

				row1.aces_t2 = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row1.aces_t2 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 12;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "double_faults_t1";

				row1.double_faults_t1 = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row1.double_faults_t1 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 13;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "double_faults_t2";

				row1.double_faults_t2 = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row1.double_faults_t2 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 14;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "won_on_1st_serve_t1";

				row1.won_on_1st_serve_t1 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.won_on_1st_serve_t1 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 15;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "won_on_1st_serve_t2";

				row1.won_on_1st_serve_t2 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.won_on_1st_serve_t2 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 16;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "won_on_2nd_serve_t1";

				row1.won_on_2nd_serve_t1 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.won_on_2nd_serve_t1 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 17;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "won_on_2nd_serve_t2";

				row1.won_on_2nd_serve_t2 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.won_on_2nd_serve_t2 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 18;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "service_games_t1";

				row1.service_games_t1 = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row1.service_games_t1 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 19;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "service_games_t2";

				row1.service_games_t2 = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row1.service_games_t2 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 20;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "won_on_1st_return_t1";

				row1.won_on_1st_return_t1 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.won_on_1st_return_t1 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 21;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "won_on_1st_return_t2";

				row1.won_on_1st_return_t2 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.won_on_1st_return_t2 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 22;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "won_on_2nd_return_t1";

				row1.won_on_2nd_return_t1 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.won_on_2nd_return_t1 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 23;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "won_on_2nd_return_t2";

				row1.won_on_2nd_return_t2 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.won_on_2nd_return_t2 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 24;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "return_games_t1";

				row1.return_games_t1 = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row1.return_games_t1 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 25;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "return_games_t2";

				row1.return_games_t2 = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row1.return_games_t2 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 26;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "total_won_on_serve_t1";

				row1.total_won_on_serve_t1 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.total_won_on_serve_t1 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 27;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "total_won_on_serve_t2";

				row1.total_won_on_serve_t2 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.total_won_on_serve_t2 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 28;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "total_won_on_return_t1";

				row1.total_won_on_return_t1 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.total_won_on_return_t1 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 29;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "total_won_on_return_t2";

				row1.total_won_on_return_t2 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.total_won_on_return_t2 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 30;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "date";

				row1.date = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.date = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 31;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "team1_player1_name";

				row1.team1_player1_name = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.team1_player1_name = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 32;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "team1_player2_name";

				row1.team1_player2_name = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.team1_player2_name = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 33;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "team2_player1_name";

				row1.team2_player1_name = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.team2_player1_name = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 34;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "team2_player2_name";

				row1.team2_player2_name = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.team2_player2_name = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 35;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "match_info_added";

				row1.match_info_added = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row1.match_info_added = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 36;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "t1_s1";

				row1.t1_s1 = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row1.t1_s1 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 37;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "t2_s1";

				row1.t2_s1 = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row1.t2_s1 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 38;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "t1_s2";

				row1.t1_s2 = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row1.t1_s2 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 39;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "t2_s2";

				row1.t2_s2 = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row1.t2_s2 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 40;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "t1_s3";

				row1.t1_s3 = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row1.t1_s3 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 41;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "t2_s3";

				row1.t2_s3 = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row1.t2_s3 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 42;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "views";

				row1.views = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row1.views = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 43;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "interactions";

				row1.interactions = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row1.interactions = null;
				emptyColumnCount_tFileInputExcel_1++;
			}

				nb_line_tFileInputExcel_1++;
				
			}catch(java.lang.Exception e){
globalMap.put("tFileInputExcel_1_ERROR_MESSAGE",e.getMessage());
			whetherReject_tFileInputExcel_1 = true;
					 System.err.println(e.getMessage());
					 row1 = null;
			}


		



 



/**
 * [tFileInputExcel_1 begin ] stop
 */
	
	/**
	 * [tFileInputExcel_1 main ] start
	 */

	

	
	
	currentComponent="tFileInputExcel_1";

	

 


	tos_count_tFileInputExcel_1++;

/**
 * [tFileInputExcel_1 main ] stop
 */
	
	/**
	 * [tFileInputExcel_1 process_data_begin ] start
	 */

	

	
	
	currentComponent="tFileInputExcel_1";

	

 



/**
 * [tFileInputExcel_1 process_data_begin ] stop
 */
// Start of branch "row1"
if(row1 != null) { 



	
	/**
	 * [tMap_1 main ] start
	 */

	

	
	
	currentComponent="tMap_1";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row1"
						
						);
					}
					

		
		
		boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;
		

        // ###############################
        // # Input tables (lookups)
		  boolean rejectedInnerJoin_tMap_1 = false;
		  boolean mainRowRejected_tMap_1 = false;
            				    								  
		

				///////////////////////////////////////////////
				// Starting Lookup Table "row2" 
				///////////////////////////////////////////////


				
				
                            
 					    boolean forceLooprow2 = false;
       		  	    	
       		  	    	
 							row2Struct row2ObjectFromLookup = null;
                          
		           		  	if(!rejectedInnerJoin_tMap_1) { // G_TM_M_020

								
								hasCasePrimitiveKeyWithNull_tMap_1 = false;
								
                        		    		    row2HashKey.full_date = (row1.date == null || row1.date.trim().isEmpty()) ? null : TalendDate.parseDate("dd/MM/yyyy", row1.date)
 == null ? null : new java.util.Date((row1.date == null || row1.date.trim().isEmpty()) ? null : TalendDate.parseDate("dd/MM/yyyy", row1.date)
.getTime());
                        		    		

								
		                        	row2HashKey.hashCodeDirty = true;
                        		
	  					
	  							
			  					
			  					
	  					
		  							tHash_Lookup_row2.lookup( row2HashKey );

	  							

	  							

 								
								  
								  if(!tHash_Lookup_row2.hasNext()) { // G_TM_M_090

  								
		  				
	  								
			  							rejectedInnerJoin_tMap_1 = true;
	  								
						
									
  									  		
 								
								  
								  } // G_TM_M_090

  								



							} // G_TM_M_020
			           		  	  
							
				           		if(tHash_Lookup_row2 != null && tHash_Lookup_row2.getCount(row2HashKey) > 1) { // G 071
			  							
			  						
									 		
									//System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row2' and it contains more one result from keys :  row2.full_date = '" + row2HashKey.full_date + "'");
								} // G 071
							

							row2Struct row2 = null;
                    		  	 
							   
                    		  	 
	       		  	    	row2Struct fromLookup_row2 = null;
							row2 = row2Default;
										 
							
								 
							
							
								if (tHash_Lookup_row2 !=null && tHash_Lookup_row2.hasNext()) { // G 099
								
							
								
								fromLookup_row2 = tHash_Lookup_row2.next();

							
							
								} // G 099
							
							

							if(fromLookup_row2 != null) {
								row2 = fromLookup_row2;
							}
							
							
							
			  							
								
	                    		  	
		                    
	            	
	           	
	            	
	            	
	            

				///////////////////////////////////////////////
				// Starting Lookup Table "row4" 
				///////////////////////////////////////////////


				
				
                            
 					    boolean forceLooprow4 = false;
       		  	    	
       		  	    	
 							row4Struct row4ObjectFromLookup = null;
                          
		           		  	if(!rejectedInnerJoin_tMap_1) { // G_TM_M_020

								
								hasCasePrimitiveKeyWithNull_tMap_1 = false;
								
                        		    		    row4HashKey.full_name = row1.team1_player1_name ;
                        		    		

								
		                        	row4HashKey.hashCodeDirty = true;
                        		
	  					
	  							
			  					
			  					
	  					
		  							tHash_Lookup_row4.lookup( row4HashKey );

	  							

	  							

 								
								  
								  if(!tHash_Lookup_row4.hasNext()) { // G_TM_M_090

  								
		  				
	  								
			  							rejectedInnerJoin_tMap_1 = true;
	  								
						
									
  									  		
 								
								  
								  } // G_TM_M_090

  								



							} // G_TM_M_020
			           		  	  
							
				           		if(tHash_Lookup_row4 != null && tHash_Lookup_row4.getCount(row4HashKey) > 1) { // G 071
			  							
			  						
									 		
									//System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row4' and it contains more one result from keys :  row4.full_name = '" + row4HashKey.full_name + "'");
								} // G 071
							

							row4Struct row4 = null;
                    		  	 
							   
                    		  	 
	       		  	    	row4Struct fromLookup_row4 = null;
							row4 = row4Default;
										 
							
								 
							
							
								if (tHash_Lookup_row4 !=null && tHash_Lookup_row4.hasNext()) { // G 099
								
							
								
								fromLookup_row4 = tHash_Lookup_row4.next();

							
							
								} // G 099
							
							

							if(fromLookup_row4 != null) {
								row4 = fromLookup_row4;
							}
							
							
							
			  							
								
	                    		  	
		                    
	            	
	           	
	            	
	            	
	            

				///////////////////////////////////////////////
				// Starting Lookup Table "row5" 
				///////////////////////////////////////////////


				
				
                            
 					    boolean forceLooprow5 = false;
       		  	    	
       		  	    	
 							row5Struct row5ObjectFromLookup = null;
                          
		           		  	if(!rejectedInnerJoin_tMap_1) { // G_TM_M_020

								
								hasCasePrimitiveKeyWithNull_tMap_1 = false;
								
                        		    		    row5HashKey.full_name = row1.team1_player2_name ;
                        		    		

								
		                        	row5HashKey.hashCodeDirty = true;
                        		
	  					
	  							
			  					
			  					
	  					
		  							tHash_Lookup_row5.lookup( row5HashKey );

	  							

	  							

 								
								  
								  if(!tHash_Lookup_row5.hasNext()) { // G_TM_M_090

  								
		  				
	  								
			  							rejectedInnerJoin_tMap_1 = true;
	  								
						
									
  									  		
 								
								  
								  } // G_TM_M_090

  								



							} // G_TM_M_020
			           		  	  
							
				           		if(tHash_Lookup_row5 != null && tHash_Lookup_row5.getCount(row5HashKey) > 1) { // G 071
			  							
			  						
									 		
									//System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row5' and it contains more one result from keys :  row5.full_name = '" + row5HashKey.full_name + "'");
								} // G 071
							

							row5Struct row5 = null;
                    		  	 
							   
                    		  	 
	       		  	    	row5Struct fromLookup_row5 = null;
							row5 = row5Default;
										 
							
								 
							
							
								if (tHash_Lookup_row5 !=null && tHash_Lookup_row5.hasNext()) { // G 099
								
							
								
								fromLookup_row5 = tHash_Lookup_row5.next();

							
							
								} // G 099
							
							

							if(fromLookup_row5 != null) {
								row5 = fromLookup_row5;
							}
							
							
							
			  							
								
	                    		  	
		                    
	            	
	           	
	            	
	            	
	            

				///////////////////////////////////////////////
				// Starting Lookup Table "row6" 
				///////////////////////////////////////////////


				
				
                            
 					    boolean forceLooprow6 = false;
       		  	    	
       		  	    	
 							row6Struct row6ObjectFromLookup = null;
                          
		           		  	if(!rejectedInnerJoin_tMap_1) { // G_TM_M_020

								
								hasCasePrimitiveKeyWithNull_tMap_1 = false;
								
                        		    		    row6HashKey.full_name = row1.team2_player1_name ;
                        		    		

								
		                        	row6HashKey.hashCodeDirty = true;
                        		
	  					
	  							
			  					
			  					
	  					
		  							tHash_Lookup_row6.lookup( row6HashKey );

	  							

	  							

 								
								  
								  if(!tHash_Lookup_row6.hasNext()) { // G_TM_M_090

  								
		  				
	  								
			  							rejectedInnerJoin_tMap_1 = true;
	  								
						
									
  									  		
 								
								  
								  } // G_TM_M_090

  								



							} // G_TM_M_020
			           		  	  
							
				           		if(tHash_Lookup_row6 != null && tHash_Lookup_row6.getCount(row6HashKey) > 1) { // G 071
			  							
			  						
									 		
									//System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row6' and it contains more one result from keys :  row6.full_name = '" + row6HashKey.full_name + "'");
								} // G 071
							

							row6Struct row6 = null;
                    		  	 
							   
                    		  	 
	       		  	    	row6Struct fromLookup_row6 = null;
							row6 = row6Default;
										 
							
								 
							
							
								if (tHash_Lookup_row6 !=null && tHash_Lookup_row6.hasNext()) { // G 099
								
							
								
								fromLookup_row6 = tHash_Lookup_row6.next();

							
							
								} // G 099
							
							

							if(fromLookup_row6 != null) {
								row6 = fromLookup_row6;
							}
							
							
							
			  							
								
	                    		  	
		                    
	            	
	           	
	            	
	            	
	            

				///////////////////////////////////////////////
				// Starting Lookup Table "row7" 
				///////////////////////////////////////////////


				
				
                            
 					    boolean forceLooprow7 = false;
       		  	    	
       		  	    	
 							row7Struct row7ObjectFromLookup = null;
                          
		           		  	if(!rejectedInnerJoin_tMap_1) { // G_TM_M_020

								
								hasCasePrimitiveKeyWithNull_tMap_1 = false;
								
                        		    		    row7HashKey.full_name = row1.team2_player2_name ;
                        		    		

								
		                        	row7HashKey.hashCodeDirty = true;
                        		
	  					
	  							
			  					
			  					
	  					
		  							tHash_Lookup_row7.lookup( row7HashKey );

	  							

	  							

 								
								  
								  if(!tHash_Lookup_row7.hasNext()) { // G_TM_M_090

  								
		  				
	  								
			  							rejectedInnerJoin_tMap_1 = true;
	  								
						
									
  									  		
 								
								  
								  } // G_TM_M_090

  								



							} // G_TM_M_020
			           		  	  
							
				           		if(tHash_Lookup_row7 != null && tHash_Lookup_row7.getCount(row7HashKey) > 1) { // G 071
			  							
			  						
									 		
									//System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row7' and it contains more one result from keys :  row7.full_name = '" + row7HashKey.full_name + "'");
								} // G 071
							

							row7Struct row7 = null;
                    		  	 
							   
                    		  	 
	       		  	    	row7Struct fromLookup_row7 = null;
							row7 = row7Default;
										 
							
								 
							
							
								if (tHash_Lookup_row7 !=null && tHash_Lookup_row7.hasNext()) { // G 099
								
							
								
								fromLookup_row7 = tHash_Lookup_row7.next();

							
							
								} // G 099
							
							

							if(fromLookup_row7 != null) {
								row7 = fromLookup_row7;
							}
							
							
							
			  							
								
	                    		  	
		                    
	            	
	           	
	            	
	            	
	            

				///////////////////////////////////////////////
				// Starting Lookup Table "row3" 
				///////////////////////////////////////////////


				
				
                            
 					    boolean forceLooprow3 = false;
       		  	    	
       		  	    	
 							row3Struct row3ObjectFromLookup = null;
                          
		           		  	if(!rejectedInnerJoin_tMap_1) { // G_TM_M_020

								
								hasCasePrimitiveKeyWithNull_tMap_1 = false;
								
                        		    		    row3HashKey.tournament_name = row1.tournament_name ;
                        		    		

								
		                        	row3HashKey.hashCodeDirty = true;
                        		
	  					
	  							
			  					
			  					
	  					
		  							tHash_Lookup_row3.lookup( row3HashKey );

	  							

	  							

 								
								  
								  if(!tHash_Lookup_row3.hasNext()) { // G_TM_M_090

  								
		  				
	  								
			  							rejectedInnerJoin_tMap_1 = true;
	  								
						
									
  									  		
 								
								  
								  } // G_TM_M_090

  								



							} // G_TM_M_020
			           		  	  
							
				           		if(tHash_Lookup_row3 != null && tHash_Lookup_row3.getCount(row3HashKey) > 1) { // G 071
			  							
			  						
									 		
									//System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row3' and it contains more one result from keys :  row3.tournament_name = '" + row3HashKey.tournament_name + "'");
								} // G 071
							

							row3Struct row3 = null;
                    		  	 
							   
                    		  	 
	       		  	    	row3Struct fromLookup_row3 = null;
							row3 = row3Default;
										 
							
								 
							
							
								if (tHash_Lookup_row3 !=null && tHash_Lookup_row3.hasNext()) { // G 099
								
							
								
								fromLookup_row3 = tHash_Lookup_row3.next();

							
							
								} // G 099
							
							

							if(fromLookup_row3 != null) {
								row3 = fromLookup_row3;
							}
							
							
							
			  							
								
	                    		  	
		                    
	            	
	            	
	            // ###############################
        { // start of Var scope
        
	        // ###############################
        	// # Vars tables
        
Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
        // ###############################
        // # Output tables

fact = null;

if(!rejectedInnerJoin_tMap_1 ) {

// # Output table : 'fact'
fact_tmp.date_key = row2.date_key ;
fact_tmp.tournament_id = row3.tournament_id;
fact_tmp.t1_p1_id = row4.player_id;
fact_tmp.t1_p2_id = row5.player_id;
fact_tmp.t2_p1_id = row6.player_id;
fact_tmp.t2_p2_id = row7.player_id;
fact_tmp.views = row1.views;
fact_tmp.interactions = row1.interactions ;
fact_tmp.winners = row1.winner.equals("team_1") ? 1 : (row1.winner.equals("team_2") ? 2 : 0) ;
fact_tmp.won_on_1st_serve_t2 = (row1.won_on_1st_serve_t2 == null || row1.won_on_1st_serve_t2.equals("")) ? 0 : 
Integer.parseInt(row1.won_on_1st_serve_t2.replace("%", "").trim()) ;
fact_tmp.won_on_2nd_serve_t2 = (row1.won_on_2nd_serve_t2 == null || row1.won_on_2nd_serve_t2.equals("")) ? 0 : 
Integer.parseInt(row1.won_on_2nd_serve_t2.replace("%", "").trim()) ;
fact_tmp.break_points_saved_t1 = (row1.break_points_converted_t1 == null || row1.break_points_converted_t1.isEmpty()) ? 0 : Integer.parseInt(row1.break_points_converted_t1.replace("%", "").trim())
;
fact_tmp.round = row1.round ;
fact_tmp.aces_t1 = row1.aces_t1 ;
fact_tmp.aces_t2 = row1.aces_t2 ;
fact_tmp.double_faults_t1 = row1.double_faults_t1 == null ? 0 : row1.double_faults_t1;
fact_tmp.double_faults_t2 = row1.double_faults_t2 == null ? 0 : row1.double_faults_t2;
fact_tmp.won_on_1st_serve_t1 = (row1.won_on_1st_serve_t1 == null || row1.won_on_1st_serve_t1.isEmpty()) ? 0 : Integer.parseInt(row1.won_on_1st_serve_t1.replace("%", "").trim()) ;
fact_tmp.won_on_2nd_serve_t1 = (row1.won_on_2nd_serve_t1 == null || row1.won_on_2nd_serve_t1.isEmpty()) ? 0 : Integer.parseInt(row1.won_on_2nd_serve_t1.replace("%", "").trim()) ;
fact_tmp.service_games_t1 = row1.service_games_t1 == null ? 0 : row1.service_games_t1 ;
fact_tmp.service_games_t2 = row1.service_games_t2 == null ? 0 : row1.service_games_t2 ;
fact_tmp.won_on_1st_return_t1 = (row1.won_on_1st_return_t1 == null || row1.won_on_1st_return_t1.isEmpty()) ? 0 : Integer.parseInt(row1.won_on_1st_return_t1.replace("%", "").trim()) ;
fact_tmp.won_on_1st_return_t2 = (row1.won_on_2nd_return_t2 == null || row1.won_on_2nd_return_t2.isEmpty()) ? 0 : Integer.parseInt(row1.won_on_2nd_return_t2.replace("%", "").trim()) ;
fact_tmp.won_on_2nd_return_t1 = (row1.won_on_2nd_return_t1 == null || row1.won_on_2nd_return_t1.isEmpty()) ? 0 : Integer.parseInt(row1.won_on_2nd_return_t1.replace("%", "").trim()) ;
fact_tmp.won_on_2nd_return_t2 = (row1.won_on_2nd_return_t2 == null || row1.won_on_2nd_return_t2.isEmpty()) ? 0 : Integer.parseInt(row1.won_on_2nd_return_t2.replace("%", "").trim()) ;
fact_tmp.return_games_t1 = row1.return_games_t1 == null ? 0 : row1.return_games_t1 ;
fact_tmp.return_games_t2 = row1.return_games_t2 == null ? 0 : row1.return_games_t2 ;
fact_tmp.total_won_on_serve_t1 = (row1.total_won_on_serve_t1 == null || row1.total_won_on_serve_t1.isEmpty()) ? 0 : Integer.parseInt(row1.total_won_on_serve_t1.replace("%", "").trim()) ;
fact_tmp.total_won_on_serve_t2 = (row1.total_won_on_serve_t2 == null || row1.total_won_on_serve_t2.isEmpty()) ? 0 : Integer.parseInt(row1.total_won_on_serve_t2.replace("%", "").trim()) ;
fact_tmp.total_won_on_return_t1 = (row1.total_won_on_return_t1 == null || row1.total_won_on_return_t1.isEmpty()) ? 0 : Integer.parseInt(row1.total_won_on_return_t1.replace("%", "").trim()) ;
fact_tmp.total_won_on_return_t2 = (row1.total_won_on_return_t2 == null || row1.total_won_on_return_t2.isEmpty()) ? 0 : Integer.parseInt(row1.total_won_on_return_t2.replace("%", "").trim()) ;
fact_tmp.match_info_added = row1.match_info_added == null ? 0 : row1.match_info_added ;
fact_tmp.t1_s1 = row1.t1_s1 ;
fact_tmp.t2_s1 = row1.t2_s1 ;
fact_tmp.t1_s2 = row1.t1_s2 ;
fact_tmp.t2_s2 = row1.t2_s2 ;
fact_tmp.t1_s3 = row1.t1_s3 ;
fact_tmp.t2_s3 = row1.t2_s3 ;
fact = fact_tmp;
}  // closing inner join bracket (2)
// ###############################

} // end of Var scope

rejectedInnerJoin_tMap_1 = false;










 


	tos_count_tMap_1++;

/**
 * [tMap_1 main ] stop
 */
	
	/**
	 * [tMap_1 process_data_begin ] start
	 */

	

	
	
	currentComponent="tMap_1";

	

 



/**
 * [tMap_1 process_data_begin ] stop
 */
// Start of branch "fact"
if(fact != null) { 



	
	/**
	 * [tDBOutput_1 main ] start
	 */

	

	
	
	currentComponent="tDBOutput_1";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"fact"
						
						);
					}
					



        whetherReject_tDBOutput_1 = false;
                    if(fact.date_key == null) {
pstmt_tDBOutput_1.setNull(1, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(1, fact.date_key);
}

                    if(fact.tournament_id == null) {
pstmt_tDBOutput_1.setNull(2, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(2, fact.tournament_id);
}

                    if(fact.t1_p1_id == null) {
pstmt_tDBOutput_1.setNull(3, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(3, fact.t1_p1_id);
}

                    if(fact.t1_p2_id == null) {
pstmt_tDBOutput_1.setNull(4, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(4, fact.t1_p2_id);
}

                    if(fact.t2_p1_id == null) {
pstmt_tDBOutput_1.setNull(5, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(5, fact.t2_p1_id);
}

                    if(fact.t2_p2_id == null) {
pstmt_tDBOutput_1.setNull(6, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(6, fact.t2_p2_id);
}

                    if(fact.views == null) {
pstmt_tDBOutput_1.setNull(7, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(7, fact.views);
}

                    if(fact.interactions == null) {
pstmt_tDBOutput_1.setNull(8, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(8, fact.interactions);
}

                    if(fact.winners == null) {
pstmt_tDBOutput_1.setNull(9, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(9, fact.winners);
}

                    if(fact.won_on_1st_serve_t2 == null) {
pstmt_tDBOutput_1.setNull(10, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(10, fact.won_on_1st_serve_t2);
}

                    if(fact.won_on_2nd_serve_t2 == null) {
pstmt_tDBOutput_1.setNull(11, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(11, fact.won_on_2nd_serve_t2);
}

                    if(fact.break_points_saved_t1 == null) {
pstmt_tDBOutput_1.setNull(12, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(12, fact.break_points_saved_t1);
}

                    if(fact.round == null) {
pstmt_tDBOutput_1.setNull(13, java.sql.Types.VARCHAR);
} else {pstmt_tDBOutput_1.setString(13, fact.round);
}

                    if(fact.aces_t1 == null) {
pstmt_tDBOutput_1.setNull(14, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(14, fact.aces_t1);
}

                    if(fact.aces_t2 == null) {
pstmt_tDBOutput_1.setNull(15, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(15, fact.aces_t2);
}

                    if(fact.double_faults_t1 == null) {
pstmt_tDBOutput_1.setNull(16, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(16, fact.double_faults_t1);
}

                    if(fact.double_faults_t2 == null) {
pstmt_tDBOutput_1.setNull(17, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(17, fact.double_faults_t2);
}

                    if(fact.won_on_1st_serve_t1 == null) {
pstmt_tDBOutput_1.setNull(18, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(18, fact.won_on_1st_serve_t1);
}

                    if(fact.won_on_2nd_serve_t1 == null) {
pstmt_tDBOutput_1.setNull(19, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(19, fact.won_on_2nd_serve_t1);
}

                    if(fact.service_games_t1 == null) {
pstmt_tDBOutput_1.setNull(20, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(20, fact.service_games_t1);
}

                    if(fact.service_games_t2 == null) {
pstmt_tDBOutput_1.setNull(21, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(21, fact.service_games_t2);
}

                    if(fact.won_on_1st_return_t1 == null) {
pstmt_tDBOutput_1.setNull(22, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(22, fact.won_on_1st_return_t1);
}

                    if(fact.won_on_1st_return_t2 == null) {
pstmt_tDBOutput_1.setNull(23, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(23, fact.won_on_1st_return_t2);
}

                    if(fact.won_on_2nd_return_t1 == null) {
pstmt_tDBOutput_1.setNull(24, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(24, fact.won_on_2nd_return_t1);
}

                    if(fact.won_on_2nd_return_t2 == null) {
pstmt_tDBOutput_1.setNull(25, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(25, fact.won_on_2nd_return_t2);
}

                    if(fact.return_games_t1 == null) {
pstmt_tDBOutput_1.setNull(26, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(26, fact.return_games_t1);
}

                    if(fact.return_games_t2 == null) {
pstmt_tDBOutput_1.setNull(27, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(27, fact.return_games_t2);
}

                    if(fact.total_won_on_serve_t1 == null) {
pstmt_tDBOutput_1.setNull(28, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(28, fact.total_won_on_serve_t1);
}

                    if(fact.total_won_on_serve_t2 == null) {
pstmt_tDBOutput_1.setNull(29, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(29, fact.total_won_on_serve_t2);
}

                    if(fact.total_won_on_return_t1 == null) {
pstmt_tDBOutput_1.setNull(30, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(30, fact.total_won_on_return_t1);
}

                    if(fact.total_won_on_return_t2 == null) {
pstmt_tDBOutput_1.setNull(31, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(31, fact.total_won_on_return_t2);
}

                    if(fact.match_info_added == null) {
pstmt_tDBOutput_1.setNull(32, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(32, fact.match_info_added);
}

                    if(fact.t1_s1 == null) {
pstmt_tDBOutput_1.setNull(33, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(33, fact.t1_s1);
}

                    if(fact.t2_s1 == null) {
pstmt_tDBOutput_1.setNull(34, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(34, fact.t2_s1);
}

                    if(fact.t1_s2 == null) {
pstmt_tDBOutput_1.setNull(35, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(35, fact.t1_s2);
}

                    if(fact.t2_s2 == null) {
pstmt_tDBOutput_1.setNull(36, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(36, fact.t2_s2);
}

                    if(fact.t1_s3 == null) {
pstmt_tDBOutput_1.setNull(37, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(37, fact.t1_s3);
}

                    if(fact.t2_s3 == null) {
pstmt_tDBOutput_1.setNull(38, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(38, fact.t2_s3);
}


        		pstmt_tDBOutput_1.addBatch();
        		nb_line_tDBOutput_1++;
        		
    		 
    		  batchSizeCounter_tDBOutput_1++;
    		
            	//////////batch execute by batch size///////
            	class LimitBytesHelper_tDBOutput_1{
            		public int limitBytePart1(int counter,java.sql.PreparedStatement pstmt_tDBOutput_1) throws Exception {
                try {
						
						for(int countEach_tDBOutput_1: pstmt_tDBOutput_1.executeBatch()) {
							if(countEach_tDBOutput_1 == -2 || countEach_tDBOutput_1 == -3) {
								break;
							}
							counter += countEach_tDBOutput_1;
						}
						
                }catch (java.sql.BatchUpdateException e){
globalMap.put("tDBOutput_1_ERROR_MESSAGE",e.getMessage());
                	
                	int countSum_tDBOutput_1 = 0;
					for(int countEach_tDBOutput_1: e.getUpdateCounts()) {
						counter += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
					}
				
            	    	
                		System.err.println(e.getMessage());
                	
               			 }
    				return counter;
            	}
            	
            	public int limitBytePart2(int counter,java.sql.PreparedStatement pstmt_tDBOutput_1) throws Exception {
                try {
                		
						for(int countEach_tDBOutput_1: pstmt_tDBOutput_1.executeBatch()) {
							if(countEach_tDBOutput_1 == -2 || countEach_tDBOutput_1 == -3) {
								break;
							}
							counter += countEach_tDBOutput_1;
						}
						
                }catch (java.sql.BatchUpdateException e){
globalMap.put("tDBOutput_1_ERROR_MESSAGE",e.getMessage());
                	
                	
					for(int countEach_tDBOutput_1: e.getUpdateCounts()) {
						counter += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
					}
					
            	    	
                        System.err.println(e.getMessage());
                	
                		}	
                	return counter;	
            	}
            }
    		if ((batchSize_tDBOutput_1 > 0) && (batchSize_tDBOutput_1 <= batchSizeCounter_tDBOutput_1)) {
    		
    		            
            	    		insertedCount_tDBOutput_1 = new LimitBytesHelper_tDBOutput_1().limitBytePart1(insertedCount_tDBOutput_1,pstmt_tDBOutput_1);
            	    		rowsToCommitCount_tDBOutput_1 = insertedCount_tDBOutput_1;
            	    	
    			
			    batchSizeCounter_tDBOutput_1 = 0;
			}
    		

    	////////////commit every////////////
    			
    		    commitCounter_tDBOutput_1++;
                if(commitEvery_tDBOutput_1 <= commitCounter_tDBOutput_1) {
                if ((batchSize_tDBOutput_1 > 0) && (batchSizeCounter_tDBOutput_1 > 0)) {
    		            
            	    		insertedCount_tDBOutput_1 = new LimitBytesHelper_tDBOutput_1().limitBytePart1(insertedCount_tDBOutput_1,pstmt_tDBOutput_1);
            	    	
            	batchSizeCounter_tDBOutput_1 = 0;
            	}
                if(rowsToCommitCount_tDBOutput_1 != 0){
                	
                }
                conn_tDBOutput_1.commit();
                if(rowsToCommitCount_tDBOutput_1 != 0){
                	
                	rowsToCommitCount_tDBOutput_1 = 0;	
                }
                commitCounter_tDBOutput_1=0;
                }

 


	tos_count_tDBOutput_1++;

/**
 * [tDBOutput_1 main ] stop
 */
	
	/**
	 * [tDBOutput_1 process_data_begin ] start
	 */

	

	
	
	currentComponent="tDBOutput_1";

	

 



/**
 * [tDBOutput_1 process_data_begin ] stop
 */
	
	/**
	 * [tDBOutput_1 process_data_end ] start
	 */

	

	
	
	currentComponent="tDBOutput_1";

	

 



/**
 * [tDBOutput_1 process_data_end ] stop
 */

} // End of branch "fact"




	
	/**
	 * [tMap_1 process_data_end ] start
	 */

	

	
	
	currentComponent="tMap_1";

	

 



/**
 * [tMap_1 process_data_end ] stop
 */

} // End of branch "row1"




	
	/**
	 * [tFileInputExcel_1 process_data_end ] start
	 */

	

	
	
	currentComponent="tFileInputExcel_1";

	

 



/**
 * [tFileInputExcel_1 process_data_end ] stop
 */
	
	/**
	 * [tFileInputExcel_1 end ] start
	 */

	

	
	
	currentComponent="tFileInputExcel_1";

	

			}
			
			
			
			globalMap.put("tFileInputExcel_1_NB_LINE",nb_line_tFileInputExcel_1);
			
				}
			
		} finally { 
				
  				if(!(source_tFileInputExcel_1 instanceof java.io.InputStream)){
  					workbook_tFileInputExcel_1.getPackage().revert();
  				}
				
		}	
		

 

ok_Hash.put("tFileInputExcel_1", true);
end_Hash.put("tFileInputExcel_1", System.currentTimeMillis());




/**
 * [tFileInputExcel_1 end ] stop
 */

	
	/**
	 * [tMap_1 end ] start
	 */

	

	
	
	currentComponent="tMap_1";

	


// ###############################
// # Lookup hashes releasing
					if(tHash_Lookup_row2 != null) {
						tHash_Lookup_row2.endGet();
					}
					globalMap.remove( "tHash_Lookup_row2" );

					
					
				
					if(tHash_Lookup_row4 != null) {
						tHash_Lookup_row4.endGet();
					}
					globalMap.remove( "tHash_Lookup_row4" );

					
					
				
					if(tHash_Lookup_row5 != null) {
						tHash_Lookup_row5.endGet();
					}
					globalMap.remove( "tHash_Lookup_row5" );

					
					
				
					if(tHash_Lookup_row6 != null) {
						tHash_Lookup_row6.endGet();
					}
					globalMap.remove( "tHash_Lookup_row6" );

					
					
				
					if(tHash_Lookup_row7 != null) {
						tHash_Lookup_row7.endGet();
					}
					globalMap.remove( "tHash_Lookup_row7" );

					
					
				
					if(tHash_Lookup_row3 != null) {
						tHash_Lookup_row3.endGet();
					}
					globalMap.remove( "tHash_Lookup_row3" );

					
					
				
// ###############################      





				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row1");
			  	}
			  	
 

ok_Hash.put("tMap_1", true);
end_Hash.put("tMap_1", System.currentTimeMillis());




/**
 * [tMap_1 end ] stop
 */

	
	/**
	 * [tDBOutput_1 end ] start
	 */

	

	
	
	currentComponent="tDBOutput_1";

	



                try {
						int countSum_tDBOutput_1 = 0;
						if (pstmt_tDBOutput_1 != null && batchSizeCounter_tDBOutput_1 > 0) {
							
							for(int countEach_tDBOutput_1: pstmt_tDBOutput_1.executeBatch()) {
								if(countEach_tDBOutput_1 == -2 || countEach_tDBOutput_1 == -3) {
									break;
								}
								countSum_tDBOutput_1 += countEach_tDBOutput_1;
							}
							rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
							
						}
            	    	
            	    		insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
            	    	
                }catch (java.sql.BatchUpdateException e){
globalMap.put("tDBOutput_1_ERROR_MESSAGE",e.getMessage());
                	
                	int countSum_tDBOutput_1 = 0;
					for(int countEach_tDBOutput_1: e.getUpdateCounts()) {
						countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
					}
					rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
					
            	    		insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
            	    	
                		System.err.println(e.getMessage());
                	
            	}
        if(pstmt_tDBOutput_1 != null) {
			
				pstmt_tDBOutput_1.close();
				resourceMap.remove("pstmt_tDBOutput_1");
			
        }
    resourceMap.put("statementClosed_tDBOutput_1", true);
            if(rowsToCommitCount_tDBOutput_1 != 0){
            	
            }
            conn_tDBOutput_1.commit();
            if(rowsToCommitCount_tDBOutput_1 != 0){
            	
            	rowsToCommitCount_tDBOutput_1 = 0;
            }
            commitCounter_tDBOutput_1 = 0;
        conn_tDBOutput_1 .close();
        resourceMap.put("finish_tDBOutput_1", true);

	nb_line_deleted_tDBOutput_1=nb_line_deleted_tDBOutput_1+ deletedCount_tDBOutput_1;
	nb_line_update_tDBOutput_1=nb_line_update_tDBOutput_1 + updatedCount_tDBOutput_1;
	nb_line_inserted_tDBOutput_1=nb_line_inserted_tDBOutput_1 + insertedCount_tDBOutput_1;
	nb_line_rejected_tDBOutput_1=nb_line_rejected_tDBOutput_1 + rejectedCount_tDBOutput_1;
	
        globalMap.put("tDBOutput_1_NB_LINE",nb_line_tDBOutput_1);
        globalMap.put("tDBOutput_1_NB_LINE_UPDATED",nb_line_update_tDBOutput_1);
        globalMap.put("tDBOutput_1_NB_LINE_INSERTED",nb_line_inserted_tDBOutput_1);
        globalMap.put("tDBOutput_1_NB_LINE_DELETED",nb_line_deleted_tDBOutput_1);
        globalMap.put("tDBOutput_1_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_1);
    

	

				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"fact");
			  	}
			  	
 

ok_Hash.put("tDBOutput_1", true);
end_Hash.put("tDBOutput_1", System.currentTimeMillis());




/**
 * [tDBOutput_1 end ] stop
 */






				}//end the resume

				



	
			}catch(java.lang.Exception e){	
				
				TalendException te = new TalendException(e, currentComponent, globalMap);
				
				throw te;
			}catch(java.lang.Error error){	
				
					runStat.stopThreadStat();
				
				throw error;
			}finally{
				
					     			//free memory for "tMap_1"
					     			globalMap.remove("tHash_Lookup_row2"); 
				     			
					     			//free memory for "tMap_1"
					     			globalMap.remove("tHash_Lookup_row4"); 
				     			
					     			//free memory for "tMap_1"
					     			globalMap.remove("tHash_Lookup_row5"); 
				     			
					     			//free memory for "tMap_1"
					     			globalMap.remove("tHash_Lookup_row6"); 
				     			
					     			//free memory for "tMap_1"
					     			globalMap.remove("tHash_Lookup_row7"); 
				     			
					     			//free memory for "tMap_1"
					     			globalMap.remove("tHash_Lookup_row3"); 
				     			
				try{
					
	
	/**
	 * [tFileInputExcel_1 finally ] start
	 */

	

	
	
	currentComponent="tFileInputExcel_1";

	

 



/**
 * [tFileInputExcel_1 finally ] stop
 */

	
	/**
	 * [tMap_1 finally ] start
	 */

	

	
	
	currentComponent="tMap_1";

	

 



/**
 * [tMap_1 finally ] stop
 */

	
	/**
	 * [tDBOutput_1 finally ] start
	 */

	

	
	
	currentComponent="tDBOutput_1";

	



    try {
    if (resourceMap.get("statementClosed_tDBOutput_1") == null) {
                java.sql.PreparedStatement pstmtToClose_tDBOutput_1 = null;
                if ((pstmtToClose_tDBOutput_1 = (java.sql.PreparedStatement) resourceMap.remove("pstmt_tDBOutput_1")) != null) {
                    pstmtToClose_tDBOutput_1.close();
                }
    }
    } finally {
        if(resourceMap.get("finish_tDBOutput_1") == null){
            java.sql.Connection ctn_tDBOutput_1 = null;
            if((ctn_tDBOutput_1 = (java.sql.Connection)resourceMap.get("conn_tDBOutput_1")) != null){
                try {
                    ctn_tDBOutput_1.close();
                } catch (java.sql.SQLException sqlEx_tDBOutput_1) {
                    String errorMessage_tDBOutput_1 = "failed to close the connection in tDBOutput_1 :" + sqlEx_tDBOutput_1.getMessage();
                    System.err.println(errorMessage_tDBOutput_1);
                }
            }
        }
    }
 



/**
 * [tDBOutput_1 finally ] stop
 */






				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", 1);
	}
	


public static class row2Struct implements routines.system.IPersistableComparableLookupRow<row2Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_FACT_MATCHS = new byte[0];
    static byte[] commonByteArray_DW_PADEL_FACT_MATCHS = new byte[0];
	protected static final int DEFAULT_HASHCODE = 1;
    protected static final int PRIME = 31;
    protected int hashCode = DEFAULT_HASHCODE;
    public boolean hashCodeDirty = true;

    public String loopKey;



	
			    public Integer date_key;

				public Integer getDate_key () {
					return this.date_key;
				}
				
			    public java.util.Date full_date;

				public java.util.Date getFull_date () {
					return this.full_date;
				}
				
			    public Integer annee;

				public Integer getAnnee () {
					return this.annee;
				}
				
			    public Integer mois;

				public Integer getMois () {
					return this.mois;
				}
				
			    public String nom_mois;

				public String getNom_mois () {
					return this.nom_mois;
				}
				
			    public String jour_semaine;

				public String getJour_semaine () {
					return this.jour_semaine;
				}
				
			    public Integer trimestre;

				public Integer getTrimestre () {
					return this.trimestre;
				}
				


	@Override
	public int hashCode() {
		if (this.hashCodeDirty) {
			final int prime = PRIME;
			int result = DEFAULT_HASHCODE;
	
						result = prime * result + ((this.full_date == null) ? 0 : this.full_date.hashCode());
					
    		this.hashCode = result;
    		this.hashCodeDirty = false;
		}
		return this.hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final row2Struct other = (row2Struct) obj;
		
						if (this.full_date == null) {
							if (other.full_date != null)
								return false;
						
						} else if (!this.full_date.equals(other.full_date))
						
							return false;
					

		return true;
    }

	public void copyDataTo(row2Struct other) {

		other.date_key = this.date_key;
	            other.full_date = this.full_date;
	            other.annee = this.annee;
	            other.mois = this.mois;
	            other.nom_mois = this.nom_mois;
	            other.jour_semaine = this.jour_semaine;
	            other.trimestre = this.trimestre;
	            
	}

	public void copyKeysDataTo(row2Struct other) {

		other.full_date = this.full_date;
	            	
	}



	private Integer readInteger(DataInputStream dis, ObjectInputStream ois) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
			intReturn = dis.readInt();
		}
		return intReturn;
	}
	
	private Integer readInteger(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		Integer intReturn;
        int length = 0;
        length = unmarshaller.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
			intReturn = unmarshaller.readInt();
		}
		return intReturn;
	}

	private void writeInteger(Integer intNum, DataOutputStream dos, ObjectOutputStream oos) throws IOException{
		if(intNum == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeInt(intNum);
    	}
	}
	
	private void writeInteger(Integer intNum, DataOutputStream dos,org.jboss.marshalling.Marshaller marshaller ) throws IOException{
		if(intNum == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeInt(intNum);
    	}
	}

	private java.util.Date readDate(ObjectInputStream dis) throws IOException{
		java.util.Date dateReturn = null;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(dis.readLong());
		}
		return dateReturn;
	}
	
	private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		java.util.Date dateReturn = null;
        int length = 0;
        length = unmarshaller.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(unmarshaller.readLong());
		}
		return dateReturn;
	}

    private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException{
		if(date1 == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeLong(date1.getTime());
    	}
    }
    
    private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(date1 == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeLong(date1.getTime());
    	}
    }
	
	private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			byte[] byteArray = new byte[length];
			dis.read(byteArray);
			strReturn = new String(byteArray, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			byte[] byteArray = new byte[length];
			unmarshaller.read(byteArray);
			strReturn = new String(byteArray, utf8Charset);
		}
		return strReturn;
	}
	
	private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
	}

	private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
	}

    public void readKeysData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_MATCHS) {

        	try {

        		int length = 0;
		
					this.full_date = readDate(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_MATCHS) {

        	try {

        		int length = 0;
		
					this.full_date = readDate(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeKeysData(ObjectOutputStream dos) {
        try {

		
					// java.util.Date
				
						writeDate(this.full_date,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// java.util.Date
				
						writeDate(this.full_date,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }



    /**
     * Fill Values data by reading ObjectInputStream.
     */
    public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
        try {

			int length = 0;
		
						this.date_key = readInteger(dis,ois);
					
						this.annee = readInteger(dis,ois);
					
						this.mois = readInteger(dis,ois);
					
						this.nom_mois = readString(dis,ois);
					
						this.jour_semaine = readString(dis,ois);
					
						this.trimestre = readInteger(dis,ois);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }
    
    public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
        try {
			int length = 0;
		
						this.date_key = readInteger(dis,objectIn);
					
						this.annee = readInteger(dis,objectIn);
					
						this.mois = readInteger(dis,objectIn);
					
						this.nom_mois = readString(dis,objectIn);
					
						this.jour_semaine = readString(dis,objectIn);
					
						this.trimestre = readInteger(dis,objectIn);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }

    /**
     * Return a byte array which represents Values data.
     */
    public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
        try {

		
					writeInteger(this.date_key, dos, oos);
					
					writeInteger(this.annee, dos, oos);
					
					writeInteger(this.mois, dos, oos);
					
						writeString(this.nom_mois, dos, oos);
					
						writeString(this.jour_semaine, dos, oos);
					
					writeInteger(this.trimestre, dos, oos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}

    }
    
    public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut){
                try {

		
					writeInteger(this.date_key, dos, objectOut);
					
					writeInteger(this.annee, dos, objectOut);
					
					writeInteger(this.mois, dos, objectOut);
					
						writeString(this.nom_mois, dos, objectOut);
					
						writeString(this.jour_semaine, dos, objectOut);
					
					writeInteger(this.trimestre, dos, objectOut);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}
    }


    
    public boolean supportMarshaller(){
        return true;
    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("date_key="+String.valueOf(date_key));
		sb.append(",full_date="+String.valueOf(full_date));
		sb.append(",annee="+String.valueOf(annee));
		sb.append(",mois="+String.valueOf(mois));
		sb.append(",nom_mois="+nom_mois);
		sb.append(",jour_semaine="+jour_semaine);
		sb.append(",trimestre="+String.valueOf(trimestre));
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(row2Struct other) {

		int returnValue = -1;
		
						returnValue = checkNullsAndCompare(this.full_date, other.full_date);
						if(returnValue != 0) {
							return returnValue;
						}

					
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}
public void tDBInput_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tDBInput_2_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { //start the resume
				globalResumeTicket = true;



		row2Struct row2 = new row2Struct();




	
	/**
	 * [tAdvancedHash_row2 begin ] start
	 */

	

	
		
		ok_Hash.put("tAdvancedHash_row2", false);
		start_Hash.put("tAdvancedHash_row2", System.currentTimeMillis());
		
	
	currentComponent="tAdvancedHash_row2";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row2");
					}
				
		int tos_count_tAdvancedHash_row2 = 0;
		

			   		// connection name:row2
			   		// source node:tDBInput_2 - inputs:(after_tFileInputExcel_1) outputs:(row2,row2) | target node:tAdvancedHash_row2 - inputs:(row2) outputs:()
			   		// linked node: tMap_1 - inputs:(row1,row2,row4,row5,row6,row7,row3) outputs:(fact)
			   
			   		org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row2 = 
			   			org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;
			   			
			   
	   			org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct> tHash_Lookup_row2 =org.talend.designer.components.lookup.memory.AdvancedMemoryLookup.
	   						<row2Struct>getLookup(matchingModeEnum_row2);
	   						   
		   	   	   globalMap.put("tHash_Lookup_row2", tHash_Lookup_row2);
		   	   	   
				
           

 



/**
 * [tAdvancedHash_row2 begin ] stop
 */



	
	/**
	 * [tDBInput_2 begin ] start
	 */

	

	
		
		ok_Hash.put("tDBInput_2", false);
		start_Hash.put("tDBInput_2", System.currentTimeMillis());
		
	
	currentComponent="tDBInput_2";

	
		int tos_count_tDBInput_2 = 0;
		
	
    
	
			org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_2 = org.talend.designer.components.util.mssql.MSSqlUtilFactory.getMSSqlGenerateTimestampUtil();
			
			java.util.List<String> talendToDBList_tDBInput_2 = new java.util.ArrayList();
			String[] talendToDBArray_tDBInput_2  = new String[]{"FLOAT","NUMERIC","NUMERIC IDENTITY","DECIMAL","DECIMAL IDENTITY","REAL"}; 
			java.util.Collections.addAll(talendToDBList_tDBInput_2, talendToDBArray_tDBInput_2); 
		    int nb_line_tDBInput_2 = 0;
		    java.sql.Connection conn_tDBInput_2 = null;
				String driverClass_tDBInput_2 = "net.sourceforge.jtds.jdbc.Driver";
			    java.lang.Class jdbcclazz_tDBInput_2 = java.lang.Class.forName(driverClass_tDBInput_2);
				String dbUser_tDBInput_2 = "Padelle";
				
				 
	final String decryptedPassword_tDBInput_2 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:rCqbmRgv35znzWwmXV/qNSIbhYYUUtFABRyXrEeTHEkTz/0=");
				
				String dbPwd_tDBInput_2 = decryptedPassword_tDBInput_2;
				
		    String port_tDBInput_2 = "1433";
		    String dbname_tDBInput_2 = "DW_padel" ;
			String url_tDBInput_2 = "jdbc:jtds:sqlserver://" + "DESKTOP-QJ70MNR" ;
		    if (!"".equals(port_tDBInput_2)) {
		    	url_tDBInput_2 += ":" + "1433";
		    }
		    if (!"".equals(dbname_tDBInput_2)) {
				url_tDBInput_2 += "//" + "DW_padel"; 
		    }
		    url_tDBInput_2 += ";appName=" + projectName + ";" + "";
		    String dbschema_tDBInput_2 = "";
				
				conn_tDBInput_2 = java.sql.DriverManager.getConnection(url_tDBInput_2,dbUser_tDBInput_2,dbPwd_tDBInput_2);
		        
		    
			java.sql.Statement stmt_tDBInput_2 = conn_tDBInput_2.createStatement();

		    String dbquery_tDBInput_2 = "SELECT dim_date.date_key,\n		dim_date.full_date,\n		dim_date.annee,\n		dim_date.mois,\n		dim_date.nom_mois,\n		dim_date.jour"
+"_semaine,\n		dim_date.trimestre\nFROM	dim_date";
		    

            	globalMap.put("tDBInput_2_QUERY",dbquery_tDBInput_2);
		    java.sql.ResultSet rs_tDBInput_2 = null;

		    try {
		    	rs_tDBInput_2 = stmt_tDBInput_2.executeQuery(dbquery_tDBInput_2);
		    	java.sql.ResultSetMetaData rsmd_tDBInput_2 = rs_tDBInput_2.getMetaData();
		    	int colQtyInRs_tDBInput_2 = rsmd_tDBInput_2.getColumnCount();

		    String tmpContent_tDBInput_2 = null;
		    
		    
		    while (rs_tDBInput_2.next()) {
		        nb_line_tDBInput_2++;
		        
							if(colQtyInRs_tDBInput_2 < 1) {
								row2.date_key = null;
							} else {
		                          
            row2.date_key = rs_tDBInput_2.getInt(1);
            if(rs_tDBInput_2.wasNull()){
                    row2.date_key = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 2) {
								row2.full_date = null;
							} else {
										
			row2.full_date = mssqlGTU_tDBInput_2.getDate(rsmd_tDBInput_2, rs_tDBInput_2, 2);
			
		                    }
							if(colQtyInRs_tDBInput_2 < 3) {
								row2.annee = null;
							} else {
		                          
            row2.annee = rs_tDBInput_2.getInt(3);
            if(rs_tDBInput_2.wasNull()){
                    row2.annee = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 4) {
								row2.mois = null;
							} else {
		                          
            row2.mois = rs_tDBInput_2.getInt(4);
            if(rs_tDBInput_2.wasNull()){
                    row2.mois = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 5) {
								row2.nom_mois = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(5);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(5).toUpperCase(java.util.Locale.ENGLISH))) {
            		row2.nom_mois = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row2.nom_mois = tmpContent_tDBInput_2;
                }
            } else {
                row2.nom_mois = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 6) {
								row2.jour_semaine = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(6);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(6).toUpperCase(java.util.Locale.ENGLISH))) {
            		row2.jour_semaine = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row2.jour_semaine = tmpContent_tDBInput_2;
                }
            } else {
                row2.jour_semaine = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 7) {
								row2.trimestre = null;
							} else {
		                          
            row2.trimestre = rs_tDBInput_2.getInt(7);
            if(rs_tDBInput_2.wasNull()){
                    row2.trimestre = null;
            }
		                    }
					





 



/**
 * [tDBInput_2 begin ] stop
 */
	
	/**
	 * [tDBInput_2 main ] start
	 */

	

	
	
	currentComponent="tDBInput_2";

	

 


	tos_count_tDBInput_2++;

/**
 * [tDBInput_2 main ] stop
 */
	
	/**
	 * [tDBInput_2 process_data_begin ] start
	 */

	

	
	
	currentComponent="tDBInput_2";

	

 



/**
 * [tDBInput_2 process_data_begin ] stop
 */

	
	/**
	 * [tAdvancedHash_row2 main ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row2";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row2"
						
						);
					}
					


			   
			   

					row2Struct row2_HashRow = new row2Struct();
		   	   	   
				
				row2_HashRow.date_key = row2.date_key;
				
				row2_HashRow.full_date = row2.full_date;
				
				row2_HashRow.annee = row2.annee;
				
				row2_HashRow.mois = row2.mois;
				
				row2_HashRow.nom_mois = row2.nom_mois;
				
				row2_HashRow.jour_semaine = row2.jour_semaine;
				
				row2_HashRow.trimestre = row2.trimestre;
				
			tHash_Lookup_row2.put(row2_HashRow);
			
            




 


	tos_count_tAdvancedHash_row2++;

/**
 * [tAdvancedHash_row2 main ] stop
 */
	
	/**
	 * [tAdvancedHash_row2 process_data_begin ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row2";

	

 



/**
 * [tAdvancedHash_row2 process_data_begin ] stop
 */
	
	/**
	 * [tAdvancedHash_row2 process_data_end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row2";

	

 



/**
 * [tAdvancedHash_row2 process_data_end ] stop
 */



	
	/**
	 * [tDBInput_2 process_data_end ] start
	 */

	

	
	
	currentComponent="tDBInput_2";

	

 



/**
 * [tDBInput_2 process_data_end ] stop
 */
	
	/**
	 * [tDBInput_2 end ] start
	 */

	

	
	
	currentComponent="tDBInput_2";

	

	}
}finally{
	if (rs_tDBInput_2 != null) {
		rs_tDBInput_2.close();
	}
	if (stmt_tDBInput_2 != null) {
		stmt_tDBInput_2.close();
	}
		if(conn_tDBInput_2 != null && !conn_tDBInput_2.isClosed()) {
			
			conn_tDBInput_2.close();
			
			if("com.mysql.cj.jdbc.Driver".equals((String)globalMap.get("driverClass_"))
			    && routines.system.BundleUtils.inOSGi()) {
			        Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread").
			            getMethod("checkedShutdown").invoke(null, (Object[]) null);
			}
			
		}
}
globalMap.put("tDBInput_2_NB_LINE",nb_line_tDBInput_2);

 

ok_Hash.put("tDBInput_2", true);
end_Hash.put("tDBInput_2", System.currentTimeMillis());




/**
 * [tDBInput_2 end ] stop
 */

	
	/**
	 * [tAdvancedHash_row2 end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row2";

	

tHash_Lookup_row2.endPut();

				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row2");
			  	}
			  	
 

ok_Hash.put("tAdvancedHash_row2", true);
end_Hash.put("tAdvancedHash_row2", System.currentTimeMillis());




/**
 * [tAdvancedHash_row2 end ] stop
 */



				}//end the resume

				



	
			}catch(java.lang.Exception e){	
				
				TalendException te = new TalendException(e, currentComponent, globalMap);
				
				throw te;
			}catch(java.lang.Error error){	
				
					runStat.stopThreadStat();
				
				throw error;
			}finally{
				
				try{
					
	
	/**
	 * [tDBInput_2 finally ] start
	 */

	

	
	
	currentComponent="tDBInput_2";

	

 



/**
 * [tDBInput_2 finally ] stop
 */

	
	/**
	 * [tAdvancedHash_row2 finally ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row2";

	

 



/**
 * [tAdvancedHash_row2 finally ] stop
 */



				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tDBInput_2_SUBPROCESS_STATE", 1);
	}
	


public static class row4Struct implements routines.system.IPersistableComparableLookupRow<row4Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_FACT_MATCHS = new byte[0];
    static byte[] commonByteArray_DW_PADEL_FACT_MATCHS = new byte[0];
	protected static final int DEFAULT_HASHCODE = 1;
    protected static final int PRIME = 31;
    protected int hashCode = DEFAULT_HASHCODE;
    public boolean hashCodeDirty = true;

    public String loopKey;



	
			    public int player_id;

				public int getPlayer_id () {
					return this.player_id;
				}
				
			    public String full_name;

				public String getFull_name () {
					return this.full_name;
				}
				
			    public Integer ranking;

				public Integer getRanking () {
					return this.ranking;
				}
				
			    public String gender;

				public String getGender () {
					return this.gender;
				}
				
			    public String nationality;

				public String getNationality () {
					return this.nationality;
				}
				
			    public java.util.Date birthdate;

				public java.util.Date getBirthdate () {
					return this.birthdate;
				}
				
			    public Short height_cm;

				public Short getHeight_cm () {
					return this.height_cm;
				}
				
			    public String playing_hand;

				public String getPlaying_hand () {
					return this.playing_hand;
				}
				
			    public String court_side;

				public String getCourt_side () {
					return this.court_side;
				}
				
			    public String partner_name;

				public String getPartner_name () {
					return this.partner_name;
				}
				
			    public Integer total_points;

				public Integer getTotal_points () {
					return this.total_points;
				}
				


	@Override
	public int hashCode() {
		if (this.hashCodeDirty) {
			final int prime = PRIME;
			int result = DEFAULT_HASHCODE;
	
						result = prime * result + ((this.full_name == null) ? 0 : this.full_name.hashCode());
					
    		this.hashCode = result;
    		this.hashCodeDirty = false;
		}
		return this.hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final row4Struct other = (row4Struct) obj;
		
						if (this.full_name == null) {
							if (other.full_name != null)
								return false;
						
						} else if (!this.full_name.equals(other.full_name))
						
							return false;
					

		return true;
    }

	public void copyDataTo(row4Struct other) {

		other.player_id = this.player_id;
	            other.full_name = this.full_name;
	            other.ranking = this.ranking;
	            other.gender = this.gender;
	            other.nationality = this.nationality;
	            other.birthdate = this.birthdate;
	            other.height_cm = this.height_cm;
	            other.playing_hand = this.playing_hand;
	            other.court_side = this.court_side;
	            other.partner_name = this.partner_name;
	            other.total_points = this.total_points;
	            
	}

	public void copyKeysDataTo(row4Struct other) {

		other.full_name = this.full_name;
	            	
	}




	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_FACT_MATCHS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_MATCHS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_FACT_MATCHS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_MATCHS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length, utf8Charset);
		}
		return strReturn;
	}

    private void writeString(String str, ObjectOutputStream dos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
    }
    
    private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
    }
	private Integer readInteger(DataInputStream dis, ObjectInputStream ois) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
			intReturn = dis.readInt();
		}
		return intReturn;
	}
	
	private Integer readInteger(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		Integer intReturn;
        int length = 0;
        length = unmarshaller.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
			intReturn = unmarshaller.readInt();
		}
		return intReturn;
	}

	private void writeInteger(Integer intNum, DataOutputStream dos, ObjectOutputStream oos) throws IOException{
		if(intNum == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeInt(intNum);
    	}
	}
	
	private void writeInteger(Integer intNum, DataOutputStream dos,org.jboss.marshalling.Marshaller marshaller ) throws IOException{
		if(intNum == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeInt(intNum);
    	}
	}
	
	private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			byte[] byteArray = new byte[length];
			dis.read(byteArray);
			strReturn = new String(byteArray, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			byte[] byteArray = new byte[length];
			unmarshaller.read(byteArray);
			strReturn = new String(byteArray, utf8Charset);
		}
		return strReturn;
	}
	
	private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
	}

	private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
	}

	private java.util.Date readDate(DataInputStream dis, ObjectInputStream ois) throws IOException{
		java.util.Date dateReturn = null;
		int length = 0;
        length = dis.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(dis.readLong());
		}
		return dateReturn;
	}
	
	private java.util.Date readDate(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller ) throws IOException{
		java.util.Date dateReturn = null;
		int length = 0;
        length = unmarshaller.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(unmarshaller.readLong());
		}
		return dateReturn;
	}

	private void writeDate(java.util.Date date1, DataOutputStream dos, ObjectOutputStream oos) throws IOException{
		if(date1 == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeLong(date1.getTime());
    	}
	}
	
	private void writeDate(java.util.Date date1, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(date1 == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeLong(date1.getTime());
    	}
	}

    public void readKeysData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_MATCHS) {

        	try {

        		int length = 0;
		
					this.full_name = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_MATCHS) {

        	try {

        		int length = 0;
		
					this.full_name = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeKeysData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.full_name,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.full_name,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }



    /**
     * Fill Values data by reading ObjectInputStream.
     */
    public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
        try {

			int length = 0;
		
			            this.player_id = dis.readInt();
					
						this.ranking = readInteger(dis,ois);
					
						this.gender = readString(dis,ois);
					
						this.nationality = readString(dis,ois);
					
						this.birthdate = readDate(dis,ois);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.height_cm = null;
           				} else {
           			    	this.height_cm = dis.readShort();
           				}
					
						this.playing_hand = readString(dis,ois);
					
						this.court_side = readString(dis,ois);
					
						this.partner_name = readString(dis,ois);
					
						this.total_points = readInteger(dis,ois);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }
    
    public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
        try {
			int length = 0;
		
			            this.player_id = objectIn.readInt();
					
						this.ranking = readInteger(dis,objectIn);
					
						this.gender = readString(dis,objectIn);
					
						this.nationality = readString(dis,objectIn);
					
						this.birthdate = readDate(dis,objectIn);
					
			            length = objectIn.readByte();
           				if (length == -1) {
           	    			this.height_cm = null;
           				} else {
           			    	this.height_cm = objectIn.readShort();
           				}
					
						this.playing_hand = readString(dis,objectIn);
					
						this.court_side = readString(dis,objectIn);
					
						this.partner_name = readString(dis,objectIn);
					
						this.total_points = readInteger(dis,objectIn);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }

    /**
     * Return a byte array which represents Values data.
     */
    public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
        try {

		
		            	dos.writeInt(this.player_id);
					
					writeInteger(this.ranking, dos, oos);
					
						writeString(this.gender, dos, oos);
					
						writeString(this.nationality, dos, oos);
					
						writeDate(this.birthdate, dos, oos);
					
						if(this.height_cm == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeShort(this.height_cm);
		            	}
					
						writeString(this.playing_hand, dos, oos);
					
						writeString(this.court_side, dos, oos);
					
						writeString(this.partner_name, dos, oos);
					
					writeInteger(this.total_points, dos, oos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}

    }
    
    public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut){
                try {

		
					objectOut.writeInt(this.player_id);
					
					writeInteger(this.ranking, dos, objectOut);
					
						writeString(this.gender, dos, objectOut);
					
						writeString(this.nationality, dos, objectOut);
					
						writeDate(this.birthdate, dos, objectOut);
					
						if(this.height_cm == null) {
							objectOut.writeByte(-1);
						} else {
							objectOut.writeByte(0);
							objectOut.writeShort(this.height_cm);
		            	}
					
						writeString(this.playing_hand, dos, objectOut);
					
						writeString(this.court_side, dos, objectOut);
					
						writeString(this.partner_name, dos, objectOut);
					
					writeInteger(this.total_points, dos, objectOut);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}
    }


    
    public boolean supportMarshaller(){
        return true;
    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("player_id="+String.valueOf(player_id));
		sb.append(",full_name="+full_name);
		sb.append(",ranking="+String.valueOf(ranking));
		sb.append(",gender="+gender);
		sb.append(",nationality="+nationality);
		sb.append(",birthdate="+String.valueOf(birthdate));
		sb.append(",height_cm="+String.valueOf(height_cm));
		sb.append(",playing_hand="+playing_hand);
		sb.append(",court_side="+court_side);
		sb.append(",partner_name="+partner_name);
		sb.append(",total_points="+String.valueOf(total_points));
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(row4Struct other) {

		int returnValue = -1;
		
						returnValue = checkNullsAndCompare(this.full_name, other.full_name);
						if(returnValue != 0) {
							return returnValue;
						}

					
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}
public void tDBInput_4Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tDBInput_4_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { //start the resume
				globalResumeTicket = true;



		row4Struct row4 = new row4Struct();




	
	/**
	 * [tAdvancedHash_row4 begin ] start
	 */

	

	
		
		ok_Hash.put("tAdvancedHash_row4", false);
		start_Hash.put("tAdvancedHash_row4", System.currentTimeMillis());
		
	
	currentComponent="tAdvancedHash_row4";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row4");
					}
				
		int tos_count_tAdvancedHash_row4 = 0;
		

			   		// connection name:row4
			   		// source node:tDBInput_4 - inputs:(after_tFileInputExcel_1) outputs:(row4,row4) | target node:tAdvancedHash_row4 - inputs:(row4) outputs:()
			   		// linked node: tMap_1 - inputs:(row1,row2,row4,row5,row6,row7,row3) outputs:(fact)
			   
			   		org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row4 = 
			   			org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;
			   			
			   
	   			org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct> tHash_Lookup_row4 =org.talend.designer.components.lookup.memory.AdvancedMemoryLookup.
	   						<row4Struct>getLookup(matchingModeEnum_row4);
	   						   
		   	   	   globalMap.put("tHash_Lookup_row4", tHash_Lookup_row4);
		   	   	   
				
           

 



/**
 * [tAdvancedHash_row4 begin ] stop
 */



	
	/**
	 * [tDBInput_4 begin ] start
	 */

	

	
		
		ok_Hash.put("tDBInput_4", false);
		start_Hash.put("tDBInput_4", System.currentTimeMillis());
		
	
	currentComponent="tDBInput_4";

	
		int tos_count_tDBInput_4 = 0;
		
	
    
	
			org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_4 = org.talend.designer.components.util.mssql.MSSqlUtilFactory.getMSSqlGenerateTimestampUtil();
			
			java.util.List<String> talendToDBList_tDBInput_4 = new java.util.ArrayList();
			String[] talendToDBArray_tDBInput_4  = new String[]{"FLOAT","NUMERIC","NUMERIC IDENTITY","DECIMAL","DECIMAL IDENTITY","REAL"}; 
			java.util.Collections.addAll(talendToDBList_tDBInput_4, talendToDBArray_tDBInput_4); 
		    int nb_line_tDBInput_4 = 0;
		    java.sql.Connection conn_tDBInput_4 = null;
				String driverClass_tDBInput_4 = "net.sourceforge.jtds.jdbc.Driver";
			    java.lang.Class jdbcclazz_tDBInput_4 = java.lang.Class.forName(driverClass_tDBInput_4);
				String dbUser_tDBInput_4 = "Padelle";
				
				 
	final String decryptedPassword_tDBInput_4 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:kwybNsSTVz2SWYKziUeR59+Z1BTvjwAi8oBNCWTyC6DJ0Yo=");
				
				String dbPwd_tDBInput_4 = decryptedPassword_tDBInput_4;
				
		    String port_tDBInput_4 = "1433";
		    String dbname_tDBInput_4 = "DW_padel" ;
			String url_tDBInput_4 = "jdbc:jtds:sqlserver://" + "DESKTOP-QJ70MNR" ;
		    if (!"".equals(port_tDBInput_4)) {
		    	url_tDBInput_4 += ":" + "1433";
		    }
		    if (!"".equals(dbname_tDBInput_4)) {
				url_tDBInput_4 += "//" + "DW_padel"; 
		    }
		    url_tDBInput_4 += ";appName=" + projectName + ";" + "";
		    String dbschema_tDBInput_4 = "";
				
				conn_tDBInput_4 = java.sql.DriverManager.getConnection(url_tDBInput_4,dbUser_tDBInput_4,dbPwd_tDBInput_4);
		        
		    
			java.sql.Statement stmt_tDBInput_4 = conn_tDBInput_4.createStatement();

		    String dbquery_tDBInput_4 = "SELECT dim_player.player_id,\n		dim_player.full_name,\n		dim_player.ranking,\n		dim_player.gender,\n		dim_player.nationalit"
+"y,\n		dim_player.birthdate,\n		dim_player.height_cm,\n		dim_player.playing_hand,\n		dim_player.court_side,\n		dim_player.part"
+"ner_name,\n		dim_player.total_points\nFROM	dim_player";
		    

            	globalMap.put("tDBInput_4_QUERY",dbquery_tDBInput_4);
		    java.sql.ResultSet rs_tDBInput_4 = null;

		    try {
		    	rs_tDBInput_4 = stmt_tDBInput_4.executeQuery(dbquery_tDBInput_4);
		    	java.sql.ResultSetMetaData rsmd_tDBInput_4 = rs_tDBInput_4.getMetaData();
		    	int colQtyInRs_tDBInput_4 = rsmd_tDBInput_4.getColumnCount();

		    String tmpContent_tDBInput_4 = null;
		    
		    
		    while (rs_tDBInput_4.next()) {
		        nb_line_tDBInput_4++;
		        
							if(colQtyInRs_tDBInput_4 < 1) {
								row4.player_id = 0;
							} else {
		                          
            row4.player_id = rs_tDBInput_4.getInt(1);
            if(rs_tDBInput_4.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
							if(colQtyInRs_tDBInput_4 < 2) {
								row4.full_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_4 = rs_tDBInput_4.getString(2);
            if(tmpContent_tDBInput_4 != null) {
            	if (talendToDBList_tDBInput_4 .contains(rsmd_tDBInput_4.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.full_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_4);
            	} else {
                	row4.full_name = tmpContent_tDBInput_4;
                }
            } else {
                row4.full_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_4 < 3) {
								row4.ranking = null;
							} else {
		                          
            row4.ranking = rs_tDBInput_4.getInt(3);
            if(rs_tDBInput_4.wasNull()){
                    row4.ranking = null;
            }
		                    }
							if(colQtyInRs_tDBInput_4 < 4) {
								row4.gender = null;
							} else {
	                         		
           		tmpContent_tDBInput_4 = rs_tDBInput_4.getString(4);
            if(tmpContent_tDBInput_4 != null) {
            	if (talendToDBList_tDBInput_4 .contains(rsmd_tDBInput_4.getColumnTypeName(4).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.gender = FormatterUtils.formatUnwithE(tmpContent_tDBInput_4);
            	} else {
                	row4.gender = tmpContent_tDBInput_4;
                }
            } else {
                row4.gender = null;
            }
		                    }
							if(colQtyInRs_tDBInput_4 < 5) {
								row4.nationality = null;
							} else {
	                         		
           		tmpContent_tDBInput_4 = rs_tDBInput_4.getString(5);
            if(tmpContent_tDBInput_4 != null) {
            	if (talendToDBList_tDBInput_4 .contains(rsmd_tDBInput_4.getColumnTypeName(5).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.nationality = FormatterUtils.formatUnwithE(tmpContent_tDBInput_4);
            	} else {
                	row4.nationality = tmpContent_tDBInput_4;
                }
            } else {
                row4.nationality = null;
            }
		                    }
							if(colQtyInRs_tDBInput_4 < 6) {
								row4.birthdate = null;
							} else {
										
			row4.birthdate = mssqlGTU_tDBInput_4.getDate(rsmd_tDBInput_4, rs_tDBInput_4, 6);
			
		                    }
							if(colQtyInRs_tDBInput_4 < 7) {
								row4.height_cm = null;
							} else {
		                          
            row4.height_cm = rs_tDBInput_4.getShort(7);
            if(rs_tDBInput_4.wasNull()){
                    row4.height_cm = null;
            }
		                    }
							if(colQtyInRs_tDBInput_4 < 8) {
								row4.playing_hand = null;
							} else {
	                         		
           		tmpContent_tDBInput_4 = rs_tDBInput_4.getString(8);
            if(tmpContent_tDBInput_4 != null) {
            	if (talendToDBList_tDBInput_4 .contains(rsmd_tDBInput_4.getColumnTypeName(8).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.playing_hand = FormatterUtils.formatUnwithE(tmpContent_tDBInput_4);
            	} else {
                	row4.playing_hand = tmpContent_tDBInput_4;
                }
            } else {
                row4.playing_hand = null;
            }
		                    }
							if(colQtyInRs_tDBInput_4 < 9) {
								row4.court_side = null;
							} else {
	                         		
           		tmpContent_tDBInput_4 = rs_tDBInput_4.getString(9);
            if(tmpContent_tDBInput_4 != null) {
            	if (talendToDBList_tDBInput_4 .contains(rsmd_tDBInput_4.getColumnTypeName(9).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.court_side = FormatterUtils.formatUnwithE(tmpContent_tDBInput_4);
            	} else {
                	row4.court_side = tmpContent_tDBInput_4;
                }
            } else {
                row4.court_side = null;
            }
		                    }
							if(colQtyInRs_tDBInput_4 < 10) {
								row4.partner_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_4 = rs_tDBInput_4.getString(10);
            if(tmpContent_tDBInput_4 != null) {
            	if (talendToDBList_tDBInput_4 .contains(rsmd_tDBInput_4.getColumnTypeName(10).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.partner_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_4);
            	} else {
                	row4.partner_name = tmpContent_tDBInput_4;
                }
            } else {
                row4.partner_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_4 < 11) {
								row4.total_points = null;
							} else {
		                          
            row4.total_points = rs_tDBInput_4.getInt(11);
            if(rs_tDBInput_4.wasNull()){
                    row4.total_points = null;
            }
		                    }
					





 



/**
 * [tDBInput_4 begin ] stop
 */
	
	/**
	 * [tDBInput_4 main ] start
	 */

	

	
	
	currentComponent="tDBInput_4";

	

 


	tos_count_tDBInput_4++;

/**
 * [tDBInput_4 main ] stop
 */
	
	/**
	 * [tDBInput_4 process_data_begin ] start
	 */

	

	
	
	currentComponent="tDBInput_4";

	

 



/**
 * [tDBInput_4 process_data_begin ] stop
 */

	
	/**
	 * [tAdvancedHash_row4 main ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row4";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row4"
						
						);
					}
					


			   
			   

					row4Struct row4_HashRow = new row4Struct();
		   	   	   
				
				row4_HashRow.player_id = row4.player_id;
				
				row4_HashRow.full_name = row4.full_name;
				
				row4_HashRow.ranking = row4.ranking;
				
				row4_HashRow.gender = row4.gender;
				
				row4_HashRow.nationality = row4.nationality;
				
				row4_HashRow.birthdate = row4.birthdate;
				
				row4_HashRow.height_cm = row4.height_cm;
				
				row4_HashRow.playing_hand = row4.playing_hand;
				
				row4_HashRow.court_side = row4.court_side;
				
				row4_HashRow.partner_name = row4.partner_name;
				
				row4_HashRow.total_points = row4.total_points;
				
			tHash_Lookup_row4.put(row4_HashRow);
			
            




 


	tos_count_tAdvancedHash_row4++;

/**
 * [tAdvancedHash_row4 main ] stop
 */
	
	/**
	 * [tAdvancedHash_row4 process_data_begin ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row4";

	

 



/**
 * [tAdvancedHash_row4 process_data_begin ] stop
 */
	
	/**
	 * [tAdvancedHash_row4 process_data_end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row4";

	

 



/**
 * [tAdvancedHash_row4 process_data_end ] stop
 */



	
	/**
	 * [tDBInput_4 process_data_end ] start
	 */

	

	
	
	currentComponent="tDBInput_4";

	

 



/**
 * [tDBInput_4 process_data_end ] stop
 */
	
	/**
	 * [tDBInput_4 end ] start
	 */

	

	
	
	currentComponent="tDBInput_4";

	

	}
}finally{
	if (rs_tDBInput_4 != null) {
		rs_tDBInput_4.close();
	}
	if (stmt_tDBInput_4 != null) {
		stmt_tDBInput_4.close();
	}
		if(conn_tDBInput_4 != null && !conn_tDBInput_4.isClosed()) {
			
			conn_tDBInput_4.close();
			
			if("com.mysql.cj.jdbc.Driver".equals((String)globalMap.get("driverClass_"))
			    && routines.system.BundleUtils.inOSGi()) {
			        Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread").
			            getMethod("checkedShutdown").invoke(null, (Object[]) null);
			}
			
		}
}
globalMap.put("tDBInput_4_NB_LINE",nb_line_tDBInput_4);

 

ok_Hash.put("tDBInput_4", true);
end_Hash.put("tDBInput_4", System.currentTimeMillis());




/**
 * [tDBInput_4 end ] stop
 */

	
	/**
	 * [tAdvancedHash_row4 end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row4";

	

tHash_Lookup_row4.endPut();

				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row4");
			  	}
			  	
 

ok_Hash.put("tAdvancedHash_row4", true);
end_Hash.put("tAdvancedHash_row4", System.currentTimeMillis());




/**
 * [tAdvancedHash_row4 end ] stop
 */



				}//end the resume

				



	
			}catch(java.lang.Exception e){	
				
				TalendException te = new TalendException(e, currentComponent, globalMap);
				
				throw te;
			}catch(java.lang.Error error){	
				
					runStat.stopThreadStat();
				
				throw error;
			}finally{
				
				try{
					
	
	/**
	 * [tDBInput_4 finally ] start
	 */

	

	
	
	currentComponent="tDBInput_4";

	

 



/**
 * [tDBInput_4 finally ] stop
 */

	
	/**
	 * [tAdvancedHash_row4 finally ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row4";

	

 



/**
 * [tAdvancedHash_row4 finally ] stop
 */



				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tDBInput_4_SUBPROCESS_STATE", 1);
	}
	


public static class row5Struct implements routines.system.IPersistableComparableLookupRow<row5Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_FACT_MATCHS = new byte[0];
    static byte[] commonByteArray_DW_PADEL_FACT_MATCHS = new byte[0];
	protected static final int DEFAULT_HASHCODE = 1;
    protected static final int PRIME = 31;
    protected int hashCode = DEFAULT_HASHCODE;
    public boolean hashCodeDirty = true;

    public String loopKey;



	
			    public int player_id;

				public int getPlayer_id () {
					return this.player_id;
				}
				
			    public String full_name;

				public String getFull_name () {
					return this.full_name;
				}
				
			    public Integer ranking;

				public Integer getRanking () {
					return this.ranking;
				}
				
			    public String gender;

				public String getGender () {
					return this.gender;
				}
				
			    public String nationality;

				public String getNationality () {
					return this.nationality;
				}
				
			    public java.util.Date birthdate;

				public java.util.Date getBirthdate () {
					return this.birthdate;
				}
				
			    public Short height_cm;

				public Short getHeight_cm () {
					return this.height_cm;
				}
				
			    public String playing_hand;

				public String getPlaying_hand () {
					return this.playing_hand;
				}
				
			    public String court_side;

				public String getCourt_side () {
					return this.court_side;
				}
				
			    public String partner_name;

				public String getPartner_name () {
					return this.partner_name;
				}
				
			    public Integer total_points;

				public Integer getTotal_points () {
					return this.total_points;
				}
				


	@Override
	public int hashCode() {
		if (this.hashCodeDirty) {
			final int prime = PRIME;
			int result = DEFAULT_HASHCODE;
	
						result = prime * result + ((this.full_name == null) ? 0 : this.full_name.hashCode());
					
    		this.hashCode = result;
    		this.hashCodeDirty = false;
		}
		return this.hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final row5Struct other = (row5Struct) obj;
		
						if (this.full_name == null) {
							if (other.full_name != null)
								return false;
						
						} else if (!this.full_name.equals(other.full_name))
						
							return false;
					

		return true;
    }

	public void copyDataTo(row5Struct other) {

		other.player_id = this.player_id;
	            other.full_name = this.full_name;
	            other.ranking = this.ranking;
	            other.gender = this.gender;
	            other.nationality = this.nationality;
	            other.birthdate = this.birthdate;
	            other.height_cm = this.height_cm;
	            other.playing_hand = this.playing_hand;
	            other.court_side = this.court_side;
	            other.partner_name = this.partner_name;
	            other.total_points = this.total_points;
	            
	}

	public void copyKeysDataTo(row5Struct other) {

		other.full_name = this.full_name;
	            	
	}




	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_FACT_MATCHS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_MATCHS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_FACT_MATCHS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_MATCHS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length, utf8Charset);
		}
		return strReturn;
	}

    private void writeString(String str, ObjectOutputStream dos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
    }
    
    private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
    }
	private Integer readInteger(DataInputStream dis, ObjectInputStream ois) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
			intReturn = dis.readInt();
		}
		return intReturn;
	}
	
	private Integer readInteger(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		Integer intReturn;
        int length = 0;
        length = unmarshaller.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
			intReturn = unmarshaller.readInt();
		}
		return intReturn;
	}

	private void writeInteger(Integer intNum, DataOutputStream dos, ObjectOutputStream oos) throws IOException{
		if(intNum == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeInt(intNum);
    	}
	}
	
	private void writeInteger(Integer intNum, DataOutputStream dos,org.jboss.marshalling.Marshaller marshaller ) throws IOException{
		if(intNum == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeInt(intNum);
    	}
	}
	
	private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			byte[] byteArray = new byte[length];
			dis.read(byteArray);
			strReturn = new String(byteArray, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			byte[] byteArray = new byte[length];
			unmarshaller.read(byteArray);
			strReturn = new String(byteArray, utf8Charset);
		}
		return strReturn;
	}
	
	private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
	}

	private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
	}

	private java.util.Date readDate(DataInputStream dis, ObjectInputStream ois) throws IOException{
		java.util.Date dateReturn = null;
		int length = 0;
        length = dis.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(dis.readLong());
		}
		return dateReturn;
	}
	
	private java.util.Date readDate(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller ) throws IOException{
		java.util.Date dateReturn = null;
		int length = 0;
        length = unmarshaller.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(unmarshaller.readLong());
		}
		return dateReturn;
	}

	private void writeDate(java.util.Date date1, DataOutputStream dos, ObjectOutputStream oos) throws IOException{
		if(date1 == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeLong(date1.getTime());
    	}
	}
	
	private void writeDate(java.util.Date date1, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(date1 == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeLong(date1.getTime());
    	}
	}

    public void readKeysData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_MATCHS) {

        	try {

        		int length = 0;
		
					this.full_name = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_MATCHS) {

        	try {

        		int length = 0;
		
					this.full_name = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeKeysData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.full_name,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.full_name,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }



    /**
     * Fill Values data by reading ObjectInputStream.
     */
    public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
        try {

			int length = 0;
		
			            this.player_id = dis.readInt();
					
						this.ranking = readInteger(dis,ois);
					
						this.gender = readString(dis,ois);
					
						this.nationality = readString(dis,ois);
					
						this.birthdate = readDate(dis,ois);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.height_cm = null;
           				} else {
           			    	this.height_cm = dis.readShort();
           				}
					
						this.playing_hand = readString(dis,ois);
					
						this.court_side = readString(dis,ois);
					
						this.partner_name = readString(dis,ois);
					
						this.total_points = readInteger(dis,ois);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }
    
    public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
        try {
			int length = 0;
		
			            this.player_id = objectIn.readInt();
					
						this.ranking = readInteger(dis,objectIn);
					
						this.gender = readString(dis,objectIn);
					
						this.nationality = readString(dis,objectIn);
					
						this.birthdate = readDate(dis,objectIn);
					
			            length = objectIn.readByte();
           				if (length == -1) {
           	    			this.height_cm = null;
           				} else {
           			    	this.height_cm = objectIn.readShort();
           				}
					
						this.playing_hand = readString(dis,objectIn);
					
						this.court_side = readString(dis,objectIn);
					
						this.partner_name = readString(dis,objectIn);
					
						this.total_points = readInteger(dis,objectIn);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }

    /**
     * Return a byte array which represents Values data.
     */
    public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
        try {

		
		            	dos.writeInt(this.player_id);
					
					writeInteger(this.ranking, dos, oos);
					
						writeString(this.gender, dos, oos);
					
						writeString(this.nationality, dos, oos);
					
						writeDate(this.birthdate, dos, oos);
					
						if(this.height_cm == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeShort(this.height_cm);
		            	}
					
						writeString(this.playing_hand, dos, oos);
					
						writeString(this.court_side, dos, oos);
					
						writeString(this.partner_name, dos, oos);
					
					writeInteger(this.total_points, dos, oos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}

    }
    
    public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut){
                try {

		
					objectOut.writeInt(this.player_id);
					
					writeInteger(this.ranking, dos, objectOut);
					
						writeString(this.gender, dos, objectOut);
					
						writeString(this.nationality, dos, objectOut);
					
						writeDate(this.birthdate, dos, objectOut);
					
						if(this.height_cm == null) {
							objectOut.writeByte(-1);
						} else {
							objectOut.writeByte(0);
							objectOut.writeShort(this.height_cm);
		            	}
					
						writeString(this.playing_hand, dos, objectOut);
					
						writeString(this.court_side, dos, objectOut);
					
						writeString(this.partner_name, dos, objectOut);
					
					writeInteger(this.total_points, dos, objectOut);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}
    }


    
    public boolean supportMarshaller(){
        return true;
    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("player_id="+String.valueOf(player_id));
		sb.append(",full_name="+full_name);
		sb.append(",ranking="+String.valueOf(ranking));
		sb.append(",gender="+gender);
		sb.append(",nationality="+nationality);
		sb.append(",birthdate="+String.valueOf(birthdate));
		sb.append(",height_cm="+String.valueOf(height_cm));
		sb.append(",playing_hand="+playing_hand);
		sb.append(",court_side="+court_side);
		sb.append(",partner_name="+partner_name);
		sb.append(",total_points="+String.valueOf(total_points));
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(row5Struct other) {

		int returnValue = -1;
		
						returnValue = checkNullsAndCompare(this.full_name, other.full_name);
						if(returnValue != 0) {
							return returnValue;
						}

					
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}
public void tDBInput_5Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tDBInput_5_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { //start the resume
				globalResumeTicket = true;



		row5Struct row5 = new row5Struct();




	
	/**
	 * [tAdvancedHash_row5 begin ] start
	 */

	

	
		
		ok_Hash.put("tAdvancedHash_row5", false);
		start_Hash.put("tAdvancedHash_row5", System.currentTimeMillis());
		
	
	currentComponent="tAdvancedHash_row5";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row5");
					}
				
		int tos_count_tAdvancedHash_row5 = 0;
		

			   		// connection name:row5
			   		// source node:tDBInput_5 - inputs:(after_tFileInputExcel_1) outputs:(row5,row5) | target node:tAdvancedHash_row5 - inputs:(row5) outputs:()
			   		// linked node: tMap_1 - inputs:(row1,row2,row4,row5,row6,row7,row3) outputs:(fact)
			   
			   		org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row5 = 
			   			org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;
			   			
			   
	   			org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct> tHash_Lookup_row5 =org.talend.designer.components.lookup.memory.AdvancedMemoryLookup.
	   						<row5Struct>getLookup(matchingModeEnum_row5);
	   						   
		   	   	   globalMap.put("tHash_Lookup_row5", tHash_Lookup_row5);
		   	   	   
				
           

 



/**
 * [tAdvancedHash_row5 begin ] stop
 */



	
	/**
	 * [tDBInput_5 begin ] start
	 */

	

	
		
		ok_Hash.put("tDBInput_5", false);
		start_Hash.put("tDBInput_5", System.currentTimeMillis());
		
	
	currentComponent="tDBInput_5";

	
		int tos_count_tDBInput_5 = 0;
		
	
    
	
			org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_5 = org.talend.designer.components.util.mssql.MSSqlUtilFactory.getMSSqlGenerateTimestampUtil();
			
			java.util.List<String> talendToDBList_tDBInput_5 = new java.util.ArrayList();
			String[] talendToDBArray_tDBInput_5  = new String[]{"FLOAT","NUMERIC","NUMERIC IDENTITY","DECIMAL","DECIMAL IDENTITY","REAL"}; 
			java.util.Collections.addAll(talendToDBList_tDBInput_5, talendToDBArray_tDBInput_5); 
		    int nb_line_tDBInput_5 = 0;
		    java.sql.Connection conn_tDBInput_5 = null;
				String driverClass_tDBInput_5 = "net.sourceforge.jtds.jdbc.Driver";
			    java.lang.Class jdbcclazz_tDBInput_5 = java.lang.Class.forName(driverClass_tDBInput_5);
				String dbUser_tDBInput_5 = "Padelle";
				
				 
	final String decryptedPassword_tDBInput_5 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:qr3+sNx+Iri/rRdaSEQAKFDguGG7n1d4a3n3s4HjH4bIl3E=");
				
				String dbPwd_tDBInput_5 = decryptedPassword_tDBInput_5;
				
		    String port_tDBInput_5 = "1433";
		    String dbname_tDBInput_5 = "DW_padel" ;
			String url_tDBInput_5 = "jdbc:jtds:sqlserver://" + "DESKTOP-QJ70MNR" ;
		    if (!"".equals(port_tDBInput_5)) {
		    	url_tDBInput_5 += ":" + "1433";
		    }
		    if (!"".equals(dbname_tDBInput_5)) {
				url_tDBInput_5 += "//" + "DW_padel"; 
		    }
		    url_tDBInput_5 += ";appName=" + projectName + ";" + "";
		    String dbschema_tDBInput_5 = "";
				
				conn_tDBInput_5 = java.sql.DriverManager.getConnection(url_tDBInput_5,dbUser_tDBInput_5,dbPwd_tDBInput_5);
		        
		    
			java.sql.Statement stmt_tDBInput_5 = conn_tDBInput_5.createStatement();

		    String dbquery_tDBInput_5 = "SELECT dim_player.player_id,\n		dim_player.full_name,\n		dim_player.ranking,\n		dim_player.gender,\n		dim_player.nationalit"
+"y,\n		dim_player.birthdate,\n		dim_player.height_cm,\n		dim_player.playing_hand,\n		dim_player.court_side,\n		dim_player.part"
+"ner_name,\n		dim_player.total_points\nFROM	dim_player";
		    

            	globalMap.put("tDBInput_5_QUERY",dbquery_tDBInput_5);
		    java.sql.ResultSet rs_tDBInput_5 = null;

		    try {
		    	rs_tDBInput_5 = stmt_tDBInput_5.executeQuery(dbquery_tDBInput_5);
		    	java.sql.ResultSetMetaData rsmd_tDBInput_5 = rs_tDBInput_5.getMetaData();
		    	int colQtyInRs_tDBInput_5 = rsmd_tDBInput_5.getColumnCount();

		    String tmpContent_tDBInput_5 = null;
		    
		    
		    while (rs_tDBInput_5.next()) {
		        nb_line_tDBInput_5++;
		        
							if(colQtyInRs_tDBInput_5 < 1) {
								row5.player_id = 0;
							} else {
		                          
            row5.player_id = rs_tDBInput_5.getInt(1);
            if(rs_tDBInput_5.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
							if(colQtyInRs_tDBInput_5 < 2) {
								row5.full_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_5 = rs_tDBInput_5.getString(2);
            if(tmpContent_tDBInput_5 != null) {
            	if (talendToDBList_tDBInput_5 .contains(rsmd_tDBInput_5.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
            		row5.full_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_5);
            	} else {
                	row5.full_name = tmpContent_tDBInput_5;
                }
            } else {
                row5.full_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_5 < 3) {
								row5.ranking = null;
							} else {
		                          
            row5.ranking = rs_tDBInput_5.getInt(3);
            if(rs_tDBInput_5.wasNull()){
                    row5.ranking = null;
            }
		                    }
							if(colQtyInRs_tDBInput_5 < 4) {
								row5.gender = null;
							} else {
	                         		
           		tmpContent_tDBInput_5 = rs_tDBInput_5.getString(4);
            if(tmpContent_tDBInput_5 != null) {
            	if (talendToDBList_tDBInput_5 .contains(rsmd_tDBInput_5.getColumnTypeName(4).toUpperCase(java.util.Locale.ENGLISH))) {
            		row5.gender = FormatterUtils.formatUnwithE(tmpContent_tDBInput_5);
            	} else {
                	row5.gender = tmpContent_tDBInput_5;
                }
            } else {
                row5.gender = null;
            }
		                    }
							if(colQtyInRs_tDBInput_5 < 5) {
								row5.nationality = null;
							} else {
	                         		
           		tmpContent_tDBInput_5 = rs_tDBInput_5.getString(5);
            if(tmpContent_tDBInput_5 != null) {
            	if (talendToDBList_tDBInput_5 .contains(rsmd_tDBInput_5.getColumnTypeName(5).toUpperCase(java.util.Locale.ENGLISH))) {
            		row5.nationality = FormatterUtils.formatUnwithE(tmpContent_tDBInput_5);
            	} else {
                	row5.nationality = tmpContent_tDBInput_5;
                }
            } else {
                row5.nationality = null;
            }
		                    }
							if(colQtyInRs_tDBInput_5 < 6) {
								row5.birthdate = null;
							} else {
										
			row5.birthdate = mssqlGTU_tDBInput_5.getDate(rsmd_tDBInput_5, rs_tDBInput_5, 6);
			
		                    }
							if(colQtyInRs_tDBInput_5 < 7) {
								row5.height_cm = null;
							} else {
		                          
            row5.height_cm = rs_tDBInput_5.getShort(7);
            if(rs_tDBInput_5.wasNull()){
                    row5.height_cm = null;
            }
		                    }
							if(colQtyInRs_tDBInput_5 < 8) {
								row5.playing_hand = null;
							} else {
	                         		
           		tmpContent_tDBInput_5 = rs_tDBInput_5.getString(8);
            if(tmpContent_tDBInput_5 != null) {
            	if (talendToDBList_tDBInput_5 .contains(rsmd_tDBInput_5.getColumnTypeName(8).toUpperCase(java.util.Locale.ENGLISH))) {
            		row5.playing_hand = FormatterUtils.formatUnwithE(tmpContent_tDBInput_5);
            	} else {
                	row5.playing_hand = tmpContent_tDBInput_5;
                }
            } else {
                row5.playing_hand = null;
            }
		                    }
							if(colQtyInRs_tDBInput_5 < 9) {
								row5.court_side = null;
							} else {
	                         		
           		tmpContent_tDBInput_5 = rs_tDBInput_5.getString(9);
            if(tmpContent_tDBInput_5 != null) {
            	if (talendToDBList_tDBInput_5 .contains(rsmd_tDBInput_5.getColumnTypeName(9).toUpperCase(java.util.Locale.ENGLISH))) {
            		row5.court_side = FormatterUtils.formatUnwithE(tmpContent_tDBInput_5);
            	} else {
                	row5.court_side = tmpContent_tDBInput_5;
                }
            } else {
                row5.court_side = null;
            }
		                    }
							if(colQtyInRs_tDBInput_5 < 10) {
								row5.partner_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_5 = rs_tDBInput_5.getString(10);
            if(tmpContent_tDBInput_5 != null) {
            	if (talendToDBList_tDBInput_5 .contains(rsmd_tDBInput_5.getColumnTypeName(10).toUpperCase(java.util.Locale.ENGLISH))) {
            		row5.partner_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_5);
            	} else {
                	row5.partner_name = tmpContent_tDBInput_5;
                }
            } else {
                row5.partner_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_5 < 11) {
								row5.total_points = null;
							} else {
		                          
            row5.total_points = rs_tDBInput_5.getInt(11);
            if(rs_tDBInput_5.wasNull()){
                    row5.total_points = null;
            }
		                    }
					





 



/**
 * [tDBInput_5 begin ] stop
 */
	
	/**
	 * [tDBInput_5 main ] start
	 */

	

	
	
	currentComponent="tDBInput_5";

	

 


	tos_count_tDBInput_5++;

/**
 * [tDBInput_5 main ] stop
 */
	
	/**
	 * [tDBInput_5 process_data_begin ] start
	 */

	

	
	
	currentComponent="tDBInput_5";

	

 



/**
 * [tDBInput_5 process_data_begin ] stop
 */

	
	/**
	 * [tAdvancedHash_row5 main ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row5";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row5"
						
						);
					}
					


			   
			   

					row5Struct row5_HashRow = new row5Struct();
		   	   	   
				
				row5_HashRow.player_id = row5.player_id;
				
				row5_HashRow.full_name = row5.full_name;
				
				row5_HashRow.ranking = row5.ranking;
				
				row5_HashRow.gender = row5.gender;
				
				row5_HashRow.nationality = row5.nationality;
				
				row5_HashRow.birthdate = row5.birthdate;
				
				row5_HashRow.height_cm = row5.height_cm;
				
				row5_HashRow.playing_hand = row5.playing_hand;
				
				row5_HashRow.court_side = row5.court_side;
				
				row5_HashRow.partner_name = row5.partner_name;
				
				row5_HashRow.total_points = row5.total_points;
				
			tHash_Lookup_row5.put(row5_HashRow);
			
            




 


	tos_count_tAdvancedHash_row5++;

/**
 * [tAdvancedHash_row5 main ] stop
 */
	
	/**
	 * [tAdvancedHash_row5 process_data_begin ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row5";

	

 



/**
 * [tAdvancedHash_row5 process_data_begin ] stop
 */
	
	/**
	 * [tAdvancedHash_row5 process_data_end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row5";

	

 



/**
 * [tAdvancedHash_row5 process_data_end ] stop
 */



	
	/**
	 * [tDBInput_5 process_data_end ] start
	 */

	

	
	
	currentComponent="tDBInput_5";

	

 



/**
 * [tDBInput_5 process_data_end ] stop
 */
	
	/**
	 * [tDBInput_5 end ] start
	 */

	

	
	
	currentComponent="tDBInput_5";

	

	}
}finally{
	if (rs_tDBInput_5 != null) {
		rs_tDBInput_5.close();
	}
	if (stmt_tDBInput_5 != null) {
		stmt_tDBInput_5.close();
	}
		if(conn_tDBInput_5 != null && !conn_tDBInput_5.isClosed()) {
			
			conn_tDBInput_5.close();
			
			if("com.mysql.cj.jdbc.Driver".equals((String)globalMap.get("driverClass_"))
			    && routines.system.BundleUtils.inOSGi()) {
			        Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread").
			            getMethod("checkedShutdown").invoke(null, (Object[]) null);
			}
			
		}
}
globalMap.put("tDBInput_5_NB_LINE",nb_line_tDBInput_5);

 

ok_Hash.put("tDBInput_5", true);
end_Hash.put("tDBInput_5", System.currentTimeMillis());




/**
 * [tDBInput_5 end ] stop
 */

	
	/**
	 * [tAdvancedHash_row5 end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row5";

	

tHash_Lookup_row5.endPut();

				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row5");
			  	}
			  	
 

ok_Hash.put("tAdvancedHash_row5", true);
end_Hash.put("tAdvancedHash_row5", System.currentTimeMillis());




/**
 * [tAdvancedHash_row5 end ] stop
 */



				}//end the resume

				



	
			}catch(java.lang.Exception e){	
				
				TalendException te = new TalendException(e, currentComponent, globalMap);
				
				throw te;
			}catch(java.lang.Error error){	
				
					runStat.stopThreadStat();
				
				throw error;
			}finally{
				
				try{
					
	
	/**
	 * [tDBInput_5 finally ] start
	 */

	

	
	
	currentComponent="tDBInput_5";

	

 



/**
 * [tDBInput_5 finally ] stop
 */

	
	/**
	 * [tAdvancedHash_row5 finally ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row5";

	

 



/**
 * [tAdvancedHash_row5 finally ] stop
 */



				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tDBInput_5_SUBPROCESS_STATE", 1);
	}
	


public static class row6Struct implements routines.system.IPersistableComparableLookupRow<row6Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_FACT_MATCHS = new byte[0];
    static byte[] commonByteArray_DW_PADEL_FACT_MATCHS = new byte[0];
	protected static final int DEFAULT_HASHCODE = 1;
    protected static final int PRIME = 31;
    protected int hashCode = DEFAULT_HASHCODE;
    public boolean hashCodeDirty = true;

    public String loopKey;



	
			    public int player_id;

				public int getPlayer_id () {
					return this.player_id;
				}
				
			    public String full_name;

				public String getFull_name () {
					return this.full_name;
				}
				
			    public Integer ranking;

				public Integer getRanking () {
					return this.ranking;
				}
				
			    public String gender;

				public String getGender () {
					return this.gender;
				}
				
			    public String nationality;

				public String getNationality () {
					return this.nationality;
				}
				
			    public java.util.Date birthdate;

				public java.util.Date getBirthdate () {
					return this.birthdate;
				}
				
			    public Short height_cm;

				public Short getHeight_cm () {
					return this.height_cm;
				}
				
			    public String playing_hand;

				public String getPlaying_hand () {
					return this.playing_hand;
				}
				
			    public String court_side;

				public String getCourt_side () {
					return this.court_side;
				}
				
			    public String partner_name;

				public String getPartner_name () {
					return this.partner_name;
				}
				
			    public Integer total_points;

				public Integer getTotal_points () {
					return this.total_points;
				}
				


	@Override
	public int hashCode() {
		if (this.hashCodeDirty) {
			final int prime = PRIME;
			int result = DEFAULT_HASHCODE;
	
						result = prime * result + ((this.full_name == null) ? 0 : this.full_name.hashCode());
					
    		this.hashCode = result;
    		this.hashCodeDirty = false;
		}
		return this.hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final row6Struct other = (row6Struct) obj;
		
						if (this.full_name == null) {
							if (other.full_name != null)
								return false;
						
						} else if (!this.full_name.equals(other.full_name))
						
							return false;
					

		return true;
    }

	public void copyDataTo(row6Struct other) {

		other.player_id = this.player_id;
	            other.full_name = this.full_name;
	            other.ranking = this.ranking;
	            other.gender = this.gender;
	            other.nationality = this.nationality;
	            other.birthdate = this.birthdate;
	            other.height_cm = this.height_cm;
	            other.playing_hand = this.playing_hand;
	            other.court_side = this.court_side;
	            other.partner_name = this.partner_name;
	            other.total_points = this.total_points;
	            
	}

	public void copyKeysDataTo(row6Struct other) {

		other.full_name = this.full_name;
	            	
	}




	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_FACT_MATCHS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_MATCHS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_FACT_MATCHS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_MATCHS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length, utf8Charset);
		}
		return strReturn;
	}

    private void writeString(String str, ObjectOutputStream dos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
    }
    
    private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
    }
	private Integer readInteger(DataInputStream dis, ObjectInputStream ois) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
			intReturn = dis.readInt();
		}
		return intReturn;
	}
	
	private Integer readInteger(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		Integer intReturn;
        int length = 0;
        length = unmarshaller.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
			intReturn = unmarshaller.readInt();
		}
		return intReturn;
	}

	private void writeInteger(Integer intNum, DataOutputStream dos, ObjectOutputStream oos) throws IOException{
		if(intNum == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeInt(intNum);
    	}
	}
	
	private void writeInteger(Integer intNum, DataOutputStream dos,org.jboss.marshalling.Marshaller marshaller ) throws IOException{
		if(intNum == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeInt(intNum);
    	}
	}
	
	private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			byte[] byteArray = new byte[length];
			dis.read(byteArray);
			strReturn = new String(byteArray, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			byte[] byteArray = new byte[length];
			unmarshaller.read(byteArray);
			strReturn = new String(byteArray, utf8Charset);
		}
		return strReturn;
	}
	
	private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
	}

	private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
	}

	private java.util.Date readDate(DataInputStream dis, ObjectInputStream ois) throws IOException{
		java.util.Date dateReturn = null;
		int length = 0;
        length = dis.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(dis.readLong());
		}
		return dateReturn;
	}
	
	private java.util.Date readDate(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller ) throws IOException{
		java.util.Date dateReturn = null;
		int length = 0;
        length = unmarshaller.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(unmarshaller.readLong());
		}
		return dateReturn;
	}

	private void writeDate(java.util.Date date1, DataOutputStream dos, ObjectOutputStream oos) throws IOException{
		if(date1 == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeLong(date1.getTime());
    	}
	}
	
	private void writeDate(java.util.Date date1, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(date1 == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeLong(date1.getTime());
    	}
	}

    public void readKeysData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_MATCHS) {

        	try {

        		int length = 0;
		
					this.full_name = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_MATCHS) {

        	try {

        		int length = 0;
		
					this.full_name = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeKeysData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.full_name,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.full_name,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }



    /**
     * Fill Values data by reading ObjectInputStream.
     */
    public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
        try {

			int length = 0;
		
			            this.player_id = dis.readInt();
					
						this.ranking = readInteger(dis,ois);
					
						this.gender = readString(dis,ois);
					
						this.nationality = readString(dis,ois);
					
						this.birthdate = readDate(dis,ois);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.height_cm = null;
           				} else {
           			    	this.height_cm = dis.readShort();
           				}
					
						this.playing_hand = readString(dis,ois);
					
						this.court_side = readString(dis,ois);
					
						this.partner_name = readString(dis,ois);
					
						this.total_points = readInteger(dis,ois);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }
    
    public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
        try {
			int length = 0;
		
			            this.player_id = objectIn.readInt();
					
						this.ranking = readInteger(dis,objectIn);
					
						this.gender = readString(dis,objectIn);
					
						this.nationality = readString(dis,objectIn);
					
						this.birthdate = readDate(dis,objectIn);
					
			            length = objectIn.readByte();
           				if (length == -1) {
           	    			this.height_cm = null;
           				} else {
           			    	this.height_cm = objectIn.readShort();
           				}
					
						this.playing_hand = readString(dis,objectIn);
					
						this.court_side = readString(dis,objectIn);
					
						this.partner_name = readString(dis,objectIn);
					
						this.total_points = readInteger(dis,objectIn);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }

    /**
     * Return a byte array which represents Values data.
     */
    public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
        try {

		
		            	dos.writeInt(this.player_id);
					
					writeInteger(this.ranking, dos, oos);
					
						writeString(this.gender, dos, oos);
					
						writeString(this.nationality, dos, oos);
					
						writeDate(this.birthdate, dos, oos);
					
						if(this.height_cm == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeShort(this.height_cm);
		            	}
					
						writeString(this.playing_hand, dos, oos);
					
						writeString(this.court_side, dos, oos);
					
						writeString(this.partner_name, dos, oos);
					
					writeInteger(this.total_points, dos, oos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}

    }
    
    public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut){
                try {

		
					objectOut.writeInt(this.player_id);
					
					writeInteger(this.ranking, dos, objectOut);
					
						writeString(this.gender, dos, objectOut);
					
						writeString(this.nationality, dos, objectOut);
					
						writeDate(this.birthdate, dos, objectOut);
					
						if(this.height_cm == null) {
							objectOut.writeByte(-1);
						} else {
							objectOut.writeByte(0);
							objectOut.writeShort(this.height_cm);
		            	}
					
						writeString(this.playing_hand, dos, objectOut);
					
						writeString(this.court_side, dos, objectOut);
					
						writeString(this.partner_name, dos, objectOut);
					
					writeInteger(this.total_points, dos, objectOut);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}
    }


    
    public boolean supportMarshaller(){
        return true;
    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("player_id="+String.valueOf(player_id));
		sb.append(",full_name="+full_name);
		sb.append(",ranking="+String.valueOf(ranking));
		sb.append(",gender="+gender);
		sb.append(",nationality="+nationality);
		sb.append(",birthdate="+String.valueOf(birthdate));
		sb.append(",height_cm="+String.valueOf(height_cm));
		sb.append(",playing_hand="+playing_hand);
		sb.append(",court_side="+court_side);
		sb.append(",partner_name="+partner_name);
		sb.append(",total_points="+String.valueOf(total_points));
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(row6Struct other) {

		int returnValue = -1;
		
						returnValue = checkNullsAndCompare(this.full_name, other.full_name);
						if(returnValue != 0) {
							return returnValue;
						}

					
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}
public void tDBInput_6Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tDBInput_6_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { //start the resume
				globalResumeTicket = true;



		row6Struct row6 = new row6Struct();




	
	/**
	 * [tAdvancedHash_row6 begin ] start
	 */

	

	
		
		ok_Hash.put("tAdvancedHash_row6", false);
		start_Hash.put("tAdvancedHash_row6", System.currentTimeMillis());
		
	
	currentComponent="tAdvancedHash_row6";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row6");
					}
				
		int tos_count_tAdvancedHash_row6 = 0;
		

			   		// connection name:row6
			   		// source node:tDBInput_6 - inputs:(after_tFileInputExcel_1) outputs:(row6,row6) | target node:tAdvancedHash_row6 - inputs:(row6) outputs:()
			   		// linked node: tMap_1 - inputs:(row1,row2,row4,row5,row6,row7,row3) outputs:(fact)
			   
			   		org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row6 = 
			   			org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;
			   			
			   
	   			org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row6Struct> tHash_Lookup_row6 =org.talend.designer.components.lookup.memory.AdvancedMemoryLookup.
	   						<row6Struct>getLookup(matchingModeEnum_row6);
	   						   
		   	   	   globalMap.put("tHash_Lookup_row6", tHash_Lookup_row6);
		   	   	   
				
           

 



/**
 * [tAdvancedHash_row6 begin ] stop
 */



	
	/**
	 * [tDBInput_6 begin ] start
	 */

	

	
		
		ok_Hash.put("tDBInput_6", false);
		start_Hash.put("tDBInput_6", System.currentTimeMillis());
		
	
	currentComponent="tDBInput_6";

	
		int tos_count_tDBInput_6 = 0;
		
	
    
	
			org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_6 = org.talend.designer.components.util.mssql.MSSqlUtilFactory.getMSSqlGenerateTimestampUtil();
			
			java.util.List<String> talendToDBList_tDBInput_6 = new java.util.ArrayList();
			String[] talendToDBArray_tDBInput_6  = new String[]{"FLOAT","NUMERIC","NUMERIC IDENTITY","DECIMAL","DECIMAL IDENTITY","REAL"}; 
			java.util.Collections.addAll(talendToDBList_tDBInput_6, talendToDBArray_tDBInput_6); 
		    int nb_line_tDBInput_6 = 0;
		    java.sql.Connection conn_tDBInput_6 = null;
				String driverClass_tDBInput_6 = "net.sourceforge.jtds.jdbc.Driver";
			    java.lang.Class jdbcclazz_tDBInput_6 = java.lang.Class.forName(driverClass_tDBInput_6);
				String dbUser_tDBInput_6 = "Padelle";
				
				 
	final String decryptedPassword_tDBInput_6 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:Sa5YCu4xe1MFxzphLwYfDq7VqSw2o+m1Nbke6GxHjSxtIjc=");
				
				String dbPwd_tDBInput_6 = decryptedPassword_tDBInput_6;
				
		    String port_tDBInput_6 = "1433";
		    String dbname_tDBInput_6 = "DW_padel" ;
			String url_tDBInput_6 = "jdbc:jtds:sqlserver://" + "DESKTOP-QJ70MNR" ;
		    if (!"".equals(port_tDBInput_6)) {
		    	url_tDBInput_6 += ":" + "1433";
		    }
		    if (!"".equals(dbname_tDBInput_6)) {
				url_tDBInput_6 += "//" + "DW_padel"; 
		    }
		    url_tDBInput_6 += ";appName=" + projectName + ";" + "";
		    String dbschema_tDBInput_6 = "";
				
				conn_tDBInput_6 = java.sql.DriverManager.getConnection(url_tDBInput_6,dbUser_tDBInput_6,dbPwd_tDBInput_6);
		        
		    
			java.sql.Statement stmt_tDBInput_6 = conn_tDBInput_6.createStatement();

		    String dbquery_tDBInput_6 = "SELECT dim_player.player_id,\n		dim_player.full_name,\n		dim_player.ranking,\n		dim_player.gender,\n		dim_player.nationalit"
+"y,\n		dim_player.birthdate,\n		dim_player.height_cm,\n		dim_player.playing_hand,\n		dim_player.court_side,\n		dim_player.part"
+"ner_name,\n		dim_player.total_points\nFROM	dim_player";
		    

            	globalMap.put("tDBInput_6_QUERY",dbquery_tDBInput_6);
		    java.sql.ResultSet rs_tDBInput_6 = null;

		    try {
		    	rs_tDBInput_6 = stmt_tDBInput_6.executeQuery(dbquery_tDBInput_6);
		    	java.sql.ResultSetMetaData rsmd_tDBInput_6 = rs_tDBInput_6.getMetaData();
		    	int colQtyInRs_tDBInput_6 = rsmd_tDBInput_6.getColumnCount();

		    String tmpContent_tDBInput_6 = null;
		    
		    
		    while (rs_tDBInput_6.next()) {
		        nb_line_tDBInput_6++;
		        
							if(colQtyInRs_tDBInput_6 < 1) {
								row6.player_id = 0;
							} else {
		                          
            row6.player_id = rs_tDBInput_6.getInt(1);
            if(rs_tDBInput_6.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
							if(colQtyInRs_tDBInput_6 < 2) {
								row6.full_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_6 = rs_tDBInput_6.getString(2);
            if(tmpContent_tDBInput_6 != null) {
            	if (talendToDBList_tDBInput_6 .contains(rsmd_tDBInput_6.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
            		row6.full_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_6);
            	} else {
                	row6.full_name = tmpContent_tDBInput_6;
                }
            } else {
                row6.full_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_6 < 3) {
								row6.ranking = null;
							} else {
		                          
            row6.ranking = rs_tDBInput_6.getInt(3);
            if(rs_tDBInput_6.wasNull()){
                    row6.ranking = null;
            }
		                    }
							if(colQtyInRs_tDBInput_6 < 4) {
								row6.gender = null;
							} else {
	                         		
           		tmpContent_tDBInput_6 = rs_tDBInput_6.getString(4);
            if(tmpContent_tDBInput_6 != null) {
            	if (talendToDBList_tDBInput_6 .contains(rsmd_tDBInput_6.getColumnTypeName(4).toUpperCase(java.util.Locale.ENGLISH))) {
            		row6.gender = FormatterUtils.formatUnwithE(tmpContent_tDBInput_6);
            	} else {
                	row6.gender = tmpContent_tDBInput_6;
                }
            } else {
                row6.gender = null;
            }
		                    }
							if(colQtyInRs_tDBInput_6 < 5) {
								row6.nationality = null;
							} else {
	                         		
           		tmpContent_tDBInput_6 = rs_tDBInput_6.getString(5);
            if(tmpContent_tDBInput_6 != null) {
            	if (talendToDBList_tDBInput_6 .contains(rsmd_tDBInput_6.getColumnTypeName(5).toUpperCase(java.util.Locale.ENGLISH))) {
            		row6.nationality = FormatterUtils.formatUnwithE(tmpContent_tDBInput_6);
            	} else {
                	row6.nationality = tmpContent_tDBInput_6;
                }
            } else {
                row6.nationality = null;
            }
		                    }
							if(colQtyInRs_tDBInput_6 < 6) {
								row6.birthdate = null;
							} else {
										
			row6.birthdate = mssqlGTU_tDBInput_6.getDate(rsmd_tDBInput_6, rs_tDBInput_6, 6);
			
		                    }
							if(colQtyInRs_tDBInput_6 < 7) {
								row6.height_cm = null;
							} else {
		                          
            row6.height_cm = rs_tDBInput_6.getShort(7);
            if(rs_tDBInput_6.wasNull()){
                    row6.height_cm = null;
            }
		                    }
							if(colQtyInRs_tDBInput_6 < 8) {
								row6.playing_hand = null;
							} else {
	                         		
           		tmpContent_tDBInput_6 = rs_tDBInput_6.getString(8);
            if(tmpContent_tDBInput_6 != null) {
            	if (talendToDBList_tDBInput_6 .contains(rsmd_tDBInput_6.getColumnTypeName(8).toUpperCase(java.util.Locale.ENGLISH))) {
            		row6.playing_hand = FormatterUtils.formatUnwithE(tmpContent_tDBInput_6);
            	} else {
                	row6.playing_hand = tmpContent_tDBInput_6;
                }
            } else {
                row6.playing_hand = null;
            }
		                    }
							if(colQtyInRs_tDBInput_6 < 9) {
								row6.court_side = null;
							} else {
	                         		
           		tmpContent_tDBInput_6 = rs_tDBInput_6.getString(9);
            if(tmpContent_tDBInput_6 != null) {
            	if (talendToDBList_tDBInput_6 .contains(rsmd_tDBInput_6.getColumnTypeName(9).toUpperCase(java.util.Locale.ENGLISH))) {
            		row6.court_side = FormatterUtils.formatUnwithE(tmpContent_tDBInput_6);
            	} else {
                	row6.court_side = tmpContent_tDBInput_6;
                }
            } else {
                row6.court_side = null;
            }
		                    }
							if(colQtyInRs_tDBInput_6 < 10) {
								row6.partner_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_6 = rs_tDBInput_6.getString(10);
            if(tmpContent_tDBInput_6 != null) {
            	if (talendToDBList_tDBInput_6 .contains(rsmd_tDBInput_6.getColumnTypeName(10).toUpperCase(java.util.Locale.ENGLISH))) {
            		row6.partner_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_6);
            	} else {
                	row6.partner_name = tmpContent_tDBInput_6;
                }
            } else {
                row6.partner_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_6 < 11) {
								row6.total_points = null;
							} else {
		                          
            row6.total_points = rs_tDBInput_6.getInt(11);
            if(rs_tDBInput_6.wasNull()){
                    row6.total_points = null;
            }
		                    }
					





 



/**
 * [tDBInput_6 begin ] stop
 */
	
	/**
	 * [tDBInput_6 main ] start
	 */

	

	
	
	currentComponent="tDBInput_6";

	

 


	tos_count_tDBInput_6++;

/**
 * [tDBInput_6 main ] stop
 */
	
	/**
	 * [tDBInput_6 process_data_begin ] start
	 */

	

	
	
	currentComponent="tDBInput_6";

	

 



/**
 * [tDBInput_6 process_data_begin ] stop
 */

	
	/**
	 * [tAdvancedHash_row6 main ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row6";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row6"
						
						);
					}
					


			   
			   

					row6Struct row6_HashRow = new row6Struct();
		   	   	   
				
				row6_HashRow.player_id = row6.player_id;
				
				row6_HashRow.full_name = row6.full_name;
				
				row6_HashRow.ranking = row6.ranking;
				
				row6_HashRow.gender = row6.gender;
				
				row6_HashRow.nationality = row6.nationality;
				
				row6_HashRow.birthdate = row6.birthdate;
				
				row6_HashRow.height_cm = row6.height_cm;
				
				row6_HashRow.playing_hand = row6.playing_hand;
				
				row6_HashRow.court_side = row6.court_side;
				
				row6_HashRow.partner_name = row6.partner_name;
				
				row6_HashRow.total_points = row6.total_points;
				
			tHash_Lookup_row6.put(row6_HashRow);
			
            




 


	tos_count_tAdvancedHash_row6++;

/**
 * [tAdvancedHash_row6 main ] stop
 */
	
	/**
	 * [tAdvancedHash_row6 process_data_begin ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row6";

	

 



/**
 * [tAdvancedHash_row6 process_data_begin ] stop
 */
	
	/**
	 * [tAdvancedHash_row6 process_data_end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row6";

	

 



/**
 * [tAdvancedHash_row6 process_data_end ] stop
 */



	
	/**
	 * [tDBInput_6 process_data_end ] start
	 */

	

	
	
	currentComponent="tDBInput_6";

	

 



/**
 * [tDBInput_6 process_data_end ] stop
 */
	
	/**
	 * [tDBInput_6 end ] start
	 */

	

	
	
	currentComponent="tDBInput_6";

	

	}
}finally{
	if (rs_tDBInput_6 != null) {
		rs_tDBInput_6.close();
	}
	if (stmt_tDBInput_6 != null) {
		stmt_tDBInput_6.close();
	}
		if(conn_tDBInput_6 != null && !conn_tDBInput_6.isClosed()) {
			
			conn_tDBInput_6.close();
			
			if("com.mysql.cj.jdbc.Driver".equals((String)globalMap.get("driverClass_"))
			    && routines.system.BundleUtils.inOSGi()) {
			        Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread").
			            getMethod("checkedShutdown").invoke(null, (Object[]) null);
			}
			
		}
}
globalMap.put("tDBInput_6_NB_LINE",nb_line_tDBInput_6);

 

ok_Hash.put("tDBInput_6", true);
end_Hash.put("tDBInput_6", System.currentTimeMillis());




/**
 * [tDBInput_6 end ] stop
 */

	
	/**
	 * [tAdvancedHash_row6 end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row6";

	

tHash_Lookup_row6.endPut();

				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row6");
			  	}
			  	
 

ok_Hash.put("tAdvancedHash_row6", true);
end_Hash.put("tAdvancedHash_row6", System.currentTimeMillis());




/**
 * [tAdvancedHash_row6 end ] stop
 */



				}//end the resume

				



	
			}catch(java.lang.Exception e){	
				
				TalendException te = new TalendException(e, currentComponent, globalMap);
				
				throw te;
			}catch(java.lang.Error error){	
				
					runStat.stopThreadStat();
				
				throw error;
			}finally{
				
				try{
					
	
	/**
	 * [tDBInput_6 finally ] start
	 */

	

	
	
	currentComponent="tDBInput_6";

	

 



/**
 * [tDBInput_6 finally ] stop
 */

	
	/**
	 * [tAdvancedHash_row6 finally ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row6";

	

 



/**
 * [tAdvancedHash_row6 finally ] stop
 */



				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tDBInput_6_SUBPROCESS_STATE", 1);
	}
	


public static class row7Struct implements routines.system.IPersistableComparableLookupRow<row7Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_FACT_MATCHS = new byte[0];
    static byte[] commonByteArray_DW_PADEL_FACT_MATCHS = new byte[0];
	protected static final int DEFAULT_HASHCODE = 1;
    protected static final int PRIME = 31;
    protected int hashCode = DEFAULT_HASHCODE;
    public boolean hashCodeDirty = true;

    public String loopKey;



	
			    public int player_id;

				public int getPlayer_id () {
					return this.player_id;
				}
				
			    public String full_name;

				public String getFull_name () {
					return this.full_name;
				}
				
			    public Integer ranking;

				public Integer getRanking () {
					return this.ranking;
				}
				
			    public String gender;

				public String getGender () {
					return this.gender;
				}
				
			    public String nationality;

				public String getNationality () {
					return this.nationality;
				}
				
			    public java.util.Date birthdate;

				public java.util.Date getBirthdate () {
					return this.birthdate;
				}
				
			    public Short height_cm;

				public Short getHeight_cm () {
					return this.height_cm;
				}
				
			    public String playing_hand;

				public String getPlaying_hand () {
					return this.playing_hand;
				}
				
			    public String court_side;

				public String getCourt_side () {
					return this.court_side;
				}
				
			    public String partner_name;

				public String getPartner_name () {
					return this.partner_name;
				}
				
			    public Integer total_points;

				public Integer getTotal_points () {
					return this.total_points;
				}
				


	@Override
	public int hashCode() {
		if (this.hashCodeDirty) {
			final int prime = PRIME;
			int result = DEFAULT_HASHCODE;
	
						result = prime * result + ((this.full_name == null) ? 0 : this.full_name.hashCode());
					
    		this.hashCode = result;
    		this.hashCodeDirty = false;
		}
		return this.hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final row7Struct other = (row7Struct) obj;
		
						if (this.full_name == null) {
							if (other.full_name != null)
								return false;
						
						} else if (!this.full_name.equals(other.full_name))
						
							return false;
					

		return true;
    }

	public void copyDataTo(row7Struct other) {

		other.player_id = this.player_id;
	            other.full_name = this.full_name;
	            other.ranking = this.ranking;
	            other.gender = this.gender;
	            other.nationality = this.nationality;
	            other.birthdate = this.birthdate;
	            other.height_cm = this.height_cm;
	            other.playing_hand = this.playing_hand;
	            other.court_side = this.court_side;
	            other.partner_name = this.partner_name;
	            other.total_points = this.total_points;
	            
	}

	public void copyKeysDataTo(row7Struct other) {

		other.full_name = this.full_name;
	            	
	}




	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_FACT_MATCHS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_MATCHS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_FACT_MATCHS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_MATCHS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length, utf8Charset);
		}
		return strReturn;
	}

    private void writeString(String str, ObjectOutputStream dos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
    }
    
    private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
    }
	private Integer readInteger(DataInputStream dis, ObjectInputStream ois) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
			intReturn = dis.readInt();
		}
		return intReturn;
	}
	
	private Integer readInteger(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		Integer intReturn;
        int length = 0;
        length = unmarshaller.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
			intReturn = unmarshaller.readInt();
		}
		return intReturn;
	}

	private void writeInteger(Integer intNum, DataOutputStream dos, ObjectOutputStream oos) throws IOException{
		if(intNum == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeInt(intNum);
    	}
	}
	
	private void writeInteger(Integer intNum, DataOutputStream dos,org.jboss.marshalling.Marshaller marshaller ) throws IOException{
		if(intNum == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeInt(intNum);
    	}
	}
	
	private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			byte[] byteArray = new byte[length];
			dis.read(byteArray);
			strReturn = new String(byteArray, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			byte[] byteArray = new byte[length];
			unmarshaller.read(byteArray);
			strReturn = new String(byteArray, utf8Charset);
		}
		return strReturn;
	}
	
	private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
	}

	private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
	}

	private java.util.Date readDate(DataInputStream dis, ObjectInputStream ois) throws IOException{
		java.util.Date dateReturn = null;
		int length = 0;
        length = dis.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(dis.readLong());
		}
		return dateReturn;
	}
	
	private java.util.Date readDate(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller ) throws IOException{
		java.util.Date dateReturn = null;
		int length = 0;
        length = unmarshaller.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(unmarshaller.readLong());
		}
		return dateReturn;
	}

	private void writeDate(java.util.Date date1, DataOutputStream dos, ObjectOutputStream oos) throws IOException{
		if(date1 == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeLong(date1.getTime());
    	}
	}
	
	private void writeDate(java.util.Date date1, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(date1 == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeLong(date1.getTime());
    	}
	}

    public void readKeysData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_MATCHS) {

        	try {

        		int length = 0;
		
					this.full_name = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_MATCHS) {

        	try {

        		int length = 0;
		
					this.full_name = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeKeysData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.full_name,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.full_name,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }



    /**
     * Fill Values data by reading ObjectInputStream.
     */
    public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
        try {

			int length = 0;
		
			            this.player_id = dis.readInt();
					
						this.ranking = readInteger(dis,ois);
					
						this.gender = readString(dis,ois);
					
						this.nationality = readString(dis,ois);
					
						this.birthdate = readDate(dis,ois);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.height_cm = null;
           				} else {
           			    	this.height_cm = dis.readShort();
           				}
					
						this.playing_hand = readString(dis,ois);
					
						this.court_side = readString(dis,ois);
					
						this.partner_name = readString(dis,ois);
					
						this.total_points = readInteger(dis,ois);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }
    
    public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
        try {
			int length = 0;
		
			            this.player_id = objectIn.readInt();
					
						this.ranking = readInteger(dis,objectIn);
					
						this.gender = readString(dis,objectIn);
					
						this.nationality = readString(dis,objectIn);
					
						this.birthdate = readDate(dis,objectIn);
					
			            length = objectIn.readByte();
           				if (length == -1) {
           	    			this.height_cm = null;
           				} else {
           			    	this.height_cm = objectIn.readShort();
           				}
					
						this.playing_hand = readString(dis,objectIn);
					
						this.court_side = readString(dis,objectIn);
					
						this.partner_name = readString(dis,objectIn);
					
						this.total_points = readInteger(dis,objectIn);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }

    /**
     * Return a byte array which represents Values data.
     */
    public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
        try {

		
		            	dos.writeInt(this.player_id);
					
					writeInteger(this.ranking, dos, oos);
					
						writeString(this.gender, dos, oos);
					
						writeString(this.nationality, dos, oos);
					
						writeDate(this.birthdate, dos, oos);
					
						if(this.height_cm == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeShort(this.height_cm);
		            	}
					
						writeString(this.playing_hand, dos, oos);
					
						writeString(this.court_side, dos, oos);
					
						writeString(this.partner_name, dos, oos);
					
					writeInteger(this.total_points, dos, oos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}

    }
    
    public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut){
                try {

		
					objectOut.writeInt(this.player_id);
					
					writeInteger(this.ranking, dos, objectOut);
					
						writeString(this.gender, dos, objectOut);
					
						writeString(this.nationality, dos, objectOut);
					
						writeDate(this.birthdate, dos, objectOut);
					
						if(this.height_cm == null) {
							objectOut.writeByte(-1);
						} else {
							objectOut.writeByte(0);
							objectOut.writeShort(this.height_cm);
		            	}
					
						writeString(this.playing_hand, dos, objectOut);
					
						writeString(this.court_side, dos, objectOut);
					
						writeString(this.partner_name, dos, objectOut);
					
					writeInteger(this.total_points, dos, objectOut);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}
    }


    
    public boolean supportMarshaller(){
        return true;
    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("player_id="+String.valueOf(player_id));
		sb.append(",full_name="+full_name);
		sb.append(",ranking="+String.valueOf(ranking));
		sb.append(",gender="+gender);
		sb.append(",nationality="+nationality);
		sb.append(",birthdate="+String.valueOf(birthdate));
		sb.append(",height_cm="+String.valueOf(height_cm));
		sb.append(",playing_hand="+playing_hand);
		sb.append(",court_side="+court_side);
		sb.append(",partner_name="+partner_name);
		sb.append(",total_points="+String.valueOf(total_points));
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(row7Struct other) {

		int returnValue = -1;
		
						returnValue = checkNullsAndCompare(this.full_name, other.full_name);
						if(returnValue != 0) {
							return returnValue;
						}

					
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}
public void tDBInput_7Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tDBInput_7_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { //start the resume
				globalResumeTicket = true;



		row7Struct row7 = new row7Struct();




	
	/**
	 * [tAdvancedHash_row7 begin ] start
	 */

	

	
		
		ok_Hash.put("tAdvancedHash_row7", false);
		start_Hash.put("tAdvancedHash_row7", System.currentTimeMillis());
		
	
	currentComponent="tAdvancedHash_row7";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row7");
					}
				
		int tos_count_tAdvancedHash_row7 = 0;
		

			   		// connection name:row7
			   		// source node:tDBInput_7 - inputs:(after_tFileInputExcel_1) outputs:(row7,row7) | target node:tAdvancedHash_row7 - inputs:(row7) outputs:()
			   		// linked node: tMap_1 - inputs:(row1,row2,row4,row5,row6,row7,row3) outputs:(fact)
			   
			   		org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row7 = 
			   			org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;
			   			
			   
	   			org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row7Struct> tHash_Lookup_row7 =org.talend.designer.components.lookup.memory.AdvancedMemoryLookup.
	   						<row7Struct>getLookup(matchingModeEnum_row7);
	   						   
		   	   	   globalMap.put("tHash_Lookup_row7", tHash_Lookup_row7);
		   	   	   
				
           

 



/**
 * [tAdvancedHash_row7 begin ] stop
 */



	
	/**
	 * [tDBInput_7 begin ] start
	 */

	

	
		
		ok_Hash.put("tDBInput_7", false);
		start_Hash.put("tDBInput_7", System.currentTimeMillis());
		
	
	currentComponent="tDBInput_7";

	
		int tos_count_tDBInput_7 = 0;
		
	
    
	
			org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_7 = org.talend.designer.components.util.mssql.MSSqlUtilFactory.getMSSqlGenerateTimestampUtil();
			
			java.util.List<String> talendToDBList_tDBInput_7 = new java.util.ArrayList();
			String[] talendToDBArray_tDBInput_7  = new String[]{"FLOAT","NUMERIC","NUMERIC IDENTITY","DECIMAL","DECIMAL IDENTITY","REAL"}; 
			java.util.Collections.addAll(talendToDBList_tDBInput_7, talendToDBArray_tDBInput_7); 
		    int nb_line_tDBInput_7 = 0;
		    java.sql.Connection conn_tDBInput_7 = null;
				String driverClass_tDBInput_7 = "net.sourceforge.jtds.jdbc.Driver";
			    java.lang.Class jdbcclazz_tDBInput_7 = java.lang.Class.forName(driverClass_tDBInput_7);
				String dbUser_tDBInput_7 = "Padelle";
				
				 
	final String decryptedPassword_tDBInput_7 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:Wha/EXAbsNkf0Jc4SfCTv+xFjjZvAn4NZhY2n02IbqOuhOU=");
				
				String dbPwd_tDBInput_7 = decryptedPassword_tDBInput_7;
				
		    String port_tDBInput_7 = "1433";
		    String dbname_tDBInput_7 = "DW_padel" ;
			String url_tDBInput_7 = "jdbc:jtds:sqlserver://" + "DESKTOP-QJ70MNR" ;
		    if (!"".equals(port_tDBInput_7)) {
		    	url_tDBInput_7 += ":" + "1433";
		    }
		    if (!"".equals(dbname_tDBInput_7)) {
				url_tDBInput_7 += "//" + "DW_padel"; 
		    }
		    url_tDBInput_7 += ";appName=" + projectName + ";" + "";
		    String dbschema_tDBInput_7 = "";
				
				conn_tDBInput_7 = java.sql.DriverManager.getConnection(url_tDBInput_7,dbUser_tDBInput_7,dbPwd_tDBInput_7);
		        
		    
			java.sql.Statement stmt_tDBInput_7 = conn_tDBInput_7.createStatement();

		    String dbquery_tDBInput_7 = "SELECT dim_player.player_id,\n		dim_player.full_name,\n		dim_player.ranking,\n		dim_player.gender,\n		dim_player.nationalit"
+"y,\n		dim_player.birthdate,\n		dim_player.height_cm,\n		dim_player.playing_hand,\n		dim_player.court_side,\n		dim_player.part"
+"ner_name,\n		dim_player.total_points\nFROM	dim_player";
		    

            	globalMap.put("tDBInput_7_QUERY",dbquery_tDBInput_7);
		    java.sql.ResultSet rs_tDBInput_7 = null;

		    try {
		    	rs_tDBInput_7 = stmt_tDBInput_7.executeQuery(dbquery_tDBInput_7);
		    	java.sql.ResultSetMetaData rsmd_tDBInput_7 = rs_tDBInput_7.getMetaData();
		    	int colQtyInRs_tDBInput_7 = rsmd_tDBInput_7.getColumnCount();

		    String tmpContent_tDBInput_7 = null;
		    
		    
		    while (rs_tDBInput_7.next()) {
		        nb_line_tDBInput_7++;
		        
							if(colQtyInRs_tDBInput_7 < 1) {
								row7.player_id = 0;
							} else {
		                          
            row7.player_id = rs_tDBInput_7.getInt(1);
            if(rs_tDBInput_7.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
							if(colQtyInRs_tDBInput_7 < 2) {
								row7.full_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_7 = rs_tDBInput_7.getString(2);
            if(tmpContent_tDBInput_7 != null) {
            	if (talendToDBList_tDBInput_7 .contains(rsmd_tDBInput_7.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
            		row7.full_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_7);
            	} else {
                	row7.full_name = tmpContent_tDBInput_7;
                }
            } else {
                row7.full_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_7 < 3) {
								row7.ranking = null;
							} else {
		                          
            row7.ranking = rs_tDBInput_7.getInt(3);
            if(rs_tDBInput_7.wasNull()){
                    row7.ranking = null;
            }
		                    }
							if(colQtyInRs_tDBInput_7 < 4) {
								row7.gender = null;
							} else {
	                         		
           		tmpContent_tDBInput_7 = rs_tDBInput_7.getString(4);
            if(tmpContent_tDBInput_7 != null) {
            	if (talendToDBList_tDBInput_7 .contains(rsmd_tDBInput_7.getColumnTypeName(4).toUpperCase(java.util.Locale.ENGLISH))) {
            		row7.gender = FormatterUtils.formatUnwithE(tmpContent_tDBInput_7);
            	} else {
                	row7.gender = tmpContent_tDBInput_7;
                }
            } else {
                row7.gender = null;
            }
		                    }
							if(colQtyInRs_tDBInput_7 < 5) {
								row7.nationality = null;
							} else {
	                         		
           		tmpContent_tDBInput_7 = rs_tDBInput_7.getString(5);
            if(tmpContent_tDBInput_7 != null) {
            	if (talendToDBList_tDBInput_7 .contains(rsmd_tDBInput_7.getColumnTypeName(5).toUpperCase(java.util.Locale.ENGLISH))) {
            		row7.nationality = FormatterUtils.formatUnwithE(tmpContent_tDBInput_7);
            	} else {
                	row7.nationality = tmpContent_tDBInput_7;
                }
            } else {
                row7.nationality = null;
            }
		                    }
							if(colQtyInRs_tDBInput_7 < 6) {
								row7.birthdate = null;
							} else {
										
			row7.birthdate = mssqlGTU_tDBInput_7.getDate(rsmd_tDBInput_7, rs_tDBInput_7, 6);
			
		                    }
							if(colQtyInRs_tDBInput_7 < 7) {
								row7.height_cm = null;
							} else {
		                          
            row7.height_cm = rs_tDBInput_7.getShort(7);
            if(rs_tDBInput_7.wasNull()){
                    row7.height_cm = null;
            }
		                    }
							if(colQtyInRs_tDBInput_7 < 8) {
								row7.playing_hand = null;
							} else {
	                         		
           		tmpContent_tDBInput_7 = rs_tDBInput_7.getString(8);
            if(tmpContent_tDBInput_7 != null) {
            	if (talendToDBList_tDBInput_7 .contains(rsmd_tDBInput_7.getColumnTypeName(8).toUpperCase(java.util.Locale.ENGLISH))) {
            		row7.playing_hand = FormatterUtils.formatUnwithE(tmpContent_tDBInput_7);
            	} else {
                	row7.playing_hand = tmpContent_tDBInput_7;
                }
            } else {
                row7.playing_hand = null;
            }
		                    }
							if(colQtyInRs_tDBInput_7 < 9) {
								row7.court_side = null;
							} else {
	                         		
           		tmpContent_tDBInput_7 = rs_tDBInput_7.getString(9);
            if(tmpContent_tDBInput_7 != null) {
            	if (talendToDBList_tDBInput_7 .contains(rsmd_tDBInput_7.getColumnTypeName(9).toUpperCase(java.util.Locale.ENGLISH))) {
            		row7.court_side = FormatterUtils.formatUnwithE(tmpContent_tDBInput_7);
            	} else {
                	row7.court_side = tmpContent_tDBInput_7;
                }
            } else {
                row7.court_side = null;
            }
		                    }
							if(colQtyInRs_tDBInput_7 < 10) {
								row7.partner_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_7 = rs_tDBInput_7.getString(10);
            if(tmpContent_tDBInput_7 != null) {
            	if (talendToDBList_tDBInput_7 .contains(rsmd_tDBInput_7.getColumnTypeName(10).toUpperCase(java.util.Locale.ENGLISH))) {
            		row7.partner_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_7);
            	} else {
                	row7.partner_name = tmpContent_tDBInput_7;
                }
            } else {
                row7.partner_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_7 < 11) {
								row7.total_points = null;
							} else {
		                          
            row7.total_points = rs_tDBInput_7.getInt(11);
            if(rs_tDBInput_7.wasNull()){
                    row7.total_points = null;
            }
		                    }
					





 



/**
 * [tDBInput_7 begin ] stop
 */
	
	/**
	 * [tDBInput_7 main ] start
	 */

	

	
	
	currentComponent="tDBInput_7";

	

 


	tos_count_tDBInput_7++;

/**
 * [tDBInput_7 main ] stop
 */
	
	/**
	 * [tDBInput_7 process_data_begin ] start
	 */

	

	
	
	currentComponent="tDBInput_7";

	

 



/**
 * [tDBInput_7 process_data_begin ] stop
 */

	
	/**
	 * [tAdvancedHash_row7 main ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row7";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row7"
						
						);
					}
					


			   
			   

					row7Struct row7_HashRow = new row7Struct();
		   	   	   
				
				row7_HashRow.player_id = row7.player_id;
				
				row7_HashRow.full_name = row7.full_name;
				
				row7_HashRow.ranking = row7.ranking;
				
				row7_HashRow.gender = row7.gender;
				
				row7_HashRow.nationality = row7.nationality;
				
				row7_HashRow.birthdate = row7.birthdate;
				
				row7_HashRow.height_cm = row7.height_cm;
				
				row7_HashRow.playing_hand = row7.playing_hand;
				
				row7_HashRow.court_side = row7.court_side;
				
				row7_HashRow.partner_name = row7.partner_name;
				
				row7_HashRow.total_points = row7.total_points;
				
			tHash_Lookup_row7.put(row7_HashRow);
			
            




 


	tos_count_tAdvancedHash_row7++;

/**
 * [tAdvancedHash_row7 main ] stop
 */
	
	/**
	 * [tAdvancedHash_row7 process_data_begin ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row7";

	

 



/**
 * [tAdvancedHash_row7 process_data_begin ] stop
 */
	
	/**
	 * [tAdvancedHash_row7 process_data_end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row7";

	

 



/**
 * [tAdvancedHash_row7 process_data_end ] stop
 */



	
	/**
	 * [tDBInput_7 process_data_end ] start
	 */

	

	
	
	currentComponent="tDBInput_7";

	

 



/**
 * [tDBInput_7 process_data_end ] stop
 */
	
	/**
	 * [tDBInput_7 end ] start
	 */

	

	
	
	currentComponent="tDBInput_7";

	

	}
}finally{
	if (rs_tDBInput_7 != null) {
		rs_tDBInput_7.close();
	}
	if (stmt_tDBInput_7 != null) {
		stmt_tDBInput_7.close();
	}
		if(conn_tDBInput_7 != null && !conn_tDBInput_7.isClosed()) {
			
			conn_tDBInput_7.close();
			
			if("com.mysql.cj.jdbc.Driver".equals((String)globalMap.get("driverClass_"))
			    && routines.system.BundleUtils.inOSGi()) {
			        Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread").
			            getMethod("checkedShutdown").invoke(null, (Object[]) null);
			}
			
		}
}
globalMap.put("tDBInput_7_NB_LINE",nb_line_tDBInput_7);

 

ok_Hash.put("tDBInput_7", true);
end_Hash.put("tDBInput_7", System.currentTimeMillis());




/**
 * [tDBInput_7 end ] stop
 */

	
	/**
	 * [tAdvancedHash_row7 end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row7";

	

tHash_Lookup_row7.endPut();

				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row7");
			  	}
			  	
 

ok_Hash.put("tAdvancedHash_row7", true);
end_Hash.put("tAdvancedHash_row7", System.currentTimeMillis());




/**
 * [tAdvancedHash_row7 end ] stop
 */



				}//end the resume

				



	
			}catch(java.lang.Exception e){	
				
				TalendException te = new TalendException(e, currentComponent, globalMap);
				
				throw te;
			}catch(java.lang.Error error){	
				
					runStat.stopThreadStat();
				
				throw error;
			}finally{
				
				try{
					
	
	/**
	 * [tDBInput_7 finally ] start
	 */

	

	
	
	currentComponent="tDBInput_7";

	

 



/**
 * [tDBInput_7 finally ] stop
 */

	
	/**
	 * [tAdvancedHash_row7 finally ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row7";

	

 



/**
 * [tAdvancedHash_row7 finally ] stop
 */



				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tDBInput_7_SUBPROCESS_STATE", 1);
	}
	


public static class row3Struct implements routines.system.IPersistableComparableLookupRow<row3Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_FACT_MATCHS = new byte[0];
    static byte[] commonByteArray_DW_PADEL_FACT_MATCHS = new byte[0];
	protected static final int DEFAULT_HASHCODE = 1;
    protected static final int PRIME = 31;
    protected int hashCode = DEFAULT_HASHCODE;
    public boolean hashCodeDirty = true;

    public String loopKey;



	
			    public int tournament_id;

				public int getTournament_id () {
					return this.tournament_id;
				}
				
			    public String tournament_name;

				public String getTournament_name () {
					return this.tournament_name;
				}
				
			    public String location;

				public String getLocation () {
					return this.location;
				}
				
			    public Integer prize_money;

				public Integer getPrize_money () {
					return this.prize_money;
				}
				
			    public String balls_brand;

				public String getBalls_brand () {
					return this.balls_brand;
				}
				
			    public String venue_type;

				public String getVenue_type () {
					return this.venue_type;
				}
				
			    public String court_manufacturer;

				public String getCourt_manufacturer () {
					return this.court_manufacturer;
				}
				
			    public String turf_type;

				public String getTurf_type () {
					return this.turf_type;
				}
				
			    public java.util.Date date;

				public java.util.Date getDate () {
					return this.date;
				}
				


	@Override
	public int hashCode() {
		if (this.hashCodeDirty) {
			final int prime = PRIME;
			int result = DEFAULT_HASHCODE;
	
						result = prime * result + ((this.tournament_name == null) ? 0 : this.tournament_name.hashCode());
					
    		this.hashCode = result;
    		this.hashCodeDirty = false;
		}
		return this.hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final row3Struct other = (row3Struct) obj;
		
						if (this.tournament_name == null) {
							if (other.tournament_name != null)
								return false;
						
						} else if (!this.tournament_name.equals(other.tournament_name))
						
							return false;
					

		return true;
    }

	public void copyDataTo(row3Struct other) {

		other.tournament_id = this.tournament_id;
	            other.tournament_name = this.tournament_name;
	            other.location = this.location;
	            other.prize_money = this.prize_money;
	            other.balls_brand = this.balls_brand;
	            other.venue_type = this.venue_type;
	            other.court_manufacturer = this.court_manufacturer;
	            other.turf_type = this.turf_type;
	            other.date = this.date;
	            
	}

	public void copyKeysDataTo(row3Struct other) {

		other.tournament_name = this.tournament_name;
	            	
	}




	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_FACT_MATCHS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_MATCHS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_FACT_MATCHS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_MATCHS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_MATCHS = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_MATCHS, 0, length, utf8Charset);
		}
		return strReturn;
	}

    private void writeString(String str, ObjectOutputStream dos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
    }
    
    private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
    }
	
	private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			byte[] byteArray = new byte[length];
			dis.read(byteArray);
			strReturn = new String(byteArray, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			byte[] byteArray = new byte[length];
			unmarshaller.read(byteArray);
			strReturn = new String(byteArray, utf8Charset);
		}
		return strReturn;
	}
	
	private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
	}

	private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
	}
	private Integer readInteger(DataInputStream dis, ObjectInputStream ois) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
			intReturn = dis.readInt();
		}
		return intReturn;
	}
	
	private Integer readInteger(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		Integer intReturn;
        int length = 0;
        length = unmarshaller.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
			intReturn = unmarshaller.readInt();
		}
		return intReturn;
	}

	private void writeInteger(Integer intNum, DataOutputStream dos, ObjectOutputStream oos) throws IOException{
		if(intNum == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeInt(intNum);
    	}
	}
	
	private void writeInteger(Integer intNum, DataOutputStream dos,org.jboss.marshalling.Marshaller marshaller ) throws IOException{
		if(intNum == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeInt(intNum);
    	}
	}

	private java.util.Date readDate(DataInputStream dis, ObjectInputStream ois) throws IOException{
		java.util.Date dateReturn = null;
		int length = 0;
        length = dis.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(dis.readLong());
		}
		return dateReturn;
	}
	
	private java.util.Date readDate(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller ) throws IOException{
		java.util.Date dateReturn = null;
		int length = 0;
        length = unmarshaller.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(unmarshaller.readLong());
		}
		return dateReturn;
	}

	private void writeDate(java.util.Date date1, DataOutputStream dos, ObjectOutputStream oos) throws IOException{
		if(date1 == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeLong(date1.getTime());
    	}
	}
	
	private void writeDate(java.util.Date date1, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(date1 == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeLong(date1.getTime());
    	}
	}

    public void readKeysData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_MATCHS) {

        	try {

        		int length = 0;
		
					this.tournament_name = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_MATCHS) {

        	try {

        		int length = 0;
		
					this.tournament_name = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeKeysData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.tournament_name,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.tournament_name,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }



    /**
     * Fill Values data by reading ObjectInputStream.
     */
    public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
        try {

			int length = 0;
		
			            this.tournament_id = dis.readInt();
					
						this.location = readString(dis,ois);
					
						this.prize_money = readInteger(dis,ois);
					
						this.balls_brand = readString(dis,ois);
					
						this.venue_type = readString(dis,ois);
					
						this.court_manufacturer = readString(dis,ois);
					
						this.turf_type = readString(dis,ois);
					
						this.date = readDate(dis,ois);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }
    
    public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
        try {
			int length = 0;
		
			            this.tournament_id = objectIn.readInt();
					
						this.location = readString(dis,objectIn);
					
						this.prize_money = readInteger(dis,objectIn);
					
						this.balls_brand = readString(dis,objectIn);
					
						this.venue_type = readString(dis,objectIn);
					
						this.court_manufacturer = readString(dis,objectIn);
					
						this.turf_type = readString(dis,objectIn);
					
						this.date = readDate(dis,objectIn);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }

    /**
     * Return a byte array which represents Values data.
     */
    public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
        try {

		
		            	dos.writeInt(this.tournament_id);
					
						writeString(this.location, dos, oos);
					
					writeInteger(this.prize_money, dos, oos);
					
						writeString(this.balls_brand, dos, oos);
					
						writeString(this.venue_type, dos, oos);
					
						writeString(this.court_manufacturer, dos, oos);
					
						writeString(this.turf_type, dos, oos);
					
						writeDate(this.date, dos, oos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}

    }
    
    public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut){
                try {

		
					objectOut.writeInt(this.tournament_id);
					
						writeString(this.location, dos, objectOut);
					
					writeInteger(this.prize_money, dos, objectOut);
					
						writeString(this.balls_brand, dos, objectOut);
					
						writeString(this.venue_type, dos, objectOut);
					
						writeString(this.court_manufacturer, dos, objectOut);
					
						writeString(this.turf_type, dos, objectOut);
					
						writeDate(this.date, dos, objectOut);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}
    }


    
    public boolean supportMarshaller(){
        return true;
    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("tournament_id="+String.valueOf(tournament_id));
		sb.append(",tournament_name="+tournament_name);
		sb.append(",location="+location);
		sb.append(",prize_money="+String.valueOf(prize_money));
		sb.append(",balls_brand="+balls_brand);
		sb.append(",venue_type="+venue_type);
		sb.append(",court_manufacturer="+court_manufacturer);
		sb.append(",turf_type="+turf_type);
		sb.append(",date="+String.valueOf(date));
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(row3Struct other) {

		int returnValue = -1;
		
						returnValue = checkNullsAndCompare(this.tournament_name, other.tournament_name);
						if(returnValue != 0) {
							return returnValue;
						}

					
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}
public void tDBInput_3Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tDBInput_3_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { //start the resume
				globalResumeTicket = true;



		row3Struct row3 = new row3Struct();




	
	/**
	 * [tAdvancedHash_row3 begin ] start
	 */

	

	
		
		ok_Hash.put("tAdvancedHash_row3", false);
		start_Hash.put("tAdvancedHash_row3", System.currentTimeMillis());
		
	
	currentComponent="tAdvancedHash_row3";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row3");
					}
				
		int tos_count_tAdvancedHash_row3 = 0;
		

			   		// connection name:row3
			   		// source node:tDBInput_3 - inputs:(after_tFileInputExcel_1) outputs:(row3,row3) | target node:tAdvancedHash_row3 - inputs:(row3) outputs:()
			   		// linked node: tMap_1 - inputs:(row1,row2,row4,row5,row6,row7,row3) outputs:(fact)
			   
			   		org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row3 = 
			   			org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;
			   			
			   
	   			org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct> tHash_Lookup_row3 =org.talend.designer.components.lookup.memory.AdvancedMemoryLookup.
	   						<row3Struct>getLookup(matchingModeEnum_row3);
	   						   
		   	   	   globalMap.put("tHash_Lookup_row3", tHash_Lookup_row3);
		   	   	   
				
           

 



/**
 * [tAdvancedHash_row3 begin ] stop
 */



	
	/**
	 * [tDBInput_3 begin ] start
	 */

	

	
		
		ok_Hash.put("tDBInput_3", false);
		start_Hash.put("tDBInput_3", System.currentTimeMillis());
		
	
	currentComponent="tDBInput_3";

	
		int tos_count_tDBInput_3 = 0;
		
	
    
	
			org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_3 = org.talend.designer.components.util.mssql.MSSqlUtilFactory.getMSSqlGenerateTimestampUtil();
			
			java.util.List<String> talendToDBList_tDBInput_3 = new java.util.ArrayList();
			String[] talendToDBArray_tDBInput_3  = new String[]{"FLOAT","NUMERIC","NUMERIC IDENTITY","DECIMAL","DECIMAL IDENTITY","REAL"}; 
			java.util.Collections.addAll(talendToDBList_tDBInput_3, talendToDBArray_tDBInput_3); 
		    int nb_line_tDBInput_3 = 0;
		    java.sql.Connection conn_tDBInput_3 = null;
				String driverClass_tDBInput_3 = "net.sourceforge.jtds.jdbc.Driver";
			    java.lang.Class jdbcclazz_tDBInput_3 = java.lang.Class.forName(driverClass_tDBInput_3);
				String dbUser_tDBInput_3 = "Padelle";
				
				 
	final String decryptedPassword_tDBInput_3 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:NairjAWVyNl+WxcFEWfYyV6F2heCWIyCKt0uvE0SfUVHBxg=");
				
				String dbPwd_tDBInput_3 = decryptedPassword_tDBInput_3;
				
		    String port_tDBInput_3 = "1433";
		    String dbname_tDBInput_3 = "DW_padel" ;
			String url_tDBInput_3 = "jdbc:jtds:sqlserver://" + "DESKTOP-QJ70MNR" ;
		    if (!"".equals(port_tDBInput_3)) {
		    	url_tDBInput_3 += ":" + "1433";
		    }
		    if (!"".equals(dbname_tDBInput_3)) {
				url_tDBInput_3 += "//" + "DW_padel"; 
		    }
		    url_tDBInput_3 += ";appName=" + projectName + ";" + "";
		    String dbschema_tDBInput_3 = "";
				
				conn_tDBInput_3 = java.sql.DriverManager.getConnection(url_tDBInput_3,dbUser_tDBInput_3,dbPwd_tDBInput_3);
		        
		    
			java.sql.Statement stmt_tDBInput_3 = conn_tDBInput_3.createStatement();

		    String dbquery_tDBInput_3 = "SELECT dim_tournament.tournament_id,\n		dim_tournament.tournament_name,\n		dim_tournament.location,\n		dim_tournament.priz"
+"e_money,\n		dim_tournament.balls_brand,\n		dim_tournament.venue_type,\n		dim_tournament.court_manufacturer,\n		dim_tournamen"
+"t.turf_type,\n		dim_tournament.date\nFROM	dim_tournament";
		    

            	globalMap.put("tDBInput_3_QUERY",dbquery_tDBInput_3);
		    java.sql.ResultSet rs_tDBInput_3 = null;

		    try {
		    	rs_tDBInput_3 = stmt_tDBInput_3.executeQuery(dbquery_tDBInput_3);
		    	java.sql.ResultSetMetaData rsmd_tDBInput_3 = rs_tDBInput_3.getMetaData();
		    	int colQtyInRs_tDBInput_3 = rsmd_tDBInput_3.getColumnCount();

		    String tmpContent_tDBInput_3 = null;
		    
		    
		    while (rs_tDBInput_3.next()) {
		        nb_line_tDBInput_3++;
		        
							if(colQtyInRs_tDBInput_3 < 1) {
								row3.tournament_id = 0;
							} else {
		                          
            row3.tournament_id = rs_tDBInput_3.getInt(1);
            if(rs_tDBInput_3.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
							if(colQtyInRs_tDBInput_3 < 2) {
								row3.tournament_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_3 = rs_tDBInput_3.getString(2);
            if(tmpContent_tDBInput_3 != null) {
            	if (talendToDBList_tDBInput_3 .contains(rsmd_tDBInput_3.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.tournament_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_3);
            	} else {
                	row3.tournament_name = tmpContent_tDBInput_3;
                }
            } else {
                row3.tournament_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_3 < 3) {
								row3.location = null;
							} else {
	                         		
           		tmpContent_tDBInput_3 = rs_tDBInput_3.getString(3);
            if(tmpContent_tDBInput_3 != null) {
            	if (talendToDBList_tDBInput_3 .contains(rsmd_tDBInput_3.getColumnTypeName(3).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.location = FormatterUtils.formatUnwithE(tmpContent_tDBInput_3);
            	} else {
                	row3.location = tmpContent_tDBInput_3;
                }
            } else {
                row3.location = null;
            }
		                    }
							if(colQtyInRs_tDBInput_3 < 4) {
								row3.prize_money = null;
							} else {
		                          
            row3.prize_money = rs_tDBInput_3.getInt(4);
            if(rs_tDBInput_3.wasNull()){
                    row3.prize_money = null;
            }
		                    }
							if(colQtyInRs_tDBInput_3 < 5) {
								row3.balls_brand = null;
							} else {
	                         		
           		tmpContent_tDBInput_3 = rs_tDBInput_3.getString(5);
            if(tmpContent_tDBInput_3 != null) {
            	if (talendToDBList_tDBInput_3 .contains(rsmd_tDBInput_3.getColumnTypeName(5).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.balls_brand = FormatterUtils.formatUnwithE(tmpContent_tDBInput_3);
            	} else {
                	row3.balls_brand = tmpContent_tDBInput_3;
                }
            } else {
                row3.balls_brand = null;
            }
		                    }
							if(colQtyInRs_tDBInput_3 < 6) {
								row3.venue_type = null;
							} else {
	                         		
           		tmpContent_tDBInput_3 = rs_tDBInput_3.getString(6);
            if(tmpContent_tDBInput_3 != null) {
            	if (talendToDBList_tDBInput_3 .contains(rsmd_tDBInput_3.getColumnTypeName(6).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.venue_type = FormatterUtils.formatUnwithE(tmpContent_tDBInput_3);
            	} else {
                	row3.venue_type = tmpContent_tDBInput_3;
                }
            } else {
                row3.venue_type = null;
            }
		                    }
							if(colQtyInRs_tDBInput_3 < 7) {
								row3.court_manufacturer = null;
							} else {
	                         		
           		tmpContent_tDBInput_3 = rs_tDBInput_3.getString(7);
            if(tmpContent_tDBInput_3 != null) {
            	if (talendToDBList_tDBInput_3 .contains(rsmd_tDBInput_3.getColumnTypeName(7).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.court_manufacturer = FormatterUtils.formatUnwithE(tmpContent_tDBInput_3);
            	} else {
                	row3.court_manufacturer = tmpContent_tDBInput_3;
                }
            } else {
                row3.court_manufacturer = null;
            }
		                    }
							if(colQtyInRs_tDBInput_3 < 8) {
								row3.turf_type = null;
							} else {
	                         		
           		tmpContent_tDBInput_3 = rs_tDBInput_3.getString(8);
            if(tmpContent_tDBInput_3 != null) {
            	if (talendToDBList_tDBInput_3 .contains(rsmd_tDBInput_3.getColumnTypeName(8).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.turf_type = FormatterUtils.formatUnwithE(tmpContent_tDBInput_3);
            	} else {
                	row3.turf_type = tmpContent_tDBInput_3;
                }
            } else {
                row3.turf_type = null;
            }
		                    }
							if(colQtyInRs_tDBInput_3 < 9) {
								row3.date = null;
							} else {
										
			row3.date = mssqlGTU_tDBInput_3.getDate(rsmd_tDBInput_3, rs_tDBInput_3, 9);
			
		                    }
					





 



/**
 * [tDBInput_3 begin ] stop
 */
	
	/**
	 * [tDBInput_3 main ] start
	 */

	

	
	
	currentComponent="tDBInput_3";

	

 


	tos_count_tDBInput_3++;

/**
 * [tDBInput_3 main ] stop
 */
	
	/**
	 * [tDBInput_3 process_data_begin ] start
	 */

	

	
	
	currentComponent="tDBInput_3";

	

 



/**
 * [tDBInput_3 process_data_begin ] stop
 */

	
	/**
	 * [tAdvancedHash_row3 main ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row3";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row3"
						
						);
					}
					


			   
			   

					row3Struct row3_HashRow = new row3Struct();
		   	   	   
				
				row3_HashRow.tournament_id = row3.tournament_id;
				
				row3_HashRow.tournament_name = row3.tournament_name;
				
				row3_HashRow.location = row3.location;
				
				row3_HashRow.prize_money = row3.prize_money;
				
				row3_HashRow.balls_brand = row3.balls_brand;
				
				row3_HashRow.venue_type = row3.venue_type;
				
				row3_HashRow.court_manufacturer = row3.court_manufacturer;
				
				row3_HashRow.turf_type = row3.turf_type;
				
				row3_HashRow.date = row3.date;
				
			tHash_Lookup_row3.put(row3_HashRow);
			
            




 


	tos_count_tAdvancedHash_row3++;

/**
 * [tAdvancedHash_row3 main ] stop
 */
	
	/**
	 * [tAdvancedHash_row3 process_data_begin ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row3";

	

 



/**
 * [tAdvancedHash_row3 process_data_begin ] stop
 */
	
	/**
	 * [tAdvancedHash_row3 process_data_end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row3";

	

 



/**
 * [tAdvancedHash_row3 process_data_end ] stop
 */



	
	/**
	 * [tDBInput_3 process_data_end ] start
	 */

	

	
	
	currentComponent="tDBInput_3";

	

 



/**
 * [tDBInput_3 process_data_end ] stop
 */
	
	/**
	 * [tDBInput_3 end ] start
	 */

	

	
	
	currentComponent="tDBInput_3";

	

	}
}finally{
	if (rs_tDBInput_3 != null) {
		rs_tDBInput_3.close();
	}
	if (stmt_tDBInput_3 != null) {
		stmt_tDBInput_3.close();
	}
		if(conn_tDBInput_3 != null && !conn_tDBInput_3.isClosed()) {
			
			conn_tDBInput_3.close();
			
			if("com.mysql.cj.jdbc.Driver".equals((String)globalMap.get("driverClass_"))
			    && routines.system.BundleUtils.inOSGi()) {
			        Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread").
			            getMethod("checkedShutdown").invoke(null, (Object[]) null);
			}
			
		}
}
globalMap.put("tDBInput_3_NB_LINE",nb_line_tDBInput_3);

 

ok_Hash.put("tDBInput_3", true);
end_Hash.put("tDBInput_3", System.currentTimeMillis());




/**
 * [tDBInput_3 end ] stop
 */

	
	/**
	 * [tAdvancedHash_row3 end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row3";

	

tHash_Lookup_row3.endPut();

				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row3");
			  	}
			  	
 

ok_Hash.put("tAdvancedHash_row3", true);
end_Hash.put("tAdvancedHash_row3", System.currentTimeMillis());




/**
 * [tAdvancedHash_row3 end ] stop
 */



				}//end the resume

				



	
			}catch(java.lang.Exception e){	
				
				TalendException te = new TalendException(e, currentComponent, globalMap);
				
				throw te;
			}catch(java.lang.Error error){	
				
					runStat.stopThreadStat();
				
				throw error;
			}finally{
				
				try{
					
	
	/**
	 * [tDBInput_3 finally ] start
	 */

	

	
	
	currentComponent="tDBInput_3";

	

 



/**
 * [tDBInput_3 finally ] stop
 */

	
	/**
	 * [tAdvancedHash_row3 finally ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row3";

	

 



/**
 * [tAdvancedHash_row3 finally ] stop
 */



				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tDBInput_3_SUBPROCESS_STATE", 1);
	}
	
    public String resuming_logs_dir_path = null;
    public String resuming_checkpoint_path = null;
    public String parent_part_launcher = null;
    private String resumeEntryMethodName = null;
    private boolean globalResumeTicket = false;

    public boolean watch = false;
    // portStats is null, it means don't execute the statistics
    public Integer portStats = null;
    public int portTraces = 4334;
    public String clientHost;
    public String defaultClientHost = "localhost";
    public String contextStr = "Default";
    public boolean isDefaultContext = true;
    public String pid = "0";
    public String rootPid = null;
    public String fatherPid = null;
    public String fatherNode = null;
    public long startTime = 0;
    public boolean isChildJob = false;
    public String log4jLevel = "";
    
    private boolean enableLogStash;

    private boolean execStat = true;

    private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
        protected java.util.Map<String, String> initialValue() {
            java.util.Map<String,String> threadRunResultMap = new java.util.HashMap<String, String>();
            threadRunResultMap.put("errorCode", null);
            threadRunResultMap.put("status", "");
            return threadRunResultMap;
        };
    };


    protected PropertiesWithType context_param = new PropertiesWithType();
    public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

    public String status= "";
    

    public static void main(String[] args){
        final FACT_MATCHS FACT_MATCHSClass = new FACT_MATCHS();

        int exitCode = FACT_MATCHSClass.runJobInTOS(args);

        System.exit(exitCode);
    }


    public String[][] runJob(String[] args) {

        int exitCode = runJobInTOS(args);
        String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

        return bufferValue;
    }

    public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;
    	
        return hastBufferOutput;
    }

    public int runJobInTOS(String[] args) {
	   	// reset status
	   	status = "";
	   	
        String lastStr = "";
        for (String arg : args) {
            if (arg.equalsIgnoreCase("--context_param")) {
                lastStr = arg;
            } else if (lastStr.equals("")) {
                evalParam(arg);
            } else {
                evalParam(lastStr + " " + arg);
                lastStr = "";
            }
        }
        enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

    	
    	

        if(clientHost == null) {
            clientHost = defaultClientHost;
        }

        if(pid == null || "0".equals(pid)) {
            pid = TalendString.getAsciiRandomString(6);
        }

        if (rootPid==null) {
            rootPid = pid;
        }
        if (fatherPid==null) {
            fatherPid = pid;
        }else{
            isChildJob = true;
        }

        if (portStats != null) {
            // portStats = -1; //for testing
            if (portStats < 0 || portStats > 65535) {
                // issue:10869, the portStats is invalid, so this client socket can't open
                System.err.println("The statistics socket port " + portStats + " is invalid.");
                execStat = false;
            }
        } else {
            execStat = false;
        }
        boolean inOSGi = routines.system.BundleUtils.inOSGi();

        if (inOSGi) {
            java.util.Dictionary<String, Object> jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

            if (jobProperties != null && jobProperties.get("context") != null) {
                contextStr = (String)jobProperties.get("context");
            }
        }

        try {
            //call job/subjob with an existing context, like: --context=production. if without this parameter, there will use the default context instead.
            java.io.InputStream inContext = FACT_MATCHS.class.getClassLoader().getResourceAsStream("dw_padel/fact_matchs_0_1/contexts/" + contextStr + ".properties");
            if (inContext == null) {
                inContext = FACT_MATCHS.class.getClassLoader().getResourceAsStream("config/contexts/" + contextStr + ".properties");
            }
            if (inContext != null) {
                try {
                    //defaultProps is in order to keep the original context value
                    if(context != null && context.isEmpty()) {
	                defaultProps.load(inContext);
	                context = new ContextProperties(defaultProps);
                    }
                } finally {
                    inContext.close();
                }
            } else if (!isDefaultContext) {
                //print info and job continue to run, for case: context_param is not empty.
                System.err.println("Could not find the context " + contextStr);
            }

            if(!context_param.isEmpty()) {
                context.putAll(context_param);
				//set types for params from parentJobs
				for (Object key: context_param.keySet()){
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
            }
            class ContextProcessing {
                private void processContext_0() {
                } 
                public void processAllContext() {
                        processContext_0();
                }
            }

            new ContextProcessing().processAllContext();
        } catch (java.io.IOException ie) {
            System.err.println("Could not load context "+contextStr);
            ie.printStackTrace();
        }

        // get context value from parent directly
        if (parentContextMap != null && !parentContextMap.isEmpty()) {
        }

        //Resume: init the resumeUtil
        resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
        resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
        resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
        //Resume: jobStart
        resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "","","","",resumeUtil.convertToJsonText(context,parametersToEncrypt));

if(execStat) {
    try {
        runStat.openSocket(!isChildJob);
        runStat.setAllPID(rootPid, fatherPid, pid, jobName);
        runStat.startThreadStat(clientHost, portStats);
        runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
    } catch (java.io.IOException ioException) {
        ioException.printStackTrace();
    }
}



	
	    java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
	    globalMap.put("concurrentHashMap", concurrentHashMap);
	

    long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    long endUsedMemory = 0;
    long end = 0;

    startTime = System.currentTimeMillis();


this.globalResumeTicket = true;//to run tPreJob





this.globalResumeTicket = false;//to run others jobs

try {
errorCode = null;tFileInputExcel_1Process(globalMap);
if(!"failure".equals(status)) { status = "end"; }
}catch (TalendException e_tFileInputExcel_1) {
globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", -1);

e_tFileInputExcel_1.printStackTrace();

}

this.globalResumeTicket = true;//to run tPostJob




        end = System.currentTimeMillis();

        if (watch) {
            System.out.println((end-startTime)+" milliseconds");
        }

        endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        if (false) {
            System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : FACT_MATCHS");
        }



if (execStat) {
    runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
    runStat.stopThreadStat();
}
    int returnCode = 0;


    if(errorCode == null) {
         returnCode = status != null && status.equals("failure") ? 1 : 0;
    } else {
         returnCode = errorCode.intValue();
    }
    resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "","" + returnCode,"","","");

    return returnCode;

  }

    // only for OSGi env
    public void destroy() {


    }














    private java.util.Map<String, Object> getSharedConnections4REST() {
        java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();






        return connections;
    }

    private void evalParam(String arg) {
        if (arg.startsWith("--resuming_logs_dir_path")) {
            resuming_logs_dir_path = arg.substring(25);
        } else if (arg.startsWith("--resuming_checkpoint_path")) {
            resuming_checkpoint_path = arg.substring(27);
        } else if (arg.startsWith("--parent_part_launcher")) {
            parent_part_launcher = arg.substring(23);
        } else if (arg.startsWith("--watch")) {
            watch = true;
        } else if (arg.startsWith("--stat_port=")) {
            String portStatsStr = arg.substring(12);
            if (portStatsStr != null && !portStatsStr.equals("null")) {
                portStats = Integer.parseInt(portStatsStr);
            }
        } else if (arg.startsWith("--trace_port=")) {
            portTraces = Integer.parseInt(arg.substring(13));
        } else if (arg.startsWith("--client_host=")) {
            clientHost = arg.substring(14);
        } else if (arg.startsWith("--context=")) {
            contextStr = arg.substring(10);
            isDefaultContext = false;
        } else if (arg.startsWith("--father_pid=")) {
            fatherPid = arg.substring(13);
        } else if (arg.startsWith("--root_pid=")) {
            rootPid = arg.substring(11);
        } else if (arg.startsWith("--father_node=")) {
            fatherNode = arg.substring(14);
        } else if (arg.startsWith("--pid=")) {
            pid = arg.substring(6);
        } else if (arg.startsWith("--context_type")) {
            String keyValue = arg.substring(15);
			int index = -1;
            if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
                if (fatherPid==null) {
                    context_param.setContextType(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
                } else { // the subjob won't escape the especial chars
                    context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1) );
                }

            }

		} else if (arg.startsWith("--context_param")) {
            String keyValue = arg.substring(16);
            int index = -1;
            if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
                if (fatherPid==null) {
                    context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
                } else { // the subjob won't escape the especial chars
                    context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1) );
                }
            }
        } else if (arg.startsWith("--log4jLevel=")) {
            log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {//for trunjob call
		    final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
    }
    
    private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

    private final String[][] escapeChars = {
        {"\\\\","\\"},{"\\n","\n"},{"\\'","\'"},{"\\r","\r"},
        {"\\f","\f"},{"\\b","\b"},{"\\t","\t"}
        };
    private String replaceEscapeChars (String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0],currIndex);
				if (index>=0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0], strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
    }

    public Integer getErrorCode() {
        return errorCode;
    }


    public String getStatus() {
        return status;
    }

    ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 *     358061 characters generated by Talend Open Studio for Data Integration 
 *     on the 29 avril 2026 à 03:24:19 WAT
 ************************************************************************************************/