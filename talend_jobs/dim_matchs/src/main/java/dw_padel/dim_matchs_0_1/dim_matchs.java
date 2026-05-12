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


package dw_padel.dim_matchs_0_1;

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
 * Job: dim_matchs Purpose: <br>
 * Description:  <br>
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status 
 */
public class dim_matchs implements TalendJob {

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
	private final String jobName = "dim_matchs";
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
				dim_matchs.this.exception = e;
			}
		}
		if (!(e instanceof TalendException)) {
		try {
			for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
				if (m.getName().compareTo(currentComponent + "_error") == 0) {
					m.invoke(dim_matchs.this, new Object[] { e , currentComponent, globalMap});
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

			public void tDBInput_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tUniqRow_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tFilterRow_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBOutput_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tAdvancedHash_row3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_1_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
	






public static class dim_matchsStruct implements routines.system.IPersistableRow<dim_matchsStruct> {
    final static byte[] commonByteArrayLock_DW_PADEL_dim_matchs = new byte[0];
    static byte[] commonByteArray_DW_PADEL_dim_matchs = new byte[0];
	protected static final int DEFAULT_HASHCODE = 1;
    protected static final int PRIME = 31;
    protected int hashCode = DEFAULT_HASHCODE;
    public boolean hashCodeDirty = true;

    public String loopKey;



	
			    public int match_id;

				public int getMatch_id () {
					return this.match_id;
				}
				
			    public Integer match_number;

				public Integer getMatch_number () {
					return this.match_number;
				}
				
			    public String tournament_name;

				public String getTournament_name () {
					return this.tournament_name;
				}
				
			    public String round;

				public String getRound () {
					return this.round;
				}
				
			    public String winner;

				public String getWinner () {
					return this.winner;
				}
				
			    public Double total_points_won_t1;

				public Double getTotal_points_won_t1 () {
					return this.total_points_won_t1;
				}
				
			    public Double total_points_won_t2;

				public Double getTotal_points_won_t2 () {
					return this.total_points_won_t2;
				}
				
			    public Double break_points_converted_t1;

				public Double getBreak_points_converted_t1 () {
					return this.break_points_converted_t1;
				}
				
			    public Double break_points_converted_t2;

				public Double getBreak_points_converted_t2 () {
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
				
			    public Double won_on_1st_serve_t1;

				public Double getWon_on_1st_serve_t1 () {
					return this.won_on_1st_serve_t1;
				}
				
			    public Double won_on_1st_serve_t2;

				public Double getWon_on_1st_serve_t2 () {
					return this.won_on_1st_serve_t2;
				}
				
			    public Double won_on_2nd_serve_t1;

				public Double getWon_on_2nd_serve_t1 () {
					return this.won_on_2nd_serve_t1;
				}
				
			    public Double won_on_2nd_serve_t2;

				public Double getWon_on_2nd_serve_t2 () {
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
				
			    public Double won_on_1st_return_t1;

				public Double getWon_on_1st_return_t1 () {
					return this.won_on_1st_return_t1;
				}
				
			    public Double won_on_1st_return_t2;

				public Double getWon_on_1st_return_t2 () {
					return this.won_on_1st_return_t2;
				}
				
			    public Double won_on_2nd_return_t1;

				public Double getWon_on_2nd_return_t1 () {
					return this.won_on_2nd_return_t1;
				}
				
			    public Double won_on_2nd_return_t2;

				public Double getWon_on_2nd_return_t2 () {
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
				
			    public Double total_won_on_serve_t1;

				public Double getTotal_won_on_serve_t1 () {
					return this.total_won_on_serve_t1;
				}
				
			    public Double total_won_on_serve_t2;

				public Double getTotal_won_on_serve_t2 () {
					return this.total_won_on_serve_t2;
				}
				
			    public Double total_won_on_return_t1;

				public Double getTotal_won_on_return_t1 () {
					return this.total_won_on_return_t1;
				}
				
			    public Double total_won_on_return_t2;

				public Double getTotal_won_on_return_t2 () {
					return this.total_won_on_return_t2;
				}
				
			    public java.util.Date date;

				public java.util.Date getDate () {
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
				


	@Override
	public int hashCode() {
		if (this.hashCodeDirty) {
			final int prime = PRIME;
			int result = DEFAULT_HASHCODE;
	
							result = prime * result + (int) this.match_id;
						
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
		final dim_matchsStruct other = (dim_matchsStruct) obj;
		
						if (this.match_id != other.match_id)
							return false;
					

		return true;
    }

	public void copyDataTo(dim_matchsStruct other) {

		other.match_id = this.match_id;
	            other.match_number = this.match_number;
	            other.tournament_name = this.tournament_name;
	            other.round = this.round;
	            other.winner = this.winner;
	            other.total_points_won_t1 = this.total_points_won_t1;
	            other.total_points_won_t2 = this.total_points_won_t2;
	            other.break_points_converted_t1 = this.break_points_converted_t1;
	            other.break_points_converted_t2 = this.break_points_converted_t2;
	            other.longest_streak_t1 = this.longest_streak_t1;
	            other.longest_streak_t2 = this.longest_streak_t2;
	            other.aces_t1 = this.aces_t1;
	            other.aces_t2 = this.aces_t2;
	            other.double_faults_t1 = this.double_faults_t1;
	            other.double_faults_t2 = this.double_faults_t2;
	            other.won_on_1st_serve_t1 = this.won_on_1st_serve_t1;
	            other.won_on_1st_serve_t2 = this.won_on_1st_serve_t2;
	            other.won_on_2nd_serve_t1 = this.won_on_2nd_serve_t1;
	            other.won_on_2nd_serve_t2 = this.won_on_2nd_serve_t2;
	            other.service_games_t1 = this.service_games_t1;
	            other.service_games_t2 = this.service_games_t2;
	            other.won_on_1st_return_t1 = this.won_on_1st_return_t1;
	            other.won_on_1st_return_t2 = this.won_on_1st_return_t2;
	            other.won_on_2nd_return_t1 = this.won_on_2nd_return_t1;
	            other.won_on_2nd_return_t2 = this.won_on_2nd_return_t2;
	            other.return_games_t1 = this.return_games_t1;
	            other.return_games_t2 = this.return_games_t2;
	            other.total_won_on_serve_t1 = this.total_won_on_serve_t1;
	            other.total_won_on_serve_t2 = this.total_won_on_serve_t2;
	            other.total_won_on_return_t1 = this.total_won_on_return_t1;
	            other.total_won_on_return_t2 = this.total_won_on_return_t2;
	            other.date = this.date;
	            other.team1_player1_name = this.team1_player1_name;
	            other.team1_player2_name = this.team1_player2_name;
	            other.team2_player1_name = this.team2_player1_name;
	            other.team2_player2_name = this.team2_player2_name;
	            other.match_info_added = this.match_info_added;
	            other.t1_s1 = this.t1_s1;
	            other.t2_s1 = this.t2_s1;
	            other.t1_s2 = this.t1_s2;
	            other.t2_s2 = this.t2_s2;
	            other.t1_s3 = this.t1_s3;
	            other.t2_s3 = this.t2_s3;
	            other.views = this.views;
	            other.interactions = this.interactions;
	            
	}

	public void copyKeysDataTo(dim_matchsStruct other) {

		other.match_id = this.match_id;
	            	
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
			if(length > commonByteArray_DW_PADEL_dim_matchs.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_matchs.length == 0) {
   					commonByteArray_DW_PADEL_dim_matchs = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_matchs = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_dim_matchs, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_matchs, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_dim_matchs.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_matchs.length == 0) {
   					commonByteArray_DW_PADEL_dim_matchs = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_matchs = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_dim_matchs, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_matchs, 0, length, utf8Charset);
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

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_DW_PADEL_dim_matchs) {

        	try {

        		int length = 0;
		
			        this.match_id = dis.readInt();
					
						this.match_number = readInteger(dis);
					
					this.tournament_name = readString(dis);
					
					this.round = readString(dis);
					
					this.winner = readString(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.total_points_won_t1 = null;
           				} else {
           			    	this.total_points_won_t1 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.total_points_won_t2 = null;
           				} else {
           			    	this.total_points_won_t2 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.break_points_converted_t1 = null;
           				} else {
           			    	this.break_points_converted_t1 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.break_points_converted_t2 = null;
           				} else {
           			    	this.break_points_converted_t2 = dis.readDouble();
           				}
					
						this.longest_streak_t1 = readInteger(dis);
					
						this.longest_streak_t2 = readInteger(dis);
					
						this.aces_t1 = readInteger(dis);
					
						this.aces_t2 = readInteger(dis);
					
						this.double_faults_t1 = readInteger(dis);
					
						this.double_faults_t2 = readInteger(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.won_on_1st_serve_t1 = null;
           				} else {
           			    	this.won_on_1st_serve_t1 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.won_on_1st_serve_t2 = null;
           				} else {
           			    	this.won_on_1st_serve_t2 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.won_on_2nd_serve_t1 = null;
           				} else {
           			    	this.won_on_2nd_serve_t1 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.won_on_2nd_serve_t2 = null;
           				} else {
           			    	this.won_on_2nd_serve_t2 = dis.readDouble();
           				}
					
						this.service_games_t1 = readInteger(dis);
					
						this.service_games_t2 = readInteger(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.won_on_1st_return_t1 = null;
           				} else {
           			    	this.won_on_1st_return_t1 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.won_on_1st_return_t2 = null;
           				} else {
           			    	this.won_on_1st_return_t2 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.won_on_2nd_return_t1 = null;
           				} else {
           			    	this.won_on_2nd_return_t1 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.won_on_2nd_return_t2 = null;
           				} else {
           			    	this.won_on_2nd_return_t2 = dis.readDouble();
           				}
					
						this.return_games_t1 = readInteger(dis);
					
						this.return_games_t2 = readInteger(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.total_won_on_serve_t1 = null;
           				} else {
           			    	this.total_won_on_serve_t1 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.total_won_on_serve_t2 = null;
           				} else {
           			    	this.total_won_on_serve_t2 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.total_won_on_return_t1 = null;
           				} else {
           			    	this.total_won_on_return_t1 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.total_won_on_return_t2 = null;
           				} else {
           			    	this.total_won_on_return_t2 = dis.readDouble();
           				}
					
					this.date = readDate(dis);
					
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

		synchronized(commonByteArrayLock_DW_PADEL_dim_matchs) {

        	try {

        		int length = 0;
		
			        this.match_id = dis.readInt();
					
						this.match_number = readInteger(dis);
					
					this.tournament_name = readString(dis);
					
					this.round = readString(dis);
					
					this.winner = readString(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.total_points_won_t1 = null;
           				} else {
           			    	this.total_points_won_t1 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.total_points_won_t2 = null;
           				} else {
           			    	this.total_points_won_t2 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.break_points_converted_t1 = null;
           				} else {
           			    	this.break_points_converted_t1 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.break_points_converted_t2 = null;
           				} else {
           			    	this.break_points_converted_t2 = dis.readDouble();
           				}
					
						this.longest_streak_t1 = readInteger(dis);
					
						this.longest_streak_t2 = readInteger(dis);
					
						this.aces_t1 = readInteger(dis);
					
						this.aces_t2 = readInteger(dis);
					
						this.double_faults_t1 = readInteger(dis);
					
						this.double_faults_t2 = readInteger(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.won_on_1st_serve_t1 = null;
           				} else {
           			    	this.won_on_1st_serve_t1 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.won_on_1st_serve_t2 = null;
           				} else {
           			    	this.won_on_1st_serve_t2 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.won_on_2nd_serve_t1 = null;
           				} else {
           			    	this.won_on_2nd_serve_t1 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.won_on_2nd_serve_t2 = null;
           				} else {
           			    	this.won_on_2nd_serve_t2 = dis.readDouble();
           				}
					
						this.service_games_t1 = readInteger(dis);
					
						this.service_games_t2 = readInteger(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.won_on_1st_return_t1 = null;
           				} else {
           			    	this.won_on_1st_return_t1 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.won_on_1st_return_t2 = null;
           				} else {
           			    	this.won_on_1st_return_t2 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.won_on_2nd_return_t1 = null;
           				} else {
           			    	this.won_on_2nd_return_t1 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.won_on_2nd_return_t2 = null;
           				} else {
           			    	this.won_on_2nd_return_t2 = dis.readDouble();
           				}
					
						this.return_games_t1 = readInteger(dis);
					
						this.return_games_t2 = readInteger(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.total_won_on_serve_t1 = null;
           				} else {
           			    	this.total_won_on_serve_t1 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.total_won_on_serve_t2 = null;
           				} else {
           			    	this.total_won_on_serve_t2 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.total_won_on_return_t1 = null;
           				} else {
           			    	this.total_won_on_return_t1 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.total_won_on_return_t2 = null;
           				} else {
           			    	this.total_won_on_return_t2 = dis.readDouble();
           				}
					
					this.date = readDate(dis);
					
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

		
					// int
				
		            	dos.writeInt(this.match_id);
					
					// Integer
				
						writeInteger(this.match_number,dos);
					
					// String
				
						writeString(this.tournament_name,dos);
					
					// String
				
						writeString(this.round,dos);
					
					// String
				
						writeString(this.winner,dos);
					
					// Double
				
						if(this.total_points_won_t1 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.total_points_won_t1);
		            	}
					
					// Double
				
						if(this.total_points_won_t2 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.total_points_won_t2);
		            	}
					
					// Double
				
						if(this.break_points_converted_t1 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.break_points_converted_t1);
		            	}
					
					// Double
				
						if(this.break_points_converted_t2 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.break_points_converted_t2);
		            	}
					
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
					
					// Double
				
						if(this.won_on_1st_serve_t1 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.won_on_1st_serve_t1);
		            	}
					
					// Double
				
						if(this.won_on_1st_serve_t2 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.won_on_1st_serve_t2);
		            	}
					
					// Double
				
						if(this.won_on_2nd_serve_t1 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.won_on_2nd_serve_t1);
		            	}
					
					// Double
				
						if(this.won_on_2nd_serve_t2 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.won_on_2nd_serve_t2);
		            	}
					
					// Integer
				
						writeInteger(this.service_games_t1,dos);
					
					// Integer
				
						writeInteger(this.service_games_t2,dos);
					
					// Double
				
						if(this.won_on_1st_return_t1 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.won_on_1st_return_t1);
		            	}
					
					// Double
				
						if(this.won_on_1st_return_t2 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.won_on_1st_return_t2);
		            	}
					
					// Double
				
						if(this.won_on_2nd_return_t1 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.won_on_2nd_return_t1);
		            	}
					
					// Double
				
						if(this.won_on_2nd_return_t2 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.won_on_2nd_return_t2);
		            	}
					
					// Integer
				
						writeInteger(this.return_games_t1,dos);
					
					// Integer
				
						writeInteger(this.return_games_t2,dos);
					
					// Double
				
						if(this.total_won_on_serve_t1 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.total_won_on_serve_t1);
		            	}
					
					// Double
				
						if(this.total_won_on_serve_t2 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.total_won_on_serve_t2);
		            	}
					
					// Double
				
						if(this.total_won_on_return_t1 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.total_won_on_return_t1);
		            	}
					
					// Double
				
						if(this.total_won_on_return_t2 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.total_won_on_return_t2);
		            	}
					
					// java.util.Date
				
						writeDate(this.date,dos);
					
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

		
					// int
				
		            	dos.writeInt(this.match_id);
					
					// Integer
				
						writeInteger(this.match_number,dos);
					
					// String
				
						writeString(this.tournament_name,dos);
					
					// String
				
						writeString(this.round,dos);
					
					// String
				
						writeString(this.winner,dos);
					
					// Double
				
						if(this.total_points_won_t1 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.total_points_won_t1);
		            	}
					
					// Double
				
						if(this.total_points_won_t2 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.total_points_won_t2);
		            	}
					
					// Double
				
						if(this.break_points_converted_t1 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.break_points_converted_t1);
		            	}
					
					// Double
				
						if(this.break_points_converted_t2 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.break_points_converted_t2);
		            	}
					
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
					
					// Double
				
						if(this.won_on_1st_serve_t1 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.won_on_1st_serve_t1);
		            	}
					
					// Double
				
						if(this.won_on_1st_serve_t2 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.won_on_1st_serve_t2);
		            	}
					
					// Double
				
						if(this.won_on_2nd_serve_t1 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.won_on_2nd_serve_t1);
		            	}
					
					// Double
				
						if(this.won_on_2nd_serve_t2 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.won_on_2nd_serve_t2);
		            	}
					
					// Integer
				
						writeInteger(this.service_games_t1,dos);
					
					// Integer
				
						writeInteger(this.service_games_t2,dos);
					
					// Double
				
						if(this.won_on_1st_return_t1 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.won_on_1st_return_t1);
		            	}
					
					// Double
				
						if(this.won_on_1st_return_t2 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.won_on_1st_return_t2);
		            	}
					
					// Double
				
						if(this.won_on_2nd_return_t1 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.won_on_2nd_return_t1);
		            	}
					
					// Double
				
						if(this.won_on_2nd_return_t2 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.won_on_2nd_return_t2);
		            	}
					
					// Integer
				
						writeInteger(this.return_games_t1,dos);
					
					// Integer
				
						writeInteger(this.return_games_t2,dos);
					
					// Double
				
						if(this.total_won_on_serve_t1 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.total_won_on_serve_t1);
		            	}
					
					// Double
				
						if(this.total_won_on_serve_t2 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.total_won_on_serve_t2);
		            	}
					
					// Double
				
						if(this.total_won_on_return_t1 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.total_won_on_return_t1);
		            	}
					
					// Double
				
						if(this.total_won_on_return_t2 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.total_won_on_return_t2);
		            	}
					
					// java.util.Date
				
						writeDate(this.date,dos);
					
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
		sb.append("match_id="+String.valueOf(match_id));
		sb.append(",match_number="+String.valueOf(match_number));
		sb.append(",tournament_name="+tournament_name);
		sb.append(",round="+round);
		sb.append(",winner="+winner);
		sb.append(",total_points_won_t1="+String.valueOf(total_points_won_t1));
		sb.append(",total_points_won_t2="+String.valueOf(total_points_won_t2));
		sb.append(",break_points_converted_t1="+String.valueOf(break_points_converted_t1));
		sb.append(",break_points_converted_t2="+String.valueOf(break_points_converted_t2));
		sb.append(",longest_streak_t1="+String.valueOf(longest_streak_t1));
		sb.append(",longest_streak_t2="+String.valueOf(longest_streak_t2));
		sb.append(",aces_t1="+String.valueOf(aces_t1));
		sb.append(",aces_t2="+String.valueOf(aces_t2));
		sb.append(",double_faults_t1="+String.valueOf(double_faults_t1));
		sb.append(",double_faults_t2="+String.valueOf(double_faults_t2));
		sb.append(",won_on_1st_serve_t1="+String.valueOf(won_on_1st_serve_t1));
		sb.append(",won_on_1st_serve_t2="+String.valueOf(won_on_1st_serve_t2));
		sb.append(",won_on_2nd_serve_t1="+String.valueOf(won_on_2nd_serve_t1));
		sb.append(",won_on_2nd_serve_t2="+String.valueOf(won_on_2nd_serve_t2));
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
		sb.append(",date="+String.valueOf(date));
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
    public int compareTo(dim_matchsStruct other) {

		int returnValue = -1;
		
						returnValue = checkNullsAndCompare(this.match_id, other.match_id);
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

public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_dim_matchs = new byte[0];
    static byte[] commonByteArray_DW_PADEL_dim_matchs = new byte[0];

	
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
			if(length > commonByteArray_DW_PADEL_dim_matchs.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_matchs.length == 0) {
   					commonByteArray_DW_PADEL_dim_matchs = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_matchs = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_dim_matchs, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_matchs, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_dim_matchs.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_matchs.length == 0) {
   					commonByteArray_DW_PADEL_dim_matchs = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_matchs = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_dim_matchs, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_matchs, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_dim_matchs) {

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

		synchronized(commonByteArrayLock_DW_PADEL_dim_matchs) {

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

public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_dim_matchs = new byte[0];
    static byte[] commonByteArray_DW_PADEL_dim_matchs = new byte[0];

	
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
			if(length > commonByteArray_DW_PADEL_dim_matchs.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_matchs.length == 0) {
   					commonByteArray_DW_PADEL_dim_matchs = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_matchs = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_dim_matchs, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_matchs, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_dim_matchs.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_matchs.length == 0) {
   					commonByteArray_DW_PADEL_dim_matchs = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_matchs = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_dim_matchs, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_matchs, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_dim_matchs) {

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

		synchronized(commonByteArrayLock_DW_PADEL_dim_matchs) {

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
    public int compareTo(row2Struct other) {

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

public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_dim_matchs = new byte[0];
    static byte[] commonByteArray_DW_PADEL_dim_matchs = new byte[0];

	
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
			if(length > commonByteArray_DW_PADEL_dim_matchs.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_matchs.length == 0) {
   					commonByteArray_DW_PADEL_dim_matchs = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_matchs = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_dim_matchs, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_matchs, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_dim_matchs.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_matchs.length == 0) {
   					commonByteArray_DW_PADEL_dim_matchs = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_matchs = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_dim_matchs, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_matchs, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_dim_matchs) {

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

		synchronized(commonByteArrayLock_DW_PADEL_dim_matchs) {

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
    public int compareTo(row4Struct other) {

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

public static class after_tDBInput_1Struct implements routines.system.IPersistableRow<after_tDBInput_1Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_dim_matchs = new byte[0];
    static byte[] commonByteArray_DW_PADEL_dim_matchs = new byte[0];

	
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
			if(length > commonByteArray_DW_PADEL_dim_matchs.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_matchs.length == 0) {
   					commonByteArray_DW_PADEL_dim_matchs = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_matchs = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_dim_matchs, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_matchs, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_dim_matchs.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_matchs.length == 0) {
   					commonByteArray_DW_PADEL_dim_matchs = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_matchs = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_dim_matchs, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_matchs, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_dim_matchs) {

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

		synchronized(commonByteArrayLock_DW_PADEL_dim_matchs) {

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
    public int compareTo(after_tDBInput_1Struct other) {

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
public void tDBInput_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tDBInput_1_SUBPROCESS_STATE", 0);

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

		row4Struct row4 = new row4Struct();
row2Struct row2 = new row2Struct();
row1Struct row1 = new row1Struct();
dim_matchsStruct dim_matchs = new dim_matchsStruct();







	
	/**
	 * [tDBOutput_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tDBOutput_1", false);
		start_Hash.put("tDBOutput_1", System.currentTimeMillis());
		
	
	currentComponent="tDBOutput_1";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"dim_matchs");
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


        int updateKeyCount_tDBOutput_1 = 1;
        if(updateKeyCount_tDBOutput_1 < 1) {
            throw new RuntimeException("For update, Schema must have a key");
        } else if (updateKeyCount_tDBOutput_1 == 45 && true) {
                    System.err.println("For update, every Schema column can not be a key");
        }

	
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

 
	final String decryptedPassword_tDBOutput_1 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:rkcF2M1Jmwydo7idfJSlDItZCYbYSCBtgdJaIImDtJtHwhA=");

    String dbPwd_tDBOutput_1 = decryptedPassword_tDBOutput_1;	
    conn_tDBOutput_1 = java.sql.DriverManager.getConnection(url_tDBOutput_1,dbUser_tDBOutput_1,dbPwd_tDBOutput_1);
	
		resourceMap.put("conn_tDBOutput_1", conn_tDBOutput_1);
	
        conn_tDBOutput_1.setAutoCommit(false);
        int commitEvery_tDBOutput_1 = 10000;
        int commitCounter_tDBOutput_1 = 0;


if(dbschema_tDBOutput_1 == null || dbschema_tDBOutput_1.trim().length() == 0) {
    tableName_tDBOutput_1 = "matches_with_views_interactions";
} else {
    tableName_tDBOutput_1 = dbschema_tDBOutput_1 + "].[" + "matches_with_views_interactions";
}
	int count_tDBOutput_1=0;

        java.sql.PreparedStatement pstmt_tDBOutput_1 = conn_tDBOutput_1.prepareStatement("SELECT COUNT(1) FROM [" + tableName_tDBOutput_1 + "] WHERE [match_id] = ?");
        resourceMap.put("pstmt_tDBOutput_1", pstmt_tDBOutput_1);
        String insert_tDBOutput_1 = "INSERT INTO [" + tableName_tDBOutput_1 + "] ([match_id],[match_number],[tournament_name],[round],[winner],[total_points_won_t1],[total_points_won_t2],[break_points_converted_t1],[break_points_converted_t2],[longest_streak_t1],[longest_streak_t2],[aces_t1],[aces_t2],[double_faults_t1],[double_faults_t2],[won_on_1st_serve_t1],[won_on_1st_serve_t2],[won_on_2nd_serve_t1],[won_on_2nd_serve_t2],[service_games_t1],[service_games_t2],[won_on_1st_return_t1],[won_on_1st_return_t2],[won_on_2nd_return_t1],[won_on_2nd_return_t2],[return_games_t1],[return_games_t2],[total_won_on_serve_t1],[total_won_on_serve_t2],[total_won_on_return_t1],[total_won_on_return_t2],[date],[team1_player1_name],[team1_player2_name],[team2_player1_name],[team2_player2_name],[match_info_added],[t1_s1],[t2_s1],[t1_s2],[t2_s2],[t1_s3],[t2_s3],[views],[interactions]) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        java.sql.PreparedStatement pstmtInsert_tDBOutput_1 = conn_tDBOutput_1.prepareStatement(insert_tDBOutput_1);
        resourceMap.put("pstmtInsert_tDBOutput_1", pstmtInsert_tDBOutput_1);
        String update_tDBOutput_1 = "UPDATE [" + tableName_tDBOutput_1 + "] SET [match_number] = ?,[tournament_name] = ?,[round] = ?,[winner] = ?,[total_points_won_t1] = ?,[total_points_won_t2] = ?,[break_points_converted_t1] = ?,[break_points_converted_t2] = ?,[longest_streak_t1] = ?,[longest_streak_t2] = ?,[aces_t1] = ?,[aces_t2] = ?,[double_faults_t1] = ?,[double_faults_t2] = ?,[won_on_1st_serve_t1] = ?,[won_on_1st_serve_t2] = ?,[won_on_2nd_serve_t1] = ?,[won_on_2nd_serve_t2] = ?,[service_games_t1] = ?,[service_games_t2] = ?,[won_on_1st_return_t1] = ?,[won_on_1st_return_t2] = ?,[won_on_2nd_return_t1] = ?,[won_on_2nd_return_t2] = ?,[return_games_t1] = ?,[return_games_t2] = ?,[total_won_on_serve_t1] = ?,[total_won_on_serve_t2] = ?,[total_won_on_return_t1] = ?,[total_won_on_return_t2] = ?,[date] = ?,[team1_player1_name] = ?,[team1_player2_name] = ?,[team2_player1_name] = ?,[team2_player2_name] = ?,[match_info_added] = ?,[t1_s1] = ?,[t2_s1] = ?,[t1_s2] = ?,[t2_s2] = ?,[t1_s3] = ?,[t2_s3] = ?,[views] = ?,[interactions] = ? WHERE [match_id] = ?";
        java.sql.PreparedStatement pstmtUpdate_tDBOutput_1 = conn_tDBOutput_1.prepareStatement(update_tDBOutput_1);
        resourceMap.put("pstmtUpdate_tDBOutput_1", pstmtUpdate_tDBOutput_1);

 



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
dim_matchsStruct dim_matchs_tmp = new dim_matchsStruct();
// ###############################

        
        



        









 



/**
 * [tMap_1 begin ] stop
 */



	
	/**
	 * [tFilterRow_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tFilterRow_1", false);
		start_Hash.put("tFilterRow_1", System.currentTimeMillis());
		
	
	currentComponent="tFilterRow_1";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row2");
					}
				
		int tos_count_tFilterRow_1 = 0;
		
    int nb_line_tFilterRow_1 = 0;
    int nb_line_ok_tFilterRow_1 = 0;
    int nb_line_reject_tFilterRow_1 = 0;

    class Operator_tFilterRow_1 {
      private String sErrorMsg = "";
      private boolean bMatchFlag = true;
      private String sUnionFlag = "&&";

      public Operator_tFilterRow_1(String unionFlag){
        sUnionFlag = unionFlag;
        bMatchFlag =  "||".equals(unionFlag) ? false : true;
      }

      public String getErrorMsg() {
        if (sErrorMsg != null && sErrorMsg.length() > 1)
          return sErrorMsg.substring(1);
        else 
          return null;
      }

      public boolean getMatchFlag() {
        return bMatchFlag;
      }

      public void matches(boolean partMatched, String reason) {
        // no need to care about the next judgement
        if ("||".equals(sUnionFlag) && bMatchFlag){
          return;
        }

        if (!partMatched) {
          sErrorMsg += "|" + reason;
        }

        if ("||".equals(sUnionFlag))
          bMatchFlag = bMatchFlag || partMatched;
        else
          bMatchFlag = bMatchFlag && partMatched;
      }
    }

 



/**
 * [tFilterRow_1 begin ] stop
 */



	
	/**
	 * [tUniqRow_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tUniqRow_1", false);
		start_Hash.put("tUniqRow_1", System.currentTimeMillis());
		
	
	currentComponent="tUniqRow_1";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row4");
					}
				
		int tos_count_tUniqRow_1 = 0;
		

	
		class KeyStruct_tUniqRow_1 {
	
			private static final int DEFAULT_HASHCODE = 1;
		    private static final int PRIME = 31;
		    private int hashCode = DEFAULT_HASHCODE;
		    public boolean hashCodeDirty = true;
	
	        
					Integer match_id;        
	        
		    @Override
			public int hashCode() {
				if (this.hashCodeDirty) {
					final int prime = PRIME;
					int result = DEFAULT_HASHCODE;
			
								result = prime * result + ((this.match_id == null) ? 0 : this.match_id.hashCode());
								
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
				final KeyStruct_tUniqRow_1 other = (KeyStruct_tUniqRow_1) obj;
				
									if (this.match_id == null) {
										if (other.match_id != null) 
											return false;
								
									} else if (!this.match_id.equals(other.match_id))
								 
										return false;
								
				
				return true;
			}
	  
	        
		}

	
int nb_uniques_tUniqRow_1 = 0;
int nb_duplicates_tUniqRow_1 = 0;
KeyStruct_tUniqRow_1 finder_tUniqRow_1 = new KeyStruct_tUniqRow_1();
java.util.Set<KeyStruct_tUniqRow_1> keystUniqRow_1 = new java.util.HashSet<KeyStruct_tUniqRow_1>(); 

 



/**
 * [tUniqRow_1 begin ] stop
 */



	
	/**
	 * [tDBInput_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tDBInput_1", false);
		start_Hash.put("tDBInput_1", System.currentTimeMillis());
		
	
	currentComponent="tDBInput_1";

	
		int tos_count_tDBInput_1 = 0;
		
	
    
	
			org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_1 = org.talend.designer.components.util.mssql.MSSqlUtilFactory.getMSSqlGenerateTimestampUtil();
			
			java.util.List<String> talendToDBList_tDBInput_1 = new java.util.ArrayList();
			String[] talendToDBArray_tDBInput_1  = new String[]{"FLOAT","NUMERIC","NUMERIC IDENTITY","DECIMAL","DECIMAL IDENTITY","REAL"}; 
			java.util.Collections.addAll(talendToDBList_tDBInput_1, talendToDBArray_tDBInput_1); 
		    int nb_line_tDBInput_1 = 0;
		    java.sql.Connection conn_tDBInput_1 = null;
				String driverClass_tDBInput_1 = "net.sourceforge.jtds.jdbc.Driver";
			    java.lang.Class jdbcclazz_tDBInput_1 = java.lang.Class.forName(driverClass_tDBInput_1);
				String dbUser_tDBInput_1 = "Padelle";
				
				 
	final String decryptedPassword_tDBInput_1 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:O+hRzEQBb5mLYr1iGGlZjiNNDsn1q4NiqeSUpO6g6iX/65M=");
				
				String dbPwd_tDBInput_1 = decryptedPassword_tDBInput_1;
				
		    String port_tDBInput_1 = "1433";
		    String dbname_tDBInput_1 = "SA_PADEL" ;
			String url_tDBInput_1 = "jdbc:jtds:sqlserver://" + "DESKTOP-QJ70MNR" ;
		    if (!"".equals(port_tDBInput_1)) {
		    	url_tDBInput_1 += ":" + "1433";
		    }
		    if (!"".equals(dbname_tDBInput_1)) {
				url_tDBInput_1 += "//" + "SA_PADEL"; 
		    }
		    url_tDBInput_1 += ";appName=" + projectName + ";" + "";
		    String dbschema_tDBInput_1 = "";
				
				conn_tDBInput_1 = java.sql.DriverManager.getConnection(url_tDBInput_1,dbUser_tDBInput_1,dbPwd_tDBInput_1);
		        
		    
			java.sql.Statement stmt_tDBInput_1 = conn_tDBInput_1.createStatement();

		    String dbquery_tDBInput_1 = "SELECT matches.tournament_name,\n		matches.match_id,\n		matches.\"round\",\n		matches.winner,\n		matches.total_points_won_t"
+"1,\n		matches.total_points_won_t2,\n		matches.break_points_converted_t1,\n		matches.break_points_converted_t2,\n		matches.lo"
+"ngest_streak_t1,\n		matches.longest_streak_t2,\n		matches.aces_t1,\n		matches.aces_t2,\n		matches.double_faults_t1,\n		matche"
+"s.double_faults_t2,\n		matches.won_on_1st_serve_t1,\n		matches.won_on_1st_serve_t2,\n		matches.won_on_2nd_serve_t1,\n		match"
+"es.won_on_2nd_serve_t2,\n		matches.service_games_t1,\n		matches.service_games_t2,\n		matches.won_on_1st_return_t1,\n		matche"
+"s.won_on_1st_return_t2,\n		matches.won_on_2nd_return_t1,\n		matches.won_on_2nd_return_t2,\n		matches.return_games_t1,\n		mat"
+"ches.return_games_t2,\n		matches.total_won_on_serve_t1,\n		matches.total_won_on_serve_t2,\n		matches.total_won_on_return_t1"
+",\n		matches.total_won_on_return_t2,\n		matches.date,\n		matches.team1_player1_name,\n		matches.team1_player2_name,\n		matche"
+"s.team2_player1_name,\n		matches.team2_player2_name,\n		matches.match_info_added,\n		matches.t1_s1,\n		matches.t2_s1,\n		matc"
+"hes.t1_s2,\n		matches.t2_s2,\n		matches.t1_s3,\n		matches.t2_s3,\n		matches.views,\n		matches.interactions\nFROM	matches";
		    

            	globalMap.put("tDBInput_1_QUERY",dbquery_tDBInput_1);
		    java.sql.ResultSet rs_tDBInput_1 = null;

		    try {
		    	rs_tDBInput_1 = stmt_tDBInput_1.executeQuery(dbquery_tDBInput_1);
		    	java.sql.ResultSetMetaData rsmd_tDBInput_1 = rs_tDBInput_1.getMetaData();
		    	int colQtyInRs_tDBInput_1 = rsmd_tDBInput_1.getColumnCount();

		    String tmpContent_tDBInput_1 = null;
		    
		    
		    while (rs_tDBInput_1.next()) {
		        nb_line_tDBInput_1++;
		        
							if(colQtyInRs_tDBInput_1 < 1) {
								row4.tournament_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(1);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(1).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.tournament_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row4.tournament_name = tmpContent_tDBInput_1;
                }
            } else {
                row4.tournament_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 2) {
								row4.match_id = null;
							} else {
		                          
            row4.match_id = rs_tDBInput_1.getInt(2);
            if(rs_tDBInput_1.wasNull()){
                    row4.match_id = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 3) {
								row4.round = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(3);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(3).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.round = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row4.round = tmpContent_tDBInput_1;
                }
            } else {
                row4.round = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 4) {
								row4.winner = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(4);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(4).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.winner = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row4.winner = tmpContent_tDBInput_1;
                }
            } else {
                row4.winner = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 5) {
								row4.total_points_won_t1 = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(5);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(5).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.total_points_won_t1 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row4.total_points_won_t1 = tmpContent_tDBInput_1;
                }
            } else {
                row4.total_points_won_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 6) {
								row4.total_points_won_t2 = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(6);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(6).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.total_points_won_t2 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row4.total_points_won_t2 = tmpContent_tDBInput_1;
                }
            } else {
                row4.total_points_won_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 7) {
								row4.break_points_converted_t1 = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(7);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(7).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.break_points_converted_t1 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row4.break_points_converted_t1 = tmpContent_tDBInput_1;
                }
            } else {
                row4.break_points_converted_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 8) {
								row4.break_points_converted_t2 = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(8);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(8).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.break_points_converted_t2 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row4.break_points_converted_t2 = tmpContent_tDBInput_1;
                }
            } else {
                row4.break_points_converted_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 9) {
								row4.longest_streak_t1 = null;
							} else {
		                          
            row4.longest_streak_t1 = rs_tDBInput_1.getInt(9);
            if(rs_tDBInput_1.wasNull()){
                    row4.longest_streak_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 10) {
								row4.longest_streak_t2 = null;
							} else {
		                          
            row4.longest_streak_t2 = rs_tDBInput_1.getInt(10);
            if(rs_tDBInput_1.wasNull()){
                    row4.longest_streak_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 11) {
								row4.aces_t1 = null;
							} else {
		                          
            row4.aces_t1 = rs_tDBInput_1.getInt(11);
            if(rs_tDBInput_1.wasNull()){
                    row4.aces_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 12) {
								row4.aces_t2 = null;
							} else {
		                          
            row4.aces_t2 = rs_tDBInput_1.getInt(12);
            if(rs_tDBInput_1.wasNull()){
                    row4.aces_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 13) {
								row4.double_faults_t1 = null;
							} else {
		                          
            row4.double_faults_t1 = rs_tDBInput_1.getInt(13);
            if(rs_tDBInput_1.wasNull()){
                    row4.double_faults_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 14) {
								row4.double_faults_t2 = null;
							} else {
		                          
            row4.double_faults_t2 = rs_tDBInput_1.getInt(14);
            if(rs_tDBInput_1.wasNull()){
                    row4.double_faults_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 15) {
								row4.won_on_1st_serve_t1 = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(15);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(15).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.won_on_1st_serve_t1 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row4.won_on_1st_serve_t1 = tmpContent_tDBInput_1;
                }
            } else {
                row4.won_on_1st_serve_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 16) {
								row4.won_on_1st_serve_t2 = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(16);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(16).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.won_on_1st_serve_t2 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row4.won_on_1st_serve_t2 = tmpContent_tDBInput_1;
                }
            } else {
                row4.won_on_1st_serve_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 17) {
								row4.won_on_2nd_serve_t1 = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(17);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(17).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.won_on_2nd_serve_t1 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row4.won_on_2nd_serve_t1 = tmpContent_tDBInput_1;
                }
            } else {
                row4.won_on_2nd_serve_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 18) {
								row4.won_on_2nd_serve_t2 = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(18);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(18).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.won_on_2nd_serve_t2 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row4.won_on_2nd_serve_t2 = tmpContent_tDBInput_1;
                }
            } else {
                row4.won_on_2nd_serve_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 19) {
								row4.service_games_t1 = null;
							} else {
		                          
            row4.service_games_t1 = rs_tDBInput_1.getInt(19);
            if(rs_tDBInput_1.wasNull()){
                    row4.service_games_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 20) {
								row4.service_games_t2 = null;
							} else {
		                          
            row4.service_games_t2 = rs_tDBInput_1.getInt(20);
            if(rs_tDBInput_1.wasNull()){
                    row4.service_games_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 21) {
								row4.won_on_1st_return_t1 = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(21);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(21).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.won_on_1st_return_t1 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row4.won_on_1st_return_t1 = tmpContent_tDBInput_1;
                }
            } else {
                row4.won_on_1st_return_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 22) {
								row4.won_on_1st_return_t2 = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(22);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(22).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.won_on_1st_return_t2 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row4.won_on_1st_return_t2 = tmpContent_tDBInput_1;
                }
            } else {
                row4.won_on_1st_return_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 23) {
								row4.won_on_2nd_return_t1 = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(23);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(23).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.won_on_2nd_return_t1 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row4.won_on_2nd_return_t1 = tmpContent_tDBInput_1;
                }
            } else {
                row4.won_on_2nd_return_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 24) {
								row4.won_on_2nd_return_t2 = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(24);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(24).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.won_on_2nd_return_t2 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row4.won_on_2nd_return_t2 = tmpContent_tDBInput_1;
                }
            } else {
                row4.won_on_2nd_return_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 25) {
								row4.return_games_t1 = null;
							} else {
		                          
            row4.return_games_t1 = rs_tDBInput_1.getInt(25);
            if(rs_tDBInput_1.wasNull()){
                    row4.return_games_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 26) {
								row4.return_games_t2 = null;
							} else {
		                          
            row4.return_games_t2 = rs_tDBInput_1.getInt(26);
            if(rs_tDBInput_1.wasNull()){
                    row4.return_games_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 27) {
								row4.total_won_on_serve_t1 = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(27);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(27).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.total_won_on_serve_t1 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row4.total_won_on_serve_t1 = tmpContent_tDBInput_1;
                }
            } else {
                row4.total_won_on_serve_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 28) {
								row4.total_won_on_serve_t2 = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(28);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(28).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.total_won_on_serve_t2 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row4.total_won_on_serve_t2 = tmpContent_tDBInput_1;
                }
            } else {
                row4.total_won_on_serve_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 29) {
								row4.total_won_on_return_t1 = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(29);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(29).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.total_won_on_return_t1 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row4.total_won_on_return_t1 = tmpContent_tDBInput_1;
                }
            } else {
                row4.total_won_on_return_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 30) {
								row4.total_won_on_return_t2 = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(30);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(30).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.total_won_on_return_t2 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row4.total_won_on_return_t2 = tmpContent_tDBInput_1;
                }
            } else {
                row4.total_won_on_return_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 31) {
								row4.date = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(31);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(31).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.date = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row4.date = tmpContent_tDBInput_1;
                }
            } else {
                row4.date = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 32) {
								row4.team1_player1_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(32);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(32).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.team1_player1_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row4.team1_player1_name = tmpContent_tDBInput_1;
                }
            } else {
                row4.team1_player1_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 33) {
								row4.team1_player2_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(33);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(33).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.team1_player2_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row4.team1_player2_name = tmpContent_tDBInput_1;
                }
            } else {
                row4.team1_player2_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 34) {
								row4.team2_player1_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(34);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(34).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.team2_player1_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row4.team2_player1_name = tmpContent_tDBInput_1;
                }
            } else {
                row4.team2_player1_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 35) {
								row4.team2_player2_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(35);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(35).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.team2_player2_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row4.team2_player2_name = tmpContent_tDBInput_1;
                }
            } else {
                row4.team2_player2_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 36) {
								row4.match_info_added = null;
							} else {
		                          
            row4.match_info_added = rs_tDBInput_1.getInt(36);
            if(rs_tDBInput_1.wasNull()){
                    row4.match_info_added = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 37) {
								row4.t1_s1 = null;
							} else {
		                          
            row4.t1_s1 = rs_tDBInput_1.getInt(37);
            if(rs_tDBInput_1.wasNull()){
                    row4.t1_s1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 38) {
								row4.t2_s1 = null;
							} else {
		                          
            row4.t2_s1 = rs_tDBInput_1.getInt(38);
            if(rs_tDBInput_1.wasNull()){
                    row4.t2_s1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 39) {
								row4.t1_s2 = null;
							} else {
		                          
            row4.t1_s2 = rs_tDBInput_1.getInt(39);
            if(rs_tDBInput_1.wasNull()){
                    row4.t1_s2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 40) {
								row4.t2_s2 = null;
							} else {
		                          
            row4.t2_s2 = rs_tDBInput_1.getInt(40);
            if(rs_tDBInput_1.wasNull()){
                    row4.t2_s2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 41) {
								row4.t1_s3 = null;
							} else {
		                          
            row4.t1_s3 = rs_tDBInput_1.getInt(41);
            if(rs_tDBInput_1.wasNull()){
                    row4.t1_s3 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 42) {
								row4.t2_s3 = null;
							} else {
		                          
            row4.t2_s3 = rs_tDBInput_1.getInt(42);
            if(rs_tDBInput_1.wasNull()){
                    row4.t2_s3 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 43) {
								row4.views = null;
							} else {
		                          
            row4.views = rs_tDBInput_1.getInt(43);
            if(rs_tDBInput_1.wasNull()){
                    row4.views = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 44) {
								row4.interactions = null;
							} else {
		                          
            row4.interactions = rs_tDBInput_1.getInt(44);
            if(rs_tDBInput_1.wasNull()){
                    row4.interactions = null;
            }
		                    }
					





 



/**
 * [tDBInput_1 begin ] stop
 */
	
	/**
	 * [tDBInput_1 main ] start
	 */

	

	
	
	currentComponent="tDBInput_1";

	

 


	tos_count_tDBInput_1++;

/**
 * [tDBInput_1 main ] stop
 */
	
	/**
	 * [tDBInput_1 process_data_begin ] start
	 */

	

	
	
	currentComponent="tDBInput_1";

	

 



/**
 * [tDBInput_1 process_data_begin ] stop
 */

	
	/**
	 * [tUniqRow_1 main ] start
	 */

	

	
	
	currentComponent="tUniqRow_1";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row4"
						
						);
					}
					
row2 = null;			
finder_tUniqRow_1.match_id = row4.match_id;	
finder_tUniqRow_1.hashCodeDirty = true;
if (!keystUniqRow_1.contains(finder_tUniqRow_1)) {
		KeyStruct_tUniqRow_1 new_tUniqRow_1 = new KeyStruct_tUniqRow_1();

		
new_tUniqRow_1.match_id = row4.match_id;
		
		keystUniqRow_1.add(new_tUniqRow_1);if(row2 == null){ 
	
	row2 = new row2Struct();
}row2.tournament_name = row4.tournament_name;			row2.match_id = row4.match_id;			row2.round = row4.round;			row2.winner = row4.winner;			row2.total_points_won_t1 = row4.total_points_won_t1;			row2.total_points_won_t2 = row4.total_points_won_t2;			row2.break_points_converted_t1 = row4.break_points_converted_t1;			row2.break_points_converted_t2 = row4.break_points_converted_t2;			row2.longest_streak_t1 = row4.longest_streak_t1;			row2.longest_streak_t2 = row4.longest_streak_t2;			row2.aces_t1 = row4.aces_t1;			row2.aces_t2 = row4.aces_t2;			row2.double_faults_t1 = row4.double_faults_t1;			row2.double_faults_t2 = row4.double_faults_t2;			row2.won_on_1st_serve_t1 = row4.won_on_1st_serve_t1;			row2.won_on_1st_serve_t2 = row4.won_on_1st_serve_t2;			row2.won_on_2nd_serve_t1 = row4.won_on_2nd_serve_t1;			row2.won_on_2nd_serve_t2 = row4.won_on_2nd_serve_t2;			row2.service_games_t1 = row4.service_games_t1;			row2.service_games_t2 = row4.service_games_t2;			row2.won_on_1st_return_t1 = row4.won_on_1st_return_t1;			row2.won_on_1st_return_t2 = row4.won_on_1st_return_t2;			row2.won_on_2nd_return_t1 = row4.won_on_2nd_return_t1;			row2.won_on_2nd_return_t2 = row4.won_on_2nd_return_t2;			row2.return_games_t1 = row4.return_games_t1;			row2.return_games_t2 = row4.return_games_t2;			row2.total_won_on_serve_t1 = row4.total_won_on_serve_t1;			row2.total_won_on_serve_t2 = row4.total_won_on_serve_t2;			row2.total_won_on_return_t1 = row4.total_won_on_return_t1;			row2.total_won_on_return_t2 = row4.total_won_on_return_t2;			row2.date = row4.date;			row2.team1_player1_name = row4.team1_player1_name;			row2.team1_player2_name = row4.team1_player2_name;			row2.team2_player1_name = row4.team2_player1_name;			row2.team2_player2_name = row4.team2_player2_name;			row2.match_info_added = row4.match_info_added;			row2.t1_s1 = row4.t1_s1;			row2.t2_s1 = row4.t2_s1;			row2.t1_s2 = row4.t1_s2;			row2.t2_s2 = row4.t2_s2;			row2.t1_s3 = row4.t1_s3;			row2.t2_s3 = row4.t2_s3;			row2.views = row4.views;			row2.interactions = row4.interactions;					
		nb_uniques_tUniqRow_1++;
	} else {
	  nb_duplicates_tUniqRow_1++;
	}

 


	tos_count_tUniqRow_1++;

/**
 * [tUniqRow_1 main ] stop
 */
	
	/**
	 * [tUniqRow_1 process_data_begin ] start
	 */

	

	
	
	currentComponent="tUniqRow_1";

	

 



/**
 * [tUniqRow_1 process_data_begin ] stop
 */
// Start of branch "row2"
if(row2 != null) { 



	
	/**
	 * [tFilterRow_1 main ] start
	 */

	

	
	
	currentComponent="tFilterRow_1";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row2"
						
						);
					}
					

          row1 = null;
    Operator_tFilterRow_1 ope_tFilterRow_1 = new Operator_tFilterRow_1("&&");
	        ope_tFilterRow_1.matches((row2.tournament_name != null)
	                       , "tournament_name!=null failed");
    
    if (ope_tFilterRow_1.getMatchFlag()) {
              if(row1 == null){ 
                row1 = new row1Struct();
              }
               row1.tournament_name = row2.tournament_name;
               row1.match_id = row2.match_id;
               row1.round = row2.round;
               row1.winner = row2.winner;
               row1.total_points_won_t1 = row2.total_points_won_t1;
               row1.total_points_won_t2 = row2.total_points_won_t2;
               row1.break_points_converted_t1 = row2.break_points_converted_t1;
               row1.break_points_converted_t2 = row2.break_points_converted_t2;
               row1.longest_streak_t1 = row2.longest_streak_t1;
               row1.longest_streak_t2 = row2.longest_streak_t2;
               row1.aces_t1 = row2.aces_t1;
               row1.aces_t2 = row2.aces_t2;
               row1.double_faults_t1 = row2.double_faults_t1;
               row1.double_faults_t2 = row2.double_faults_t2;
               row1.won_on_1st_serve_t1 = row2.won_on_1st_serve_t1;
               row1.won_on_1st_serve_t2 = row2.won_on_1st_serve_t2;
               row1.won_on_2nd_serve_t1 = row2.won_on_2nd_serve_t1;
               row1.won_on_2nd_serve_t2 = row2.won_on_2nd_serve_t2;
               row1.service_games_t1 = row2.service_games_t1;
               row1.service_games_t2 = row2.service_games_t2;
               row1.won_on_1st_return_t1 = row2.won_on_1st_return_t1;
               row1.won_on_1st_return_t2 = row2.won_on_1st_return_t2;
               row1.won_on_2nd_return_t1 = row2.won_on_2nd_return_t1;
               row1.won_on_2nd_return_t2 = row2.won_on_2nd_return_t2;
               row1.return_games_t1 = row2.return_games_t1;
               row1.return_games_t2 = row2.return_games_t2;
               row1.total_won_on_serve_t1 = row2.total_won_on_serve_t1;
               row1.total_won_on_serve_t2 = row2.total_won_on_serve_t2;
               row1.total_won_on_return_t1 = row2.total_won_on_return_t1;
               row1.total_won_on_return_t2 = row2.total_won_on_return_t2;
               row1.date = row2.date;
               row1.team1_player1_name = row2.team1_player1_name;
               row1.team1_player2_name = row2.team1_player2_name;
               row1.team2_player1_name = row2.team2_player1_name;
               row1.team2_player2_name = row2.team2_player2_name;
               row1.match_info_added = row2.match_info_added;
               row1.t1_s1 = row2.t1_s1;
               row1.t2_s1 = row2.t2_s1;
               row1.t1_s2 = row2.t1_s2;
               row1.t2_s2 = row2.t2_s2;
               row1.t1_s3 = row2.t1_s3;
               row1.t2_s3 = row2.t2_s3;
               row1.views = row2.views;
               row1.interactions = row2.interactions;    
      nb_line_ok_tFilterRow_1++;
    } else {
      nb_line_reject_tFilterRow_1++;
    }

nb_line_tFilterRow_1++;

 


	tos_count_tFilterRow_1++;

/**
 * [tFilterRow_1 main ] stop
 */
	
	/**
	 * [tFilterRow_1 process_data_begin ] start
	 */

	

	
	
	currentComponent="tFilterRow_1";

	

 



/**
 * [tFilterRow_1 process_data_begin ] stop
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
				// Starting Lookup Table "row3" 
				///////////////////////////////////////////////


				
				
                            
 					    boolean forceLooprow3 = false;
       		  	    	
       		  	    	
 							row3Struct row3ObjectFromLookup = null;
                          
		           		  	if(!rejectedInnerJoin_tMap_1) { // G_TM_M_020

								
								hasCasePrimitiveKeyWithNull_tMap_1 = false;
								
                        		    		    row3HashKey.tournament_name = row1.tournament_name ;
                        		    		

								
		                        	row3HashKey.hashCodeDirty = true;
                        		
	  					
	  							
			  					
			  					
	  					
		  							tHash_Lookup_row3.lookup( row3HashKey );

	  							

	  							

 								
		  				
	  								
						
									
  									  		
 								



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

dim_matchs = null;


// # Output table : 'dim_matchs'
dim_matchs_tmp.match_id = Numeric.sequence("s1", 1, 1);
dim_matchs_tmp.match_number = row1.match_id ;
dim_matchs_tmp.tournament_name = row1.tournament_name ;
dim_matchs_tmp.round = row1.round ;
dim_matchs_tmp.winner = row1.winner ;
dim_matchs_tmp.total_points_won_t1 = (row1.total_points_won_t1 == null || row1.total_points_won_t1.trim().isEmpty()) ? 
null : 
Double.valueOf(row1.total_points_won_t1.trim().replace(",", ".").replace("%", "")) ;
dim_matchs_tmp.total_points_won_t2 = (row1.total_points_won_t2== null || row1.total_points_won_t2.trim().isEmpty()) ? 
null : 
Double.valueOf(row1.total_points_won_t2.trim().replace(",", ".").replace("%", "")) ;
dim_matchs_tmp.break_points_converted_t1 = (row1.break_points_converted_t1  == null || row1.break_points_converted_t1.trim().isEmpty()) ? 
null : 
Double.valueOf(row1.break_points_converted_t1.trim().replace(",", ".").replace("%", "")) ;
dim_matchs_tmp.break_points_converted_t2 = (row1.break_points_converted_t2== null || row1.break_points_converted_t2.trim().isEmpty()) ? 
null : 
Double.valueOf(row1.break_points_converted_t2.trim().replace(",", ".").replace("%", "")) ;
dim_matchs_tmp.longest_streak_t1 = row1.longest_streak_t1 ;
dim_matchs_tmp.longest_streak_t2 = row1.longest_streak_t2 ;
dim_matchs_tmp.aces_t1 = row1.aces_t1 ;
dim_matchs_tmp.aces_t2 = row1.aces_t2 ;
dim_matchs_tmp.double_faults_t1 = row1.double_faults_t1 ;
dim_matchs_tmp.double_faults_t2 = row1.double_faults_t2 ;
dim_matchs_tmp.won_on_1st_serve_t1 = (row1.won_on_1st_serve_t1== null || row1.won_on_1st_serve_t1.trim().isEmpty()) ? 
null : 
Double.valueOf(row1.won_on_1st_serve_t1.trim().replace(",", ".").replace("%", "")) ;
dim_matchs_tmp.won_on_1st_serve_t2 = (row1.won_on_1st_serve_t2== null || row1.won_on_1st_serve_t2.trim().isEmpty()) ? 
null : 
Double.valueOf(row1.won_on_1st_serve_t2.trim().replace(",", ".").replace("%", "")) ;
dim_matchs_tmp.won_on_2nd_serve_t1 = (row1.won_on_2nd_serve_t1== null || row1.won_on_2nd_serve_t1.trim().isEmpty()) ? 
null : 
Double.valueOf(row1.won_on_2nd_serve_t1.trim().replace(",", ".").replace("%", "")) ;
dim_matchs_tmp.won_on_2nd_serve_t2 = (row1.won_on_2nd_serve_t2== null || row1.won_on_2nd_serve_t2.trim().isEmpty()) ? 
null : 
Double.valueOf(row1.won_on_2nd_serve_t2.trim().replace(",", ".").replace("%", "")) ;
dim_matchs_tmp.service_games_t1 = row1.service_games_t1 ;
dim_matchs_tmp.service_games_t2 = row1.service_games_t2 ;
dim_matchs_tmp.won_on_1st_return_t1 = (row1.won_on_1st_return_t1== null || row1.won_on_1st_return_t1.trim().isEmpty()) ? 
null : 
Double.valueOf(row1.won_on_1st_return_t1.trim().replace(",", ".").replace("%", "")) ;
dim_matchs_tmp.won_on_1st_return_t2 = (row1.won_on_1st_return_t2== null || row1.won_on_1st_return_t2.trim().isEmpty()) ? 
null : 
Double.valueOf(row1.won_on_1st_return_t2.trim().replace(",", ".").replace("%", "")) ;
dim_matchs_tmp.won_on_2nd_return_t1 = (row1.won_on_2nd_return_t1== null || row1.won_on_2nd_return_t1.trim().isEmpty()) ? 
null : 
Double.valueOf(row1.won_on_2nd_return_t1.trim().replace(",", ".").replace("%", "")) ;
dim_matchs_tmp.won_on_2nd_return_t2 = (row1.won_on_2nd_return_t2== null || row1.won_on_2nd_return_t2.trim().isEmpty()) ? 
null : 
Double.valueOf(row1.won_on_2nd_return_t2.trim().replace(",", ".").replace("%", "")) ;
dim_matchs_tmp.return_games_t1 = row1.return_games_t1 ;
dim_matchs_tmp.return_games_t2 = row1.return_games_t2 ;
dim_matchs_tmp.total_won_on_serve_t1 = (row1.total_won_on_serve_t1 == null || row1.total_won_on_serve_t1.trim().isEmpty()) ? 
null : 
Double.valueOf(row1.total_won_on_serve_t1.trim().replace(",", ".").replace("%", "")) ;
dim_matchs_tmp.total_won_on_serve_t2 = (row1.total_won_on_return_t2 == null || row1.total_won_on_return_t2.trim().isEmpty()) ? 
null : 
Double.valueOf(row1.total_won_on_return_t2.replace(",", ".").replace("%", "").trim()) ;
dim_matchs_tmp.total_won_on_return_t1 = (row1.total_won_on_return_t1== null || row1.total_won_on_return_t1.trim().isEmpty()) ? 
null : 
Double.valueOf(row1.total_won_on_return_t1.replace(",", ".").replace("%", "")) ;
dim_matchs_tmp.total_won_on_return_t2 = (row1.total_won_on_return_t2== null || row1.total_won_on_return_t2.trim().isEmpty()) ? 
null : 
Double.valueOf(row1.total_won_on_return_t2.replace(",", ".").replace("%", "")) ;
dim_matchs_tmp.date = (row1.date == null || row1.date.isEmpty()) ? null :TalendDate.parseDate("dd/MM/yyyy", row1.date ) ;
dim_matchs_tmp.team1_player1_name = row1.team1_player1_name ;
dim_matchs_tmp.team1_player2_name = row1.team1_player2_name ;
dim_matchs_tmp.team2_player1_name = row1.team2_player1_name ;
dim_matchs_tmp.team2_player2_name = row1.team2_player2_name ;
dim_matchs_tmp.match_info_added = row1.match_info_added ;
dim_matchs_tmp.t1_s1 = row1.t1_s1 ;
dim_matchs_tmp.t2_s1 = row1.t2_s1 ;
dim_matchs_tmp.t1_s2 = row1.t1_s2 ;
dim_matchs_tmp.t2_s2 = row1.t2_s2 ;
dim_matchs_tmp.t1_s3 = row1.t1_s3 ;
dim_matchs_tmp.t2_s3 = row1.t2_s3 ;
dim_matchs_tmp.views = row1.views ;
dim_matchs_tmp.interactions = row1.interactions ;
dim_matchs = dim_matchs_tmp;
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
// Start of branch "dim_matchs"
if(dim_matchs != null) { 



	
	/**
	 * [tDBOutput_1 main ] start
	 */

	

	
	
	currentComponent="tDBOutput_1";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"dim_matchs"
						
						);
					}
					



        whetherReject_tDBOutput_1 = false;


                    pstmt_tDBOutput_1.setInt(1, dim_matchs.match_id);

            int checkCount_tDBOutput_1 = -1;
            try (java.sql.ResultSet rs_tDBOutput_1 = pstmt_tDBOutput_1.executeQuery()) {
                while(rs_tDBOutput_1.next()) {
                    checkCount_tDBOutput_1 = rs_tDBOutput_1.getInt(1);
                }
            }
            if(checkCount_tDBOutput_1 > 0) {
                        if(dim_matchs.match_number == null) {
pstmtUpdate_tDBOutput_1.setNull(1, java.sql.Types.INTEGER);
} else {pstmtUpdate_tDBOutput_1.setInt(1, dim_matchs.match_number);
}

                        if(dim_matchs.tournament_name == null) {
pstmtUpdate_tDBOutput_1.setNull(2, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(2, dim_matchs.tournament_name);
}

                        if(dim_matchs.round == null) {
pstmtUpdate_tDBOutput_1.setNull(3, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(3, dim_matchs.round);
}

                        if(dim_matchs.winner == null) {
pstmtUpdate_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(4, dim_matchs.winner);
}

                        if(dim_matchs.total_points_won_t1 == null) {
pstmtUpdate_tDBOutput_1.setNull(5, java.sql.Types.DOUBLE);
} else {pstmtUpdate_tDBOutput_1.setDouble(5, dim_matchs.total_points_won_t1);
}

                        if(dim_matchs.total_points_won_t2 == null) {
pstmtUpdate_tDBOutput_1.setNull(6, java.sql.Types.DOUBLE);
} else {pstmtUpdate_tDBOutput_1.setDouble(6, dim_matchs.total_points_won_t2);
}

                        if(dim_matchs.break_points_converted_t1 == null) {
pstmtUpdate_tDBOutput_1.setNull(7, java.sql.Types.DOUBLE);
} else {pstmtUpdate_tDBOutput_1.setDouble(7, dim_matchs.break_points_converted_t1);
}

                        if(dim_matchs.break_points_converted_t2 == null) {
pstmtUpdate_tDBOutput_1.setNull(8, java.sql.Types.DOUBLE);
} else {pstmtUpdate_tDBOutput_1.setDouble(8, dim_matchs.break_points_converted_t2);
}

                        if(dim_matchs.longest_streak_t1 == null) {
pstmtUpdate_tDBOutput_1.setNull(9, java.sql.Types.INTEGER);
} else {pstmtUpdate_tDBOutput_1.setInt(9, dim_matchs.longest_streak_t1);
}

                        if(dim_matchs.longest_streak_t2 == null) {
pstmtUpdate_tDBOutput_1.setNull(10, java.sql.Types.INTEGER);
} else {pstmtUpdate_tDBOutput_1.setInt(10, dim_matchs.longest_streak_t2);
}

                        if(dim_matchs.aces_t1 == null) {
pstmtUpdate_tDBOutput_1.setNull(11, java.sql.Types.INTEGER);
} else {pstmtUpdate_tDBOutput_1.setInt(11, dim_matchs.aces_t1);
}

                        if(dim_matchs.aces_t2 == null) {
pstmtUpdate_tDBOutput_1.setNull(12, java.sql.Types.INTEGER);
} else {pstmtUpdate_tDBOutput_1.setInt(12, dim_matchs.aces_t2);
}

                        if(dim_matchs.double_faults_t1 == null) {
pstmtUpdate_tDBOutput_1.setNull(13, java.sql.Types.INTEGER);
} else {pstmtUpdate_tDBOutput_1.setInt(13, dim_matchs.double_faults_t1);
}

                        if(dim_matchs.double_faults_t2 == null) {
pstmtUpdate_tDBOutput_1.setNull(14, java.sql.Types.INTEGER);
} else {pstmtUpdate_tDBOutput_1.setInt(14, dim_matchs.double_faults_t2);
}

                        if(dim_matchs.won_on_1st_serve_t1 == null) {
pstmtUpdate_tDBOutput_1.setNull(15, java.sql.Types.DOUBLE);
} else {pstmtUpdate_tDBOutput_1.setDouble(15, dim_matchs.won_on_1st_serve_t1);
}

                        if(dim_matchs.won_on_1st_serve_t2 == null) {
pstmtUpdate_tDBOutput_1.setNull(16, java.sql.Types.DOUBLE);
} else {pstmtUpdate_tDBOutput_1.setDouble(16, dim_matchs.won_on_1st_serve_t2);
}

                        if(dim_matchs.won_on_2nd_serve_t1 == null) {
pstmtUpdate_tDBOutput_1.setNull(17, java.sql.Types.DOUBLE);
} else {pstmtUpdate_tDBOutput_1.setDouble(17, dim_matchs.won_on_2nd_serve_t1);
}

                        if(dim_matchs.won_on_2nd_serve_t2 == null) {
pstmtUpdate_tDBOutput_1.setNull(18, java.sql.Types.DOUBLE);
} else {pstmtUpdate_tDBOutput_1.setDouble(18, dim_matchs.won_on_2nd_serve_t2);
}

                        if(dim_matchs.service_games_t1 == null) {
pstmtUpdate_tDBOutput_1.setNull(19, java.sql.Types.INTEGER);
} else {pstmtUpdate_tDBOutput_1.setInt(19, dim_matchs.service_games_t1);
}

                        if(dim_matchs.service_games_t2 == null) {
pstmtUpdate_tDBOutput_1.setNull(20, java.sql.Types.INTEGER);
} else {pstmtUpdate_tDBOutput_1.setInt(20, dim_matchs.service_games_t2);
}

                        if(dim_matchs.won_on_1st_return_t1 == null) {
pstmtUpdate_tDBOutput_1.setNull(21, java.sql.Types.DOUBLE);
} else {pstmtUpdate_tDBOutput_1.setDouble(21, dim_matchs.won_on_1st_return_t1);
}

                        if(dim_matchs.won_on_1st_return_t2 == null) {
pstmtUpdate_tDBOutput_1.setNull(22, java.sql.Types.DOUBLE);
} else {pstmtUpdate_tDBOutput_1.setDouble(22, dim_matchs.won_on_1st_return_t2);
}

                        if(dim_matchs.won_on_2nd_return_t1 == null) {
pstmtUpdate_tDBOutput_1.setNull(23, java.sql.Types.DOUBLE);
} else {pstmtUpdate_tDBOutput_1.setDouble(23, dim_matchs.won_on_2nd_return_t1);
}

                        if(dim_matchs.won_on_2nd_return_t2 == null) {
pstmtUpdate_tDBOutput_1.setNull(24, java.sql.Types.DOUBLE);
} else {pstmtUpdate_tDBOutput_1.setDouble(24, dim_matchs.won_on_2nd_return_t2);
}

                        if(dim_matchs.return_games_t1 == null) {
pstmtUpdate_tDBOutput_1.setNull(25, java.sql.Types.INTEGER);
} else {pstmtUpdate_tDBOutput_1.setInt(25, dim_matchs.return_games_t1);
}

                        if(dim_matchs.return_games_t2 == null) {
pstmtUpdate_tDBOutput_1.setNull(26, java.sql.Types.INTEGER);
} else {pstmtUpdate_tDBOutput_1.setInt(26, dim_matchs.return_games_t2);
}

                        if(dim_matchs.total_won_on_serve_t1 == null) {
pstmtUpdate_tDBOutput_1.setNull(27, java.sql.Types.DOUBLE);
} else {pstmtUpdate_tDBOutput_1.setDouble(27, dim_matchs.total_won_on_serve_t1);
}

                        if(dim_matchs.total_won_on_serve_t2 == null) {
pstmtUpdate_tDBOutput_1.setNull(28, java.sql.Types.DOUBLE);
} else {pstmtUpdate_tDBOutput_1.setDouble(28, dim_matchs.total_won_on_serve_t2);
}

                        if(dim_matchs.total_won_on_return_t1 == null) {
pstmtUpdate_tDBOutput_1.setNull(29, java.sql.Types.DOUBLE);
} else {pstmtUpdate_tDBOutput_1.setDouble(29, dim_matchs.total_won_on_return_t1);
}

                        if(dim_matchs.total_won_on_return_t2 == null) {
pstmtUpdate_tDBOutput_1.setNull(30, java.sql.Types.DOUBLE);
} else {pstmtUpdate_tDBOutput_1.setDouble(30, dim_matchs.total_won_on_return_t2);
}

                        if(dim_matchs.date != null) {
pstmtUpdate_tDBOutput_1.setTimestamp(31, new java.sql.Timestamp(dim_matchs.date.getTime()));
} else {
pstmtUpdate_tDBOutput_1.setNull(31, java.sql.Types.TIMESTAMP);
}

                        if(dim_matchs.team1_player1_name == null) {
pstmtUpdate_tDBOutput_1.setNull(32, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(32, dim_matchs.team1_player1_name);
}

                        if(dim_matchs.team1_player2_name == null) {
pstmtUpdate_tDBOutput_1.setNull(33, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(33, dim_matchs.team1_player2_name);
}

                        if(dim_matchs.team2_player1_name == null) {
pstmtUpdate_tDBOutput_1.setNull(34, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(34, dim_matchs.team2_player1_name);
}

                        if(dim_matchs.team2_player2_name == null) {
pstmtUpdate_tDBOutput_1.setNull(35, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(35, dim_matchs.team2_player2_name);
}

                        if(dim_matchs.match_info_added == null) {
pstmtUpdate_tDBOutput_1.setNull(36, java.sql.Types.INTEGER);
} else {pstmtUpdate_tDBOutput_1.setInt(36, dim_matchs.match_info_added);
}

                        if(dim_matchs.t1_s1 == null) {
pstmtUpdate_tDBOutput_1.setNull(37, java.sql.Types.INTEGER);
} else {pstmtUpdate_tDBOutput_1.setInt(37, dim_matchs.t1_s1);
}

                        if(dim_matchs.t2_s1 == null) {
pstmtUpdate_tDBOutput_1.setNull(38, java.sql.Types.INTEGER);
} else {pstmtUpdate_tDBOutput_1.setInt(38, dim_matchs.t2_s1);
}

                        if(dim_matchs.t1_s2 == null) {
pstmtUpdate_tDBOutput_1.setNull(39, java.sql.Types.INTEGER);
} else {pstmtUpdate_tDBOutput_1.setInt(39, dim_matchs.t1_s2);
}

                        if(dim_matchs.t2_s2 == null) {
pstmtUpdate_tDBOutput_1.setNull(40, java.sql.Types.INTEGER);
} else {pstmtUpdate_tDBOutput_1.setInt(40, dim_matchs.t2_s2);
}

                        if(dim_matchs.t1_s3 == null) {
pstmtUpdate_tDBOutput_1.setNull(41, java.sql.Types.INTEGER);
} else {pstmtUpdate_tDBOutput_1.setInt(41, dim_matchs.t1_s3);
}

                        if(dim_matchs.t2_s3 == null) {
pstmtUpdate_tDBOutput_1.setNull(42, java.sql.Types.INTEGER);
} else {pstmtUpdate_tDBOutput_1.setInt(42, dim_matchs.t2_s3);
}

                        if(dim_matchs.views == null) {
pstmtUpdate_tDBOutput_1.setNull(43, java.sql.Types.INTEGER);
} else {pstmtUpdate_tDBOutput_1.setInt(43, dim_matchs.views);
}

                        if(dim_matchs.interactions == null) {
pstmtUpdate_tDBOutput_1.setNull(44, java.sql.Types.INTEGER);
} else {pstmtUpdate_tDBOutput_1.setInt(44, dim_matchs.interactions);
}


	                    

                        pstmtUpdate_tDBOutput_1.setInt(45 + count_tDBOutput_1, dim_matchs.match_id);

            try {
                    int processedCount_tDBOutput_1 = pstmtUpdate_tDBOutput_1.executeUpdate();
                    updatedCount_tDBOutput_1 += processedCount_tDBOutput_1;
                    rowsToCommitCount_tDBOutput_1 += processedCount_tDBOutput_1;
                    nb_line_tDBOutput_1++;
        			
                } catch(java.lang.Exception e) {
globalMap.put("tDBOutput_1_ERROR_MESSAGE",e.getMessage());
                    whetherReject_tDBOutput_1 = true;
                    	nb_line_tDBOutput_1++;
                    	
                            System.err.println(e.getMessage());
                }
            } else {
                        pstmtInsert_tDBOutput_1.setInt(1, dim_matchs.match_id);

                        if(dim_matchs.match_number == null) {
pstmtInsert_tDBOutput_1.setNull(2, java.sql.Types.INTEGER);
} else {pstmtInsert_tDBOutput_1.setInt(2, dim_matchs.match_number);
}

                        if(dim_matchs.tournament_name == null) {
pstmtInsert_tDBOutput_1.setNull(3, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(3, dim_matchs.tournament_name);
}

                        if(dim_matchs.round == null) {
pstmtInsert_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(4, dim_matchs.round);
}

                        if(dim_matchs.winner == null) {
pstmtInsert_tDBOutput_1.setNull(5, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(5, dim_matchs.winner);
}

                        if(dim_matchs.total_points_won_t1 == null) {
pstmtInsert_tDBOutput_1.setNull(6, java.sql.Types.DOUBLE);
} else {pstmtInsert_tDBOutput_1.setDouble(6, dim_matchs.total_points_won_t1);
}

                        if(dim_matchs.total_points_won_t2 == null) {
pstmtInsert_tDBOutput_1.setNull(7, java.sql.Types.DOUBLE);
} else {pstmtInsert_tDBOutput_1.setDouble(7, dim_matchs.total_points_won_t2);
}

                        if(dim_matchs.break_points_converted_t1 == null) {
pstmtInsert_tDBOutput_1.setNull(8, java.sql.Types.DOUBLE);
} else {pstmtInsert_tDBOutput_1.setDouble(8, dim_matchs.break_points_converted_t1);
}

                        if(dim_matchs.break_points_converted_t2 == null) {
pstmtInsert_tDBOutput_1.setNull(9, java.sql.Types.DOUBLE);
} else {pstmtInsert_tDBOutput_1.setDouble(9, dim_matchs.break_points_converted_t2);
}

                        if(dim_matchs.longest_streak_t1 == null) {
pstmtInsert_tDBOutput_1.setNull(10, java.sql.Types.INTEGER);
} else {pstmtInsert_tDBOutput_1.setInt(10, dim_matchs.longest_streak_t1);
}

                        if(dim_matchs.longest_streak_t2 == null) {
pstmtInsert_tDBOutput_1.setNull(11, java.sql.Types.INTEGER);
} else {pstmtInsert_tDBOutput_1.setInt(11, dim_matchs.longest_streak_t2);
}

                        if(dim_matchs.aces_t1 == null) {
pstmtInsert_tDBOutput_1.setNull(12, java.sql.Types.INTEGER);
} else {pstmtInsert_tDBOutput_1.setInt(12, dim_matchs.aces_t1);
}

                        if(dim_matchs.aces_t2 == null) {
pstmtInsert_tDBOutput_1.setNull(13, java.sql.Types.INTEGER);
} else {pstmtInsert_tDBOutput_1.setInt(13, dim_matchs.aces_t2);
}

                        if(dim_matchs.double_faults_t1 == null) {
pstmtInsert_tDBOutput_1.setNull(14, java.sql.Types.INTEGER);
} else {pstmtInsert_tDBOutput_1.setInt(14, dim_matchs.double_faults_t1);
}

                        if(dim_matchs.double_faults_t2 == null) {
pstmtInsert_tDBOutput_1.setNull(15, java.sql.Types.INTEGER);
} else {pstmtInsert_tDBOutput_1.setInt(15, dim_matchs.double_faults_t2);
}

                        if(dim_matchs.won_on_1st_serve_t1 == null) {
pstmtInsert_tDBOutput_1.setNull(16, java.sql.Types.DOUBLE);
} else {pstmtInsert_tDBOutput_1.setDouble(16, dim_matchs.won_on_1st_serve_t1);
}

                        if(dim_matchs.won_on_1st_serve_t2 == null) {
pstmtInsert_tDBOutput_1.setNull(17, java.sql.Types.DOUBLE);
} else {pstmtInsert_tDBOutput_1.setDouble(17, dim_matchs.won_on_1st_serve_t2);
}

                        if(dim_matchs.won_on_2nd_serve_t1 == null) {
pstmtInsert_tDBOutput_1.setNull(18, java.sql.Types.DOUBLE);
} else {pstmtInsert_tDBOutput_1.setDouble(18, dim_matchs.won_on_2nd_serve_t1);
}

                        if(dim_matchs.won_on_2nd_serve_t2 == null) {
pstmtInsert_tDBOutput_1.setNull(19, java.sql.Types.DOUBLE);
} else {pstmtInsert_tDBOutput_1.setDouble(19, dim_matchs.won_on_2nd_serve_t2);
}

                        if(dim_matchs.service_games_t1 == null) {
pstmtInsert_tDBOutput_1.setNull(20, java.sql.Types.INTEGER);
} else {pstmtInsert_tDBOutput_1.setInt(20, dim_matchs.service_games_t1);
}

                        if(dim_matchs.service_games_t2 == null) {
pstmtInsert_tDBOutput_1.setNull(21, java.sql.Types.INTEGER);
} else {pstmtInsert_tDBOutput_1.setInt(21, dim_matchs.service_games_t2);
}

                        if(dim_matchs.won_on_1st_return_t1 == null) {
pstmtInsert_tDBOutput_1.setNull(22, java.sql.Types.DOUBLE);
} else {pstmtInsert_tDBOutput_1.setDouble(22, dim_matchs.won_on_1st_return_t1);
}

                        if(dim_matchs.won_on_1st_return_t2 == null) {
pstmtInsert_tDBOutput_1.setNull(23, java.sql.Types.DOUBLE);
} else {pstmtInsert_tDBOutput_1.setDouble(23, dim_matchs.won_on_1st_return_t2);
}

                        if(dim_matchs.won_on_2nd_return_t1 == null) {
pstmtInsert_tDBOutput_1.setNull(24, java.sql.Types.DOUBLE);
} else {pstmtInsert_tDBOutput_1.setDouble(24, dim_matchs.won_on_2nd_return_t1);
}

                        if(dim_matchs.won_on_2nd_return_t2 == null) {
pstmtInsert_tDBOutput_1.setNull(25, java.sql.Types.DOUBLE);
} else {pstmtInsert_tDBOutput_1.setDouble(25, dim_matchs.won_on_2nd_return_t2);
}

                        if(dim_matchs.return_games_t1 == null) {
pstmtInsert_tDBOutput_1.setNull(26, java.sql.Types.INTEGER);
} else {pstmtInsert_tDBOutput_1.setInt(26, dim_matchs.return_games_t1);
}

                        if(dim_matchs.return_games_t2 == null) {
pstmtInsert_tDBOutput_1.setNull(27, java.sql.Types.INTEGER);
} else {pstmtInsert_tDBOutput_1.setInt(27, dim_matchs.return_games_t2);
}

                        if(dim_matchs.total_won_on_serve_t1 == null) {
pstmtInsert_tDBOutput_1.setNull(28, java.sql.Types.DOUBLE);
} else {pstmtInsert_tDBOutput_1.setDouble(28, dim_matchs.total_won_on_serve_t1);
}

                        if(dim_matchs.total_won_on_serve_t2 == null) {
pstmtInsert_tDBOutput_1.setNull(29, java.sql.Types.DOUBLE);
} else {pstmtInsert_tDBOutput_1.setDouble(29, dim_matchs.total_won_on_serve_t2);
}

                        if(dim_matchs.total_won_on_return_t1 == null) {
pstmtInsert_tDBOutput_1.setNull(30, java.sql.Types.DOUBLE);
} else {pstmtInsert_tDBOutput_1.setDouble(30, dim_matchs.total_won_on_return_t1);
}

                        if(dim_matchs.total_won_on_return_t2 == null) {
pstmtInsert_tDBOutput_1.setNull(31, java.sql.Types.DOUBLE);
} else {pstmtInsert_tDBOutput_1.setDouble(31, dim_matchs.total_won_on_return_t2);
}

                        if(dim_matchs.date != null) {
pstmtInsert_tDBOutput_1.setTimestamp(32, new java.sql.Timestamp(dim_matchs.date.getTime()));
} else {
pstmtInsert_tDBOutput_1.setNull(32, java.sql.Types.TIMESTAMP);
}

                        if(dim_matchs.team1_player1_name == null) {
pstmtInsert_tDBOutput_1.setNull(33, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(33, dim_matchs.team1_player1_name);
}

                        if(dim_matchs.team1_player2_name == null) {
pstmtInsert_tDBOutput_1.setNull(34, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(34, dim_matchs.team1_player2_name);
}

                        if(dim_matchs.team2_player1_name == null) {
pstmtInsert_tDBOutput_1.setNull(35, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(35, dim_matchs.team2_player1_name);
}

                        if(dim_matchs.team2_player2_name == null) {
pstmtInsert_tDBOutput_1.setNull(36, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(36, dim_matchs.team2_player2_name);
}

                        if(dim_matchs.match_info_added == null) {
pstmtInsert_tDBOutput_1.setNull(37, java.sql.Types.INTEGER);
} else {pstmtInsert_tDBOutput_1.setInt(37, dim_matchs.match_info_added);
}

                        if(dim_matchs.t1_s1 == null) {
pstmtInsert_tDBOutput_1.setNull(38, java.sql.Types.INTEGER);
} else {pstmtInsert_tDBOutput_1.setInt(38, dim_matchs.t1_s1);
}

                        if(dim_matchs.t2_s1 == null) {
pstmtInsert_tDBOutput_1.setNull(39, java.sql.Types.INTEGER);
} else {pstmtInsert_tDBOutput_1.setInt(39, dim_matchs.t2_s1);
}

                        if(dim_matchs.t1_s2 == null) {
pstmtInsert_tDBOutput_1.setNull(40, java.sql.Types.INTEGER);
} else {pstmtInsert_tDBOutput_1.setInt(40, dim_matchs.t1_s2);
}

                        if(dim_matchs.t2_s2 == null) {
pstmtInsert_tDBOutput_1.setNull(41, java.sql.Types.INTEGER);
} else {pstmtInsert_tDBOutput_1.setInt(41, dim_matchs.t2_s2);
}

                        if(dim_matchs.t1_s3 == null) {
pstmtInsert_tDBOutput_1.setNull(42, java.sql.Types.INTEGER);
} else {pstmtInsert_tDBOutput_1.setInt(42, dim_matchs.t1_s3);
}

                        if(dim_matchs.t2_s3 == null) {
pstmtInsert_tDBOutput_1.setNull(43, java.sql.Types.INTEGER);
} else {pstmtInsert_tDBOutput_1.setInt(43, dim_matchs.t2_s3);
}

                        if(dim_matchs.views == null) {
pstmtInsert_tDBOutput_1.setNull(44, java.sql.Types.INTEGER);
} else {pstmtInsert_tDBOutput_1.setInt(44, dim_matchs.views);
}

                        if(dim_matchs.interactions == null) {
pstmtInsert_tDBOutput_1.setNull(45, java.sql.Types.INTEGER);
} else {pstmtInsert_tDBOutput_1.setInt(45, dim_matchs.interactions);
}

            try {
                    int processedCount_tDBOutput_1 = pstmtInsert_tDBOutput_1.executeUpdate();
                    insertedCount_tDBOutput_1 += processedCount_tDBOutput_1;
                    rowsToCommitCount_tDBOutput_1 += processedCount_tDBOutput_1;
                    nb_line_tDBOutput_1++;
                } catch(java.lang.Exception e) {
globalMap.put("tDBOutput_1_ERROR_MESSAGE",e.getMessage());
                    whetherReject_tDBOutput_1 = true;
                    	nb_line_tDBOutput_1++;
                    	
                            System.err.println(e.getMessage());
                }
            }
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

    	////////////commit every////////////
    			
    		    commitCounter_tDBOutput_1++;
                if(commitEvery_tDBOutput_1 <= commitCounter_tDBOutput_1) {
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

} // End of branch "dim_matchs"




	
	/**
	 * [tMap_1 process_data_end ] start
	 */

	

	
	
	currentComponent="tMap_1";

	

 



/**
 * [tMap_1 process_data_end ] stop
 */

} // End of branch "row1"




	
	/**
	 * [tFilterRow_1 process_data_end ] start
	 */

	

	
	
	currentComponent="tFilterRow_1";

	

 



/**
 * [tFilterRow_1 process_data_end ] stop
 */

} // End of branch "row2"




	
	/**
	 * [tUniqRow_1 process_data_end ] start
	 */

	

	
	
	currentComponent="tUniqRow_1";

	

 



/**
 * [tUniqRow_1 process_data_end ] stop
 */



	
	/**
	 * [tDBInput_1 process_data_end ] start
	 */

	

	
	
	currentComponent="tDBInput_1";

	

 



/**
 * [tDBInput_1 process_data_end ] stop
 */
	
	/**
	 * [tDBInput_1 end ] start
	 */

	

	
	
	currentComponent="tDBInput_1";

	

	}
}finally{
	if (rs_tDBInput_1 != null) {
		rs_tDBInput_1.close();
	}
	if (stmt_tDBInput_1 != null) {
		stmt_tDBInput_1.close();
	}
		if(conn_tDBInput_1 != null && !conn_tDBInput_1.isClosed()) {
			
			conn_tDBInput_1.close();
			
			if("com.mysql.cj.jdbc.Driver".equals((String)globalMap.get("driverClass_"))
			    && routines.system.BundleUtils.inOSGi()) {
			        Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread").
			            getMethod("checkedShutdown").invoke(null, (Object[]) null);
			}
			
		}
}
globalMap.put("tDBInput_1_NB_LINE",nb_line_tDBInput_1);

 

ok_Hash.put("tDBInput_1", true);
end_Hash.put("tDBInput_1", System.currentTimeMillis());




/**
 * [tDBInput_1 end ] stop
 */

	
	/**
	 * [tUniqRow_1 end ] start
	 */

	

	
	
	currentComponent="tUniqRow_1";

	

globalMap.put("tUniqRow_1_NB_UNIQUES",nb_uniques_tUniqRow_1);
globalMap.put("tUniqRow_1_NB_DUPLICATES",nb_duplicates_tUniqRow_1);

				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row4");
			  	}
			  	
 

ok_Hash.put("tUniqRow_1", true);
end_Hash.put("tUniqRow_1", System.currentTimeMillis());




/**
 * [tUniqRow_1 end ] stop
 */

	
	/**
	 * [tFilterRow_1 end ] start
	 */

	

	
	
	currentComponent="tFilterRow_1";

	
    globalMap.put("tFilterRow_1_NB_LINE", nb_line_tFilterRow_1);
    globalMap.put("tFilterRow_1_NB_LINE_OK", nb_line_ok_tFilterRow_1);
    globalMap.put("tFilterRow_1_NB_LINE_REJECT", nb_line_reject_tFilterRow_1);
    

				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row2");
			  	}
			  	
 

ok_Hash.put("tFilterRow_1", true);
end_Hash.put("tFilterRow_1", System.currentTimeMillis());




/**
 * [tFilterRow_1 end ] stop
 */

	
	/**
	 * [tMap_1 end ] start
	 */

	

	
	
	currentComponent="tMap_1";

	


// ###############################
// # Lookup hashes releasing
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

	



        if(pstmtUpdate_tDBOutput_1 != null){
            pstmtUpdate_tDBOutput_1.close();
            resourceMap.remove("pstmtUpdate_tDBOutput_1");
        }
        if(pstmtInsert_tDBOutput_1 != null){
            pstmtInsert_tDBOutput_1.close();
            resourceMap.remove("pstmtInsert_tDBOutput_1");
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
			  		runStat.updateStat(resourceMap,iterateId,2,0,"dim_matchs");
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
					     			globalMap.remove("tHash_Lookup_row3"); 
				     			
				try{
					
	
	/**
	 * [tDBInput_1 finally ] start
	 */

	

	
	
	currentComponent="tDBInput_1";

	

 



/**
 * [tDBInput_1 finally ] stop
 */

	
	/**
	 * [tUniqRow_1 finally ] start
	 */

	

	
	
	currentComponent="tUniqRow_1";

	

 



/**
 * [tUniqRow_1 finally ] stop
 */

	
	/**
	 * [tFilterRow_1 finally ] start
	 */

	

	
	
	currentComponent="tFilterRow_1";

	

 



/**
 * [tFilterRow_1 finally ] stop
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
                java.sql.PreparedStatement pstmtUpdateToClose_tDBOutput_1 = null;
                if ((pstmtUpdateToClose_tDBOutput_1 = (java.sql.PreparedStatement) resourceMap.remove("pstmtUpdate_tDBOutput_1")) != null) {
                    pstmtUpdateToClose_tDBOutput_1.close();
                }
                java.sql.PreparedStatement pstmtInsertToClose_tDBOutput_1 = null;
                if ((pstmtInsertToClose_tDBOutput_1 = (java.sql.PreparedStatement) resourceMap.remove("pstmtInsert_tDBOutput_1")) != null) {
                    pstmtInsertToClose_tDBOutput_1.close();
                }
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
		

		globalMap.put("tDBInput_1_SUBPROCESS_STATE", 1);
	}
	


public static class row3Struct implements routines.system.IPersistableComparableLookupRow<row3Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_dim_matchs = new byte[0];
    static byte[] commonByteArray_DW_PADEL_dim_matchs = new byte[0];
	protected static final int DEFAULT_HASHCODE = 1;
    protected static final int PRIME = 31;
    protected int hashCode = DEFAULT_HASHCODE;
    public boolean hashCodeDirty = true;

    public String loopKey;



	
			    public int match_id;

				public int getMatch_id () {
					return this.match_id;
				}
				
			    public Integer match_number;

				public Integer getMatch_number () {
					return this.match_number;
				}
				
			    public String tournament_name;

				public String getTournament_name () {
					return this.tournament_name;
				}
				
			    public String round;

				public String getRound () {
					return this.round;
				}
				
			    public String winner;

				public String getWinner () {
					return this.winner;
				}
				
			    public Double total_points_won_t1;

				public Double getTotal_points_won_t1 () {
					return this.total_points_won_t1;
				}
				
			    public Double total_points_won_t2;

				public Double getTotal_points_won_t2 () {
					return this.total_points_won_t2;
				}
				
			    public Double break_points_converted_t1;

				public Double getBreak_points_converted_t1 () {
					return this.break_points_converted_t1;
				}
				
			    public Double break_points_converted_t2;

				public Double getBreak_points_converted_t2 () {
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
				
			    public Double won_on_1st_serve_t1;

				public Double getWon_on_1st_serve_t1 () {
					return this.won_on_1st_serve_t1;
				}
				
			    public Double won_on_1st_serve_t2;

				public Double getWon_on_1st_serve_t2 () {
					return this.won_on_1st_serve_t2;
				}
				
			    public Double won_on_2nd_serve_t1;

				public Double getWon_on_2nd_serve_t1 () {
					return this.won_on_2nd_serve_t1;
				}
				
			    public Double won_on_2nd_serve_t2;

				public Double getWon_on_2nd_serve_t2 () {
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
				
			    public Double won_on_1st_return_t1;

				public Double getWon_on_1st_return_t1 () {
					return this.won_on_1st_return_t1;
				}
				
			    public Double won_on_1st_return_t2;

				public Double getWon_on_1st_return_t2 () {
					return this.won_on_1st_return_t2;
				}
				
			    public Double won_on_2nd_return_t1;

				public Double getWon_on_2nd_return_t1 () {
					return this.won_on_2nd_return_t1;
				}
				
			    public Double won_on_2nd_return_t2;

				public Double getWon_on_2nd_return_t2 () {
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
				
			    public Double total_won_on_serve_t1;

				public Double getTotal_won_on_serve_t1 () {
					return this.total_won_on_serve_t1;
				}
				
			    public Double total_won_on_serve_t2;

				public Double getTotal_won_on_serve_t2 () {
					return this.total_won_on_serve_t2;
				}
				
			    public Double total_won_on_return_t1;

				public Double getTotal_won_on_return_t1 () {
					return this.total_won_on_return_t1;
				}
				
			    public Double total_won_on_return_t2;

				public Double getTotal_won_on_return_t2 () {
					return this.total_won_on_return_t2;
				}
				
			    public java.util.Date date;

				public java.util.Date getDate () {
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

		other.match_id = this.match_id;
	            other.match_number = this.match_number;
	            other.tournament_name = this.tournament_name;
	            other.round = this.round;
	            other.winner = this.winner;
	            other.total_points_won_t1 = this.total_points_won_t1;
	            other.total_points_won_t2 = this.total_points_won_t2;
	            other.break_points_converted_t1 = this.break_points_converted_t1;
	            other.break_points_converted_t2 = this.break_points_converted_t2;
	            other.longest_streak_t1 = this.longest_streak_t1;
	            other.longest_streak_t2 = this.longest_streak_t2;
	            other.aces_t1 = this.aces_t1;
	            other.aces_t2 = this.aces_t2;
	            other.double_faults_t1 = this.double_faults_t1;
	            other.double_faults_t2 = this.double_faults_t2;
	            other.won_on_1st_serve_t1 = this.won_on_1st_serve_t1;
	            other.won_on_1st_serve_t2 = this.won_on_1st_serve_t2;
	            other.won_on_2nd_serve_t1 = this.won_on_2nd_serve_t1;
	            other.won_on_2nd_serve_t2 = this.won_on_2nd_serve_t2;
	            other.service_games_t1 = this.service_games_t1;
	            other.service_games_t2 = this.service_games_t2;
	            other.won_on_1st_return_t1 = this.won_on_1st_return_t1;
	            other.won_on_1st_return_t2 = this.won_on_1st_return_t2;
	            other.won_on_2nd_return_t1 = this.won_on_2nd_return_t1;
	            other.won_on_2nd_return_t2 = this.won_on_2nd_return_t2;
	            other.return_games_t1 = this.return_games_t1;
	            other.return_games_t2 = this.return_games_t2;
	            other.total_won_on_serve_t1 = this.total_won_on_serve_t1;
	            other.total_won_on_serve_t2 = this.total_won_on_serve_t2;
	            other.total_won_on_return_t1 = this.total_won_on_return_t1;
	            other.total_won_on_return_t2 = this.total_won_on_return_t2;
	            other.date = this.date;
	            other.team1_player1_name = this.team1_player1_name;
	            other.team1_player2_name = this.team1_player2_name;
	            other.team2_player1_name = this.team2_player1_name;
	            other.team2_player2_name = this.team2_player2_name;
	            other.match_info_added = this.match_info_added;
	            other.t1_s1 = this.t1_s1;
	            other.t2_s1 = this.t2_s1;
	            other.t1_s2 = this.t1_s2;
	            other.t2_s2 = this.t2_s2;
	            other.t1_s3 = this.t1_s3;
	            other.t2_s3 = this.t2_s3;
	            other.views = this.views;
	            other.interactions = this.interactions;
	            
	}

	public void copyKeysDataTo(row3Struct other) {

		other.tournament_name = this.tournament_name;
	            	
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

	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_dim_matchs.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_matchs.length == 0) {
   					commonByteArray_DW_PADEL_dim_matchs = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_matchs = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_dim_matchs, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_matchs, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_dim_matchs.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_matchs.length == 0) {
   					commonByteArray_DW_PADEL_dim_matchs = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_matchs = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_dim_matchs, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_matchs, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_dim_matchs) {

        	try {

        		int length = 0;
		
					this.tournament_name = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_dim_matchs) {

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
		
			            this.match_id = dis.readInt();
					
						this.match_number = readInteger(dis,ois);
					
						this.round = readString(dis,ois);
					
						this.winner = readString(dis,ois);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.total_points_won_t1 = null;
           				} else {
           			    	this.total_points_won_t1 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.total_points_won_t2 = null;
           				} else {
           			    	this.total_points_won_t2 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.break_points_converted_t1 = null;
           				} else {
           			    	this.break_points_converted_t1 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.break_points_converted_t2 = null;
           				} else {
           			    	this.break_points_converted_t2 = dis.readDouble();
           				}
					
						this.longest_streak_t1 = readInteger(dis,ois);
					
						this.longest_streak_t2 = readInteger(dis,ois);
					
						this.aces_t1 = readInteger(dis,ois);
					
						this.aces_t2 = readInteger(dis,ois);
					
						this.double_faults_t1 = readInteger(dis,ois);
					
						this.double_faults_t2 = readInteger(dis,ois);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.won_on_1st_serve_t1 = null;
           				} else {
           			    	this.won_on_1st_serve_t1 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.won_on_1st_serve_t2 = null;
           				} else {
           			    	this.won_on_1st_serve_t2 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.won_on_2nd_serve_t1 = null;
           				} else {
           			    	this.won_on_2nd_serve_t1 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.won_on_2nd_serve_t2 = null;
           				} else {
           			    	this.won_on_2nd_serve_t2 = dis.readDouble();
           				}
					
						this.service_games_t1 = readInteger(dis,ois);
					
						this.service_games_t2 = readInteger(dis,ois);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.won_on_1st_return_t1 = null;
           				} else {
           			    	this.won_on_1st_return_t1 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.won_on_1st_return_t2 = null;
           				} else {
           			    	this.won_on_1st_return_t2 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.won_on_2nd_return_t1 = null;
           				} else {
           			    	this.won_on_2nd_return_t1 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.won_on_2nd_return_t2 = null;
           				} else {
           			    	this.won_on_2nd_return_t2 = dis.readDouble();
           				}
					
						this.return_games_t1 = readInteger(dis,ois);
					
						this.return_games_t2 = readInteger(dis,ois);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.total_won_on_serve_t1 = null;
           				} else {
           			    	this.total_won_on_serve_t1 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.total_won_on_serve_t2 = null;
           				} else {
           			    	this.total_won_on_serve_t2 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.total_won_on_return_t1 = null;
           				} else {
           			    	this.total_won_on_return_t1 = dis.readDouble();
           				}
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.total_won_on_return_t2 = null;
           				} else {
           			    	this.total_won_on_return_t2 = dis.readDouble();
           				}
					
						this.date = readDate(dis,ois);
					
						this.team1_player1_name = readString(dis,ois);
					
						this.team1_player2_name = readString(dis,ois);
					
						this.team2_player1_name = readString(dis,ois);
					
						this.team2_player2_name = readString(dis,ois);
					
						this.match_info_added = readInteger(dis,ois);
					
						this.t1_s1 = readInteger(dis,ois);
					
						this.t2_s1 = readInteger(dis,ois);
					
						this.t1_s2 = readInteger(dis,ois);
					
						this.t2_s2 = readInteger(dis,ois);
					
						this.t1_s3 = readInteger(dis,ois);
					
						this.t2_s3 = readInteger(dis,ois);
					
						this.views = readInteger(dis,ois);
					
						this.interactions = readInteger(dis,ois);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }
    
    public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
        try {
			int length = 0;
		
			            this.match_id = objectIn.readInt();
					
						this.match_number = readInteger(dis,objectIn);
					
						this.round = readString(dis,objectIn);
					
						this.winner = readString(dis,objectIn);
					
			            length = objectIn.readByte();
           				if (length == -1) {
           	    			this.total_points_won_t1 = null;
           				} else {
           			    	this.total_points_won_t1 = objectIn.readDouble();
           				}
					
			            length = objectIn.readByte();
           				if (length == -1) {
           	    			this.total_points_won_t2 = null;
           				} else {
           			    	this.total_points_won_t2 = objectIn.readDouble();
           				}
					
			            length = objectIn.readByte();
           				if (length == -1) {
           	    			this.break_points_converted_t1 = null;
           				} else {
           			    	this.break_points_converted_t1 = objectIn.readDouble();
           				}
					
			            length = objectIn.readByte();
           				if (length == -1) {
           	    			this.break_points_converted_t2 = null;
           				} else {
           			    	this.break_points_converted_t2 = objectIn.readDouble();
           				}
					
						this.longest_streak_t1 = readInteger(dis,objectIn);
					
						this.longest_streak_t2 = readInteger(dis,objectIn);
					
						this.aces_t1 = readInteger(dis,objectIn);
					
						this.aces_t2 = readInteger(dis,objectIn);
					
						this.double_faults_t1 = readInteger(dis,objectIn);
					
						this.double_faults_t2 = readInteger(dis,objectIn);
					
			            length = objectIn.readByte();
           				if (length == -1) {
           	    			this.won_on_1st_serve_t1 = null;
           				} else {
           			    	this.won_on_1st_serve_t1 = objectIn.readDouble();
           				}
					
			            length = objectIn.readByte();
           				if (length == -1) {
           	    			this.won_on_1st_serve_t2 = null;
           				} else {
           			    	this.won_on_1st_serve_t2 = objectIn.readDouble();
           				}
					
			            length = objectIn.readByte();
           				if (length == -1) {
           	    			this.won_on_2nd_serve_t1 = null;
           				} else {
           			    	this.won_on_2nd_serve_t1 = objectIn.readDouble();
           				}
					
			            length = objectIn.readByte();
           				if (length == -1) {
           	    			this.won_on_2nd_serve_t2 = null;
           				} else {
           			    	this.won_on_2nd_serve_t2 = objectIn.readDouble();
           				}
					
						this.service_games_t1 = readInteger(dis,objectIn);
					
						this.service_games_t2 = readInteger(dis,objectIn);
					
			            length = objectIn.readByte();
           				if (length == -1) {
           	    			this.won_on_1st_return_t1 = null;
           				} else {
           			    	this.won_on_1st_return_t1 = objectIn.readDouble();
           				}
					
			            length = objectIn.readByte();
           				if (length == -1) {
           	    			this.won_on_1st_return_t2 = null;
           				} else {
           			    	this.won_on_1st_return_t2 = objectIn.readDouble();
           				}
					
			            length = objectIn.readByte();
           				if (length == -1) {
           	    			this.won_on_2nd_return_t1 = null;
           				} else {
           			    	this.won_on_2nd_return_t1 = objectIn.readDouble();
           				}
					
			            length = objectIn.readByte();
           				if (length == -1) {
           	    			this.won_on_2nd_return_t2 = null;
           				} else {
           			    	this.won_on_2nd_return_t2 = objectIn.readDouble();
           				}
					
						this.return_games_t1 = readInteger(dis,objectIn);
					
						this.return_games_t2 = readInteger(dis,objectIn);
					
			            length = objectIn.readByte();
           				if (length == -1) {
           	    			this.total_won_on_serve_t1 = null;
           				} else {
           			    	this.total_won_on_serve_t1 = objectIn.readDouble();
           				}
					
			            length = objectIn.readByte();
           				if (length == -1) {
           	    			this.total_won_on_serve_t2 = null;
           				} else {
           			    	this.total_won_on_serve_t2 = objectIn.readDouble();
           				}
					
			            length = objectIn.readByte();
           				if (length == -1) {
           	    			this.total_won_on_return_t1 = null;
           				} else {
           			    	this.total_won_on_return_t1 = objectIn.readDouble();
           				}
					
			            length = objectIn.readByte();
           				if (length == -1) {
           	    			this.total_won_on_return_t2 = null;
           				} else {
           			    	this.total_won_on_return_t2 = objectIn.readDouble();
           				}
					
						this.date = readDate(dis,objectIn);
					
						this.team1_player1_name = readString(dis,objectIn);
					
						this.team1_player2_name = readString(dis,objectIn);
					
						this.team2_player1_name = readString(dis,objectIn);
					
						this.team2_player2_name = readString(dis,objectIn);
					
						this.match_info_added = readInteger(dis,objectIn);
					
						this.t1_s1 = readInteger(dis,objectIn);
					
						this.t2_s1 = readInteger(dis,objectIn);
					
						this.t1_s2 = readInteger(dis,objectIn);
					
						this.t2_s2 = readInteger(dis,objectIn);
					
						this.t1_s3 = readInteger(dis,objectIn);
					
						this.t2_s3 = readInteger(dis,objectIn);
					
						this.views = readInteger(dis,objectIn);
					
						this.interactions = readInteger(dis,objectIn);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }

    /**
     * Return a byte array which represents Values data.
     */
    public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
        try {

		
		            	dos.writeInt(this.match_id);
					
					writeInteger(this.match_number, dos, oos);
					
						writeString(this.round, dos, oos);
					
						writeString(this.winner, dos, oos);
					
						if(this.total_points_won_t1 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.total_points_won_t1);
		            	}
					
						if(this.total_points_won_t2 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.total_points_won_t2);
		            	}
					
						if(this.break_points_converted_t1 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.break_points_converted_t1);
		            	}
					
						if(this.break_points_converted_t2 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.break_points_converted_t2);
		            	}
					
					writeInteger(this.longest_streak_t1, dos, oos);
					
					writeInteger(this.longest_streak_t2, dos, oos);
					
					writeInteger(this.aces_t1, dos, oos);
					
					writeInteger(this.aces_t2, dos, oos);
					
					writeInteger(this.double_faults_t1, dos, oos);
					
					writeInteger(this.double_faults_t2, dos, oos);
					
						if(this.won_on_1st_serve_t1 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.won_on_1st_serve_t1);
		            	}
					
						if(this.won_on_1st_serve_t2 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.won_on_1st_serve_t2);
		            	}
					
						if(this.won_on_2nd_serve_t1 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.won_on_2nd_serve_t1);
		            	}
					
						if(this.won_on_2nd_serve_t2 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.won_on_2nd_serve_t2);
		            	}
					
					writeInteger(this.service_games_t1, dos, oos);
					
					writeInteger(this.service_games_t2, dos, oos);
					
						if(this.won_on_1st_return_t1 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.won_on_1st_return_t1);
		            	}
					
						if(this.won_on_1st_return_t2 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.won_on_1st_return_t2);
		            	}
					
						if(this.won_on_2nd_return_t1 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.won_on_2nd_return_t1);
		            	}
					
						if(this.won_on_2nd_return_t2 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.won_on_2nd_return_t2);
		            	}
					
					writeInteger(this.return_games_t1, dos, oos);
					
					writeInteger(this.return_games_t2, dos, oos);
					
						if(this.total_won_on_serve_t1 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.total_won_on_serve_t1);
		            	}
					
						if(this.total_won_on_serve_t2 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.total_won_on_serve_t2);
		            	}
					
						if(this.total_won_on_return_t1 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.total_won_on_return_t1);
		            	}
					
						if(this.total_won_on_return_t2 == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.total_won_on_return_t2);
		            	}
					
						writeDate(this.date, dos, oos);
					
						writeString(this.team1_player1_name, dos, oos);
					
						writeString(this.team1_player2_name, dos, oos);
					
						writeString(this.team2_player1_name, dos, oos);
					
						writeString(this.team2_player2_name, dos, oos);
					
					writeInteger(this.match_info_added, dos, oos);
					
					writeInteger(this.t1_s1, dos, oos);
					
					writeInteger(this.t2_s1, dos, oos);
					
					writeInteger(this.t1_s2, dos, oos);
					
					writeInteger(this.t2_s2, dos, oos);
					
					writeInteger(this.t1_s3, dos, oos);
					
					writeInteger(this.t2_s3, dos, oos);
					
					writeInteger(this.views, dos, oos);
					
					writeInteger(this.interactions, dos, oos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}

    }
    
    public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut){
                try {

		
					objectOut.writeInt(this.match_id);
					
					writeInteger(this.match_number, dos, objectOut);
					
						writeString(this.round, dos, objectOut);
					
						writeString(this.winner, dos, objectOut);
					
						if(this.total_points_won_t1 == null) {
							objectOut.writeByte(-1);
						} else {
							objectOut.writeByte(0);
							objectOut.writeDouble(this.total_points_won_t1);
		            	}
					
						if(this.total_points_won_t2 == null) {
							objectOut.writeByte(-1);
						} else {
							objectOut.writeByte(0);
							objectOut.writeDouble(this.total_points_won_t2);
		            	}
					
						if(this.break_points_converted_t1 == null) {
							objectOut.writeByte(-1);
						} else {
							objectOut.writeByte(0);
							objectOut.writeDouble(this.break_points_converted_t1);
		            	}
					
						if(this.break_points_converted_t2 == null) {
							objectOut.writeByte(-1);
						} else {
							objectOut.writeByte(0);
							objectOut.writeDouble(this.break_points_converted_t2);
		            	}
					
					writeInteger(this.longest_streak_t1, dos, objectOut);
					
					writeInteger(this.longest_streak_t2, dos, objectOut);
					
					writeInteger(this.aces_t1, dos, objectOut);
					
					writeInteger(this.aces_t2, dos, objectOut);
					
					writeInteger(this.double_faults_t1, dos, objectOut);
					
					writeInteger(this.double_faults_t2, dos, objectOut);
					
						if(this.won_on_1st_serve_t1 == null) {
							objectOut.writeByte(-1);
						} else {
							objectOut.writeByte(0);
							objectOut.writeDouble(this.won_on_1st_serve_t1);
		            	}
					
						if(this.won_on_1st_serve_t2 == null) {
							objectOut.writeByte(-1);
						} else {
							objectOut.writeByte(0);
							objectOut.writeDouble(this.won_on_1st_serve_t2);
		            	}
					
						if(this.won_on_2nd_serve_t1 == null) {
							objectOut.writeByte(-1);
						} else {
							objectOut.writeByte(0);
							objectOut.writeDouble(this.won_on_2nd_serve_t1);
		            	}
					
						if(this.won_on_2nd_serve_t2 == null) {
							objectOut.writeByte(-1);
						} else {
							objectOut.writeByte(0);
							objectOut.writeDouble(this.won_on_2nd_serve_t2);
		            	}
					
					writeInteger(this.service_games_t1, dos, objectOut);
					
					writeInteger(this.service_games_t2, dos, objectOut);
					
						if(this.won_on_1st_return_t1 == null) {
							objectOut.writeByte(-1);
						} else {
							objectOut.writeByte(0);
							objectOut.writeDouble(this.won_on_1st_return_t1);
		            	}
					
						if(this.won_on_1st_return_t2 == null) {
							objectOut.writeByte(-1);
						} else {
							objectOut.writeByte(0);
							objectOut.writeDouble(this.won_on_1st_return_t2);
		            	}
					
						if(this.won_on_2nd_return_t1 == null) {
							objectOut.writeByte(-1);
						} else {
							objectOut.writeByte(0);
							objectOut.writeDouble(this.won_on_2nd_return_t1);
		            	}
					
						if(this.won_on_2nd_return_t2 == null) {
							objectOut.writeByte(-1);
						} else {
							objectOut.writeByte(0);
							objectOut.writeDouble(this.won_on_2nd_return_t2);
		            	}
					
					writeInteger(this.return_games_t1, dos, objectOut);
					
					writeInteger(this.return_games_t2, dos, objectOut);
					
						if(this.total_won_on_serve_t1 == null) {
							objectOut.writeByte(-1);
						} else {
							objectOut.writeByte(0);
							objectOut.writeDouble(this.total_won_on_serve_t1);
		            	}
					
						if(this.total_won_on_serve_t2 == null) {
							objectOut.writeByte(-1);
						} else {
							objectOut.writeByte(0);
							objectOut.writeDouble(this.total_won_on_serve_t2);
		            	}
					
						if(this.total_won_on_return_t1 == null) {
							objectOut.writeByte(-1);
						} else {
							objectOut.writeByte(0);
							objectOut.writeDouble(this.total_won_on_return_t1);
		            	}
					
						if(this.total_won_on_return_t2 == null) {
							objectOut.writeByte(-1);
						} else {
							objectOut.writeByte(0);
							objectOut.writeDouble(this.total_won_on_return_t2);
		            	}
					
						writeDate(this.date, dos, objectOut);
					
						writeString(this.team1_player1_name, dos, objectOut);
					
						writeString(this.team1_player2_name, dos, objectOut);
					
						writeString(this.team2_player1_name, dos, objectOut);
					
						writeString(this.team2_player2_name, dos, objectOut);
					
					writeInteger(this.match_info_added, dos, objectOut);
					
					writeInteger(this.t1_s1, dos, objectOut);
					
					writeInteger(this.t2_s1, dos, objectOut);
					
					writeInteger(this.t1_s2, dos, objectOut);
					
					writeInteger(this.t2_s2, dos, objectOut);
					
					writeInteger(this.t1_s3, dos, objectOut);
					
					writeInteger(this.t2_s3, dos, objectOut);
					
					writeInteger(this.views, dos, objectOut);
					
					writeInteger(this.interactions, dos, objectOut);
					
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
		sb.append("match_id="+String.valueOf(match_id));
		sb.append(",match_number="+String.valueOf(match_number));
		sb.append(",tournament_name="+tournament_name);
		sb.append(",round="+round);
		sb.append(",winner="+winner);
		sb.append(",total_points_won_t1="+String.valueOf(total_points_won_t1));
		sb.append(",total_points_won_t2="+String.valueOf(total_points_won_t2));
		sb.append(",break_points_converted_t1="+String.valueOf(break_points_converted_t1));
		sb.append(",break_points_converted_t2="+String.valueOf(break_points_converted_t2));
		sb.append(",longest_streak_t1="+String.valueOf(longest_streak_t1));
		sb.append(",longest_streak_t2="+String.valueOf(longest_streak_t2));
		sb.append(",aces_t1="+String.valueOf(aces_t1));
		sb.append(",aces_t2="+String.valueOf(aces_t2));
		sb.append(",double_faults_t1="+String.valueOf(double_faults_t1));
		sb.append(",double_faults_t2="+String.valueOf(double_faults_t2));
		sb.append(",won_on_1st_serve_t1="+String.valueOf(won_on_1st_serve_t1));
		sb.append(",won_on_1st_serve_t2="+String.valueOf(won_on_1st_serve_t2));
		sb.append(",won_on_2nd_serve_t1="+String.valueOf(won_on_2nd_serve_t1));
		sb.append(",won_on_2nd_serve_t2="+String.valueOf(won_on_2nd_serve_t2));
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
		sb.append(",date="+String.valueOf(date));
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
			   		// source node:tDBInput_2 - inputs:(after_tDBInput_1) outputs:(row3,row3) | target node:tAdvancedHash_row3 - inputs:(row3) outputs:()
			   		// linked node: tMap_1 - inputs:(row1,row3) outputs:(dim_matchs)
			   
			   		org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row3 = 
			   			org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;
			   			
			   
	   			org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct> tHash_Lookup_row3 =org.talend.designer.components.lookup.memory.AdvancedMemoryLookup.
	   						<row3Struct>getLookup(matchingModeEnum_row3);
	   						   
		   	   	   globalMap.put("tHash_Lookup_row3", tHash_Lookup_row3);
		   	   	   
				
           

 



/**
 * [tAdvancedHash_row3 begin ] stop
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
				
				 
	final String decryptedPassword_tDBInput_2 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:xEV7S9te5QGFqV2nV+VUciS7PpWecri91wvPxxrSpiW8W1I=");
				
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

		    String dbquery_tDBInput_2 = "SELECT matches_with_views_interactions.match_id,\n		matches_with_views_interactions.match_number,\n		matches_with_views_i"
+"nteractions.tournament_name,\n		matches_with_views_interactions.\"round\",\n		matches_with_views_interactions.winner,\n		ma"
+"tches_with_views_interactions.total_points_won_t1,\n		matches_with_views_interactions.total_points_won_t2,\n		matches_with"
+"_views_interactions.break_points_converted_t1,\n		matches_with_views_interactions.break_points_converted_t2,\n		matches_wi"
+"th_views_interactions.longest_streak_t1,\n		matches_with_views_interactions.longest_streak_t2,\n		matches_with_views_inter"
+"actions.aces_t1,\n		matches_with_views_interactions.aces_t2,\n		matches_with_views_interactions.double_faults_t1,\n		matche"
+"s_with_views_interactions.double_faults_t2,\n		matches_with_views_interactions.won_on_1st_serve_t1,\n		matches_with_views_"
+"interactions.won_on_1st_serve_t2,\n		matches_with_views_interactions.won_on_2nd_serve_t1,\n		matches_with_views_interactio"
+"ns.won_on_2nd_serve_t2,\n		matches_with_views_interactions.service_games_t1,\n		matches_with_views_interactions.service_ga"
+"mes_t2,\n		matches_with_views_interactions.won_on_1st_return_t1,\n		matches_with_views_interactions.won_on_1st_return_t2,\n"
+"		matches_with_views_interactions.won_on_2nd_return_t1,\n		matches_with_views_interactions.won_on_2nd_return_t2,\n		matche"
+"s_with_views_interactions.return_games_t1,\n		matches_with_views_interactions.return_games_t2,\n		matches_with_views_inter"
+"actions.total_won_on_serve_t1,\n		matches_with_views_interactions.total_won_on_serve_t2,\n		matches_with_views_interaction"
+"s.total_won_on_return_t1,\n		matches_with_views_interactions.total_won_on_return_t2,\n		matches_with_views_interactions.da"
+"te,\n		matches_with_views_interactions.team1_player1_name,\n		matches_with_views_interactions.team1_player2_name,\n		matche"
+"s_with_views_interactions.team2_player1_name,\n		matches_with_views_interactions.team2_player2_name,\n		matches_with_views"
+"_interactions.match_info_added,\n		matches_with_views_interactions.t1_s1,\n		matches_with_views_interactions.t2_s1,\n		matc"
+"hes_with_views_interactions.t1_s2,\n		matches_with_views_interactions.t2_s2,\n		matches_with_views_interactions.t1_s3,\n		m"
+"atches_with_views_interactions.t2_s3,\n		matches_with_views_interactions.views,\n		matches_with_views_interactions.interac"
+"tions\nFROM	matches_with_views_interactions";
		    

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
								row3.match_id = 0;
							} else {
		                          
            row3.match_id = rs_tDBInput_2.getInt(1);
            if(rs_tDBInput_2.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 2) {
								row3.match_number = null;
							} else {
		                          
            row3.match_number = rs_tDBInput_2.getInt(2);
            if(rs_tDBInput_2.wasNull()){
                    row3.match_number = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 3) {
								row3.tournament_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(3);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(3).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.tournament_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row3.tournament_name = tmpContent_tDBInput_2;
                }
            } else {
                row3.tournament_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 4) {
								row3.round = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(4);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(4).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.round = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row3.round = tmpContent_tDBInput_2;
                }
            } else {
                row3.round = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 5) {
								row3.winner = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(5);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(5).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.winner = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row3.winner = tmpContent_tDBInput_2;
                }
            } else {
                row3.winner = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 6) {
								row3.total_points_won_t1 = null;
							} else {
	                         		
            row3.total_points_won_t1 = rs_tDBInput_2.getDouble(6);
            if(rs_tDBInput_2.wasNull()){
                    row3.total_points_won_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 7) {
								row3.total_points_won_t2 = null;
							} else {
	                         		
            row3.total_points_won_t2 = rs_tDBInput_2.getDouble(7);
            if(rs_tDBInput_2.wasNull()){
                    row3.total_points_won_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 8) {
								row3.break_points_converted_t1 = null;
							} else {
	                         		
            row3.break_points_converted_t1 = rs_tDBInput_2.getDouble(8);
            if(rs_tDBInput_2.wasNull()){
                    row3.break_points_converted_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 9) {
								row3.break_points_converted_t2 = null;
							} else {
	                         		
            row3.break_points_converted_t2 = rs_tDBInput_2.getDouble(9);
            if(rs_tDBInput_2.wasNull()){
                    row3.break_points_converted_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 10) {
								row3.longest_streak_t1 = null;
							} else {
		                          
            row3.longest_streak_t1 = rs_tDBInput_2.getInt(10);
            if(rs_tDBInput_2.wasNull()){
                    row3.longest_streak_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 11) {
								row3.longest_streak_t2 = null;
							} else {
		                          
            row3.longest_streak_t2 = rs_tDBInput_2.getInt(11);
            if(rs_tDBInput_2.wasNull()){
                    row3.longest_streak_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 12) {
								row3.aces_t1 = null;
							} else {
		                          
            row3.aces_t1 = rs_tDBInput_2.getInt(12);
            if(rs_tDBInput_2.wasNull()){
                    row3.aces_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 13) {
								row3.aces_t2 = null;
							} else {
		                          
            row3.aces_t2 = rs_tDBInput_2.getInt(13);
            if(rs_tDBInput_2.wasNull()){
                    row3.aces_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 14) {
								row3.double_faults_t1 = null;
							} else {
		                          
            row3.double_faults_t1 = rs_tDBInput_2.getInt(14);
            if(rs_tDBInput_2.wasNull()){
                    row3.double_faults_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 15) {
								row3.double_faults_t2 = null;
							} else {
		                          
            row3.double_faults_t2 = rs_tDBInput_2.getInt(15);
            if(rs_tDBInput_2.wasNull()){
                    row3.double_faults_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 16) {
								row3.won_on_1st_serve_t1 = null;
							} else {
	                         		
            row3.won_on_1st_serve_t1 = rs_tDBInput_2.getDouble(16);
            if(rs_tDBInput_2.wasNull()){
                    row3.won_on_1st_serve_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 17) {
								row3.won_on_1st_serve_t2 = null;
							} else {
	                         		
            row3.won_on_1st_serve_t2 = rs_tDBInput_2.getDouble(17);
            if(rs_tDBInput_2.wasNull()){
                    row3.won_on_1st_serve_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 18) {
								row3.won_on_2nd_serve_t1 = null;
							} else {
	                         		
            row3.won_on_2nd_serve_t1 = rs_tDBInput_2.getDouble(18);
            if(rs_tDBInput_2.wasNull()){
                    row3.won_on_2nd_serve_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 19) {
								row3.won_on_2nd_serve_t2 = null;
							} else {
	                         		
            row3.won_on_2nd_serve_t2 = rs_tDBInput_2.getDouble(19);
            if(rs_tDBInput_2.wasNull()){
                    row3.won_on_2nd_serve_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 20) {
								row3.service_games_t1 = null;
							} else {
		                          
            row3.service_games_t1 = rs_tDBInput_2.getInt(20);
            if(rs_tDBInput_2.wasNull()){
                    row3.service_games_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 21) {
								row3.service_games_t2 = null;
							} else {
		                          
            row3.service_games_t2 = rs_tDBInput_2.getInt(21);
            if(rs_tDBInput_2.wasNull()){
                    row3.service_games_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 22) {
								row3.won_on_1st_return_t1 = null;
							} else {
	                         		
            row3.won_on_1st_return_t1 = rs_tDBInput_2.getDouble(22);
            if(rs_tDBInput_2.wasNull()){
                    row3.won_on_1st_return_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 23) {
								row3.won_on_1st_return_t2 = null;
							} else {
	                         		
            row3.won_on_1st_return_t2 = rs_tDBInput_2.getDouble(23);
            if(rs_tDBInput_2.wasNull()){
                    row3.won_on_1st_return_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 24) {
								row3.won_on_2nd_return_t1 = null;
							} else {
	                         		
            row3.won_on_2nd_return_t1 = rs_tDBInput_2.getDouble(24);
            if(rs_tDBInput_2.wasNull()){
                    row3.won_on_2nd_return_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 25) {
								row3.won_on_2nd_return_t2 = null;
							} else {
	                         		
            row3.won_on_2nd_return_t2 = rs_tDBInput_2.getDouble(25);
            if(rs_tDBInput_2.wasNull()){
                    row3.won_on_2nd_return_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 26) {
								row3.return_games_t1 = null;
							} else {
		                          
            row3.return_games_t1 = rs_tDBInput_2.getInt(26);
            if(rs_tDBInput_2.wasNull()){
                    row3.return_games_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 27) {
								row3.return_games_t2 = null;
							} else {
		                          
            row3.return_games_t2 = rs_tDBInput_2.getInt(27);
            if(rs_tDBInput_2.wasNull()){
                    row3.return_games_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 28) {
								row3.total_won_on_serve_t1 = null;
							} else {
	                         		
            row3.total_won_on_serve_t1 = rs_tDBInput_2.getDouble(28);
            if(rs_tDBInput_2.wasNull()){
                    row3.total_won_on_serve_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 29) {
								row3.total_won_on_serve_t2 = null;
							} else {
	                         		
            row3.total_won_on_serve_t2 = rs_tDBInput_2.getDouble(29);
            if(rs_tDBInput_2.wasNull()){
                    row3.total_won_on_serve_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 30) {
								row3.total_won_on_return_t1 = null;
							} else {
	                         		
            row3.total_won_on_return_t1 = rs_tDBInput_2.getDouble(30);
            if(rs_tDBInput_2.wasNull()){
                    row3.total_won_on_return_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 31) {
								row3.total_won_on_return_t2 = null;
							} else {
	                         		
            row3.total_won_on_return_t2 = rs_tDBInput_2.getDouble(31);
            if(rs_tDBInput_2.wasNull()){
                    row3.total_won_on_return_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 32) {
								row3.date = null;
							} else {
										
			row3.date = mssqlGTU_tDBInput_2.getDate(rsmd_tDBInput_2, rs_tDBInput_2, 32);
			
		                    }
							if(colQtyInRs_tDBInput_2 < 33) {
								row3.team1_player1_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(33);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(33).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.team1_player1_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row3.team1_player1_name = tmpContent_tDBInput_2;
                }
            } else {
                row3.team1_player1_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 34) {
								row3.team1_player2_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(34);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(34).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.team1_player2_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row3.team1_player2_name = tmpContent_tDBInput_2;
                }
            } else {
                row3.team1_player2_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 35) {
								row3.team2_player1_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(35);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(35).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.team2_player1_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row3.team2_player1_name = tmpContent_tDBInput_2;
                }
            } else {
                row3.team2_player1_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 36) {
								row3.team2_player2_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(36);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(36).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.team2_player2_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row3.team2_player2_name = tmpContent_tDBInput_2;
                }
            } else {
                row3.team2_player2_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 37) {
								row3.match_info_added = null;
							} else {
		                          
            row3.match_info_added = rs_tDBInput_2.getInt(37);
            if(rs_tDBInput_2.wasNull()){
                    row3.match_info_added = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 38) {
								row3.t1_s1 = null;
							} else {
		                          
            row3.t1_s1 = rs_tDBInput_2.getInt(38);
            if(rs_tDBInput_2.wasNull()){
                    row3.t1_s1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 39) {
								row3.t2_s1 = null;
							} else {
		                          
            row3.t2_s1 = rs_tDBInput_2.getInt(39);
            if(rs_tDBInput_2.wasNull()){
                    row3.t2_s1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 40) {
								row3.t1_s2 = null;
							} else {
		                          
            row3.t1_s2 = rs_tDBInput_2.getInt(40);
            if(rs_tDBInput_2.wasNull()){
                    row3.t1_s2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 41) {
								row3.t2_s2 = null;
							} else {
		                          
            row3.t2_s2 = rs_tDBInput_2.getInt(41);
            if(rs_tDBInput_2.wasNull()){
                    row3.t2_s2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 42) {
								row3.t1_s3 = null;
							} else {
		                          
            row3.t1_s3 = rs_tDBInput_2.getInt(42);
            if(rs_tDBInput_2.wasNull()){
                    row3.t1_s3 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 43) {
								row3.t2_s3 = null;
							} else {
		                          
            row3.t2_s3 = rs_tDBInput_2.getInt(43);
            if(rs_tDBInput_2.wasNull()){
                    row3.t2_s3 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 44) {
								row3.views = null;
							} else {
		                          
            row3.views = rs_tDBInput_2.getInt(44);
            if(rs_tDBInput_2.wasNull()){
                    row3.views = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 45) {
								row3.interactions = null;
							} else {
		                          
            row3.interactions = rs_tDBInput_2.getInt(45);
            if(rs_tDBInput_2.wasNull()){
                    row3.interactions = null;
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
	 * [tAdvancedHash_row3 main ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row3";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row3"
						
						);
					}
					


			   
			   

					row3Struct row3_HashRow = new row3Struct();
		   	   	   
				
				row3_HashRow.match_id = row3.match_id;
				
				row3_HashRow.match_number = row3.match_number;
				
				row3_HashRow.tournament_name = row3.tournament_name;
				
				row3_HashRow.round = row3.round;
				
				row3_HashRow.winner = row3.winner;
				
				row3_HashRow.total_points_won_t1 = row3.total_points_won_t1;
				
				row3_HashRow.total_points_won_t2 = row3.total_points_won_t2;
				
				row3_HashRow.break_points_converted_t1 = row3.break_points_converted_t1;
				
				row3_HashRow.break_points_converted_t2 = row3.break_points_converted_t2;
				
				row3_HashRow.longest_streak_t1 = row3.longest_streak_t1;
				
				row3_HashRow.longest_streak_t2 = row3.longest_streak_t2;
				
				row3_HashRow.aces_t1 = row3.aces_t1;
				
				row3_HashRow.aces_t2 = row3.aces_t2;
				
				row3_HashRow.double_faults_t1 = row3.double_faults_t1;
				
				row3_HashRow.double_faults_t2 = row3.double_faults_t2;
				
				row3_HashRow.won_on_1st_serve_t1 = row3.won_on_1st_serve_t1;
				
				row3_HashRow.won_on_1st_serve_t2 = row3.won_on_1st_serve_t2;
				
				row3_HashRow.won_on_2nd_serve_t1 = row3.won_on_2nd_serve_t1;
				
				row3_HashRow.won_on_2nd_serve_t2 = row3.won_on_2nd_serve_t2;
				
				row3_HashRow.service_games_t1 = row3.service_games_t1;
				
				row3_HashRow.service_games_t2 = row3.service_games_t2;
				
				row3_HashRow.won_on_1st_return_t1 = row3.won_on_1st_return_t1;
				
				row3_HashRow.won_on_1st_return_t2 = row3.won_on_1st_return_t2;
				
				row3_HashRow.won_on_2nd_return_t1 = row3.won_on_2nd_return_t1;
				
				row3_HashRow.won_on_2nd_return_t2 = row3.won_on_2nd_return_t2;
				
				row3_HashRow.return_games_t1 = row3.return_games_t1;
				
				row3_HashRow.return_games_t2 = row3.return_games_t2;
				
				row3_HashRow.total_won_on_serve_t1 = row3.total_won_on_serve_t1;
				
				row3_HashRow.total_won_on_serve_t2 = row3.total_won_on_serve_t2;
				
				row3_HashRow.total_won_on_return_t1 = row3.total_won_on_return_t1;
				
				row3_HashRow.total_won_on_return_t2 = row3.total_won_on_return_t2;
				
				row3_HashRow.date = row3.date;
				
				row3_HashRow.team1_player1_name = row3.team1_player1_name;
				
				row3_HashRow.team1_player2_name = row3.team1_player2_name;
				
				row3_HashRow.team2_player1_name = row3.team2_player1_name;
				
				row3_HashRow.team2_player2_name = row3.team2_player2_name;
				
				row3_HashRow.match_info_added = row3.match_info_added;
				
				row3_HashRow.t1_s1 = row3.t1_s1;
				
				row3_HashRow.t2_s1 = row3.t2_s1;
				
				row3_HashRow.t1_s2 = row3.t1_s2;
				
				row3_HashRow.t2_s2 = row3.t2_s2;
				
				row3_HashRow.t1_s3 = row3.t1_s3;
				
				row3_HashRow.t2_s3 = row3.t2_s3;
				
				row3_HashRow.views = row3.views;
				
				row3_HashRow.interactions = row3.interactions;
				
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
	 * [tDBInput_2 finally ] start
	 */

	

	
	
	currentComponent="tDBInput_2";

	

 



/**
 * [tDBInput_2 finally ] stop
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
		

		globalMap.put("tDBInput_2_SUBPROCESS_STATE", 1);
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
        final dim_matchs dim_matchsClass = new dim_matchs();

        int exitCode = dim_matchsClass.runJobInTOS(args);

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
            java.io.InputStream inContext = dim_matchs.class.getClassLoader().getResourceAsStream("dw_padel/dim_matchs_0_1/contexts/" + contextStr + ".properties");
            if (inContext == null) {
                inContext = dim_matchs.class.getClassLoader().getResourceAsStream("config/contexts/" + contextStr + ".properties");
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
errorCode = null;tDBInput_1Process(globalMap);
if(!"failure".equals(status)) { status = "end"; }
}catch (TalendException e_tDBInput_1) {
globalMap.put("tDBInput_1_SUBPROCESS_STATE", -1);

e_tDBInput_1.printStackTrace();

}

this.globalResumeTicket = true;//to run tPostJob




        end = System.currentTimeMillis();

        if (watch) {
            System.out.println((end-startTime)+" milliseconds");
        }

        endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        if (false) {
            System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : dim_matchs");
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
 *     329050 characters generated by Talend Open Studio for Data Integration 
 *     on the 29 avril 2026 à 03:23:47 WAT
 ************************************************************************************************/