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


package dw_padel.fact_players_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.DateUtils;
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
 * Job: FACT_PLAYERS Purpose: <br>
 * Description:  <br>
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status 
 */
public class FACT_PLAYERS implements TalendJob {

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
	private final String jobName = "FACT_PLAYERS";
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
				FACT_PLAYERS.this.exception = e;
			}
		}
		if (!(e instanceof TalendException)) {
		try {
			for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
				if (m.getName().compareTo(currentComponent + "_error") == 0) {
					m.invoke(FACT_PLAYERS.this, new Object[] { e , currentComponent, globalMap});
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
			
			public void tMap_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBOutput_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_6_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_6_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tMap_3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_6_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tUniqRow_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_4_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_5_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_7_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tAdvancedHash_out3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_6_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tAdvancedHash_row8_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tAdvancedHash_row2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tAdvancedHash_row3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tAdvancedHash_row4_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tAdvancedHash_row5_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tAdvancedHash_row9_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tFileInputExcel_1_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
			public void tDBInput_6_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
			public void tDBInput_2_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
	






public static class out2Struct implements routines.system.IPersistableRow<out2Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_FACT_PLAYERS = new byte[0];
    static byte[] commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[0];

	
			    public Integer player_id;

				public Integer getPlayer_id () {
					return this.player_id;
				}
				
			    public Integer ranking;

				public Integer getRanking () {
					return this.ranking;
				}
				
			    public Integer height;

				public Integer getHeight () {
					return this.height;
				}
				
			    public Integer birthdate;

				public Integer getBirthdate () {
					return this.birthdate;
				}
				
			    public Integer _023_matches_played;

				public Integer get_023_matches_played () {
					return this._023_matches_played;
				}
				
			    public Integer _023_games_won;

				public Integer get_023_games_won () {
					return this._023_games_won;
				}
				
			    public Integer _023_games_lost;

				public Integer get_023_games_lost () {
					return this._023_games_lost;
				}
				
			    public Integer _024_matches_played;

				public Integer get_024_matches_played () {
					return this._024_matches_played;
				}
				
			    public Integer _024_matches_won;

				public Integer get_024_matches_won () {
					return this._024_matches_won;
				}
				
			    public Integer _024_games_won;

				public Integer get_024_games_won () {
					return this._024_games_won;
				}
				
			    public Integer _024_games_lost;

				public Integer get_024_games_lost () {
					return this._024_games_lost;
				}
				
			    public Integer _025_matches_played;

				public Integer get_025_matches_played () {
					return this._025_matches_played;
				}
				
			    public Integer _025_matches_won;

				public Integer get_025_matches_won () {
					return this._025_matches_won;
				}
				
			    public Integer _025_games_won;

				public Integer get_025_games_won () {
					return this._025_games_won;
				}
				
			    public Integer _025_games_lost;

				public Integer get_025_games_lost () {
					return this._025_games_lost;
				}
				
			    public Integer _026_matches_played;

				public Integer get_026_matches_played () {
					return this._026_matches_played;
				}
				
			    public Integer _026_matches_won;

				public Integer get_026_matches_won () {
					return this._026_matches_won;
				}
				
			    public Integer _026_games_won;

				public Integer get_026_games_won () {
					return this._026_games_won;
				}
				
			    public Integer _026_games_lost;

				public Integer get_026_games_lost () {
					return this._026_games_lost;
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

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

        	try {

        		int length = 0;
		
						this.player_id = readInteger(dis);
					
						this.ranking = readInteger(dis);
					
						this.height = readInteger(dis);
					
						this.birthdate = readInteger(dis);
					
						this._023_matches_played = readInteger(dis);
					
						this._023_games_won = readInteger(dis);
					
						this._023_games_lost = readInteger(dis);
					
						this._024_matches_played = readInteger(dis);
					
						this._024_matches_won = readInteger(dis);
					
						this._024_games_won = readInteger(dis);
					
						this._024_games_lost = readInteger(dis);
					
						this._025_matches_played = readInteger(dis);
					
						this._025_matches_won = readInteger(dis);
					
						this._025_games_won = readInteger(dis);
					
						this._025_games_lost = readInteger(dis);
					
						this._026_matches_played = readInteger(dis);
					
						this._026_matches_won = readInteger(dis);
					
						this._026_games_won = readInteger(dis);
					
						this._026_games_lost = readInteger(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

        	try {

        		int length = 0;
		
						this.player_id = readInteger(dis);
					
						this.ranking = readInteger(dis);
					
						this.height = readInteger(dis);
					
						this.birthdate = readInteger(dis);
					
						this._023_matches_played = readInteger(dis);
					
						this._023_games_won = readInteger(dis);
					
						this._023_games_lost = readInteger(dis);
					
						this._024_matches_played = readInteger(dis);
					
						this._024_matches_won = readInteger(dis);
					
						this._024_games_won = readInteger(dis);
					
						this._024_games_lost = readInteger(dis);
					
						this._025_matches_played = readInteger(dis);
					
						this._025_matches_won = readInteger(dis);
					
						this._025_games_won = readInteger(dis);
					
						this._025_games_lost = readInteger(dis);
					
						this._026_matches_played = readInteger(dis);
					
						this._026_matches_won = readInteger(dis);
					
						this._026_games_won = readInteger(dis);
					
						this._026_games_lost = readInteger(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// Integer
				
						writeInteger(this.player_id,dos);
					
					// Integer
				
						writeInteger(this.ranking,dos);
					
					// Integer
				
						writeInteger(this.height,dos);
					
					// Integer
				
						writeInteger(this.birthdate,dos);
					
					// Integer
				
						writeInteger(this._023_matches_played,dos);
					
					// Integer
				
						writeInteger(this._023_games_won,dos);
					
					// Integer
				
						writeInteger(this._023_games_lost,dos);
					
					// Integer
				
						writeInteger(this._024_matches_played,dos);
					
					// Integer
				
						writeInteger(this._024_matches_won,dos);
					
					// Integer
				
						writeInteger(this._024_games_won,dos);
					
					// Integer
				
						writeInteger(this._024_games_lost,dos);
					
					// Integer
				
						writeInteger(this._025_matches_played,dos);
					
					// Integer
				
						writeInteger(this._025_matches_won,dos);
					
					// Integer
				
						writeInteger(this._025_games_won,dos);
					
					// Integer
				
						writeInteger(this._025_games_lost,dos);
					
					// Integer
				
						writeInteger(this._026_matches_played,dos);
					
					// Integer
				
						writeInteger(this._026_matches_won,dos);
					
					// Integer
				
						writeInteger(this._026_games_won,dos);
					
					// Integer
				
						writeInteger(this._026_games_lost,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// Integer
				
						writeInteger(this.player_id,dos);
					
					// Integer
				
						writeInteger(this.ranking,dos);
					
					// Integer
				
						writeInteger(this.height,dos);
					
					// Integer
				
						writeInteger(this.birthdate,dos);
					
					// Integer
				
						writeInteger(this._023_matches_played,dos);
					
					// Integer
				
						writeInteger(this._023_games_won,dos);
					
					// Integer
				
						writeInteger(this._023_games_lost,dos);
					
					// Integer
				
						writeInteger(this._024_matches_played,dos);
					
					// Integer
				
						writeInteger(this._024_matches_won,dos);
					
					// Integer
				
						writeInteger(this._024_games_won,dos);
					
					// Integer
				
						writeInteger(this._024_games_lost,dos);
					
					// Integer
				
						writeInteger(this._025_matches_played,dos);
					
					// Integer
				
						writeInteger(this._025_matches_won,dos);
					
					// Integer
				
						writeInteger(this._025_games_won,dos);
					
					// Integer
				
						writeInteger(this._025_games_lost,dos);
					
					// Integer
				
						writeInteger(this._026_matches_played,dos);
					
					// Integer
				
						writeInteger(this._026_matches_won,dos);
					
					// Integer
				
						writeInteger(this._026_games_won,dos);
					
					// Integer
				
						writeInteger(this._026_games_lost,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("player_id="+String.valueOf(player_id));
		sb.append(",ranking="+String.valueOf(ranking));
		sb.append(",height="+String.valueOf(height));
		sb.append(",birthdate="+String.valueOf(birthdate));
		sb.append(",_023_matches_played="+String.valueOf(_023_matches_played));
		sb.append(",_023_games_won="+String.valueOf(_023_games_won));
		sb.append(",_023_games_lost="+String.valueOf(_023_games_lost));
		sb.append(",_024_matches_played="+String.valueOf(_024_matches_played));
		sb.append(",_024_matches_won="+String.valueOf(_024_matches_won));
		sb.append(",_024_games_won="+String.valueOf(_024_games_won));
		sb.append(",_024_games_lost="+String.valueOf(_024_games_lost));
		sb.append(",_025_matches_played="+String.valueOf(_025_matches_played));
		sb.append(",_025_matches_won="+String.valueOf(_025_matches_won));
		sb.append(",_025_games_won="+String.valueOf(_025_games_won));
		sb.append(",_025_games_lost="+String.valueOf(_025_games_lost));
		sb.append(",_026_matches_played="+String.valueOf(_026_matches_played));
		sb.append(",_026_matches_won="+String.valueOf(_026_matches_won));
		sb.append(",_026_games_won="+String.valueOf(_026_games_won));
		sb.append(",_026_games_lost="+String.valueOf(_026_games_lost));
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(out2Struct other) {

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

public static class row6Struct implements routines.system.IPersistableRow<row6Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_FACT_PLAYERS = new byte[0];
    static byte[] commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[0];

	
			    public String name;

				public String getName () {
					return this.name;
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
				
			    public Integer total_points;

				public Integer getTotal_points () {
					return this.total_points;
				}
				
			    public Integer height;

				public Integer getHeight () {
					return this.height;
				}
				
			    public String birthdate;

				public String getBirthdate () {
					return this.birthdate;
				}
				
			    public Integer age;

				public Integer getAge () {
					return this.age;
				}
				
			    public String hand;

				public String getHand () {
					return this.hand;
				}
				
			    public String side;

				public String getSide () {
					return this.side;
				}
				
			    public String partner;

				public String getPartner () {
					return this.partner;
				}
				
			    public Integer _023_matches_played;

				public Integer get_023_matches_played () {
					return this._023_matches_played;
				}
				
			    public Integer _023_matches_won;

				public Integer get_023_matches_won () {
					return this._023_matches_won;
				}
				
			    public String _023_win_pct;

				public String get_023_win_pct () {
					return this._023_win_pct;
				}
				
			    public Integer _023_sets_won;

				public Integer get_023_sets_won () {
					return this._023_sets_won;
				}
				
			    public Integer _023_sets_lost;

				public Integer get_023_sets_lost () {
					return this._023_sets_lost;
				}
				
			    public String _023_avg_sets_match;

				public String get_023_avg_sets_match () {
					return this._023_avg_sets_match;
				}
				
			    public Integer _023_games_won;

				public Integer get_023_games_won () {
					return this._023_games_won;
				}
				
			    public Integer _023_games_lost;

				public Integer get_023_games_lost () {
					return this._023_games_lost;
				}
				
			    public String _023_avg_games_match;

				public String get_023_avg_games_match () {
					return this._023_avg_games_match;
				}
				
			    public Integer _023_titles;

				public Integer get_023_titles () {
					return this._023_titles;
				}
				
			    public Integer _023_finals;

				public Integer get_023_finals () {
					return this._023_finals;
				}
				
			    public Integer _023_semifinals;

				public Integer get_023_semifinals () {
					return this._023_semifinals;
				}
				
			    public Integer _023_best_round;

				public Integer get_023_best_round () {
					return this._023_best_round;
				}
				
			    public Integer _024_matches_played;

				public Integer get_024_matches_played () {
					return this._024_matches_played;
				}
				
			    public Integer _024_matches_won;

				public Integer get_024_matches_won () {
					return this._024_matches_won;
				}
				
			    public String _024_win_pct;

				public String get_024_win_pct () {
					return this._024_win_pct;
				}
				
			    public Integer _024_sets_won;

				public Integer get_024_sets_won () {
					return this._024_sets_won;
				}
				
			    public Integer _024_sets_lost;

				public Integer get_024_sets_lost () {
					return this._024_sets_lost;
				}
				
			    public String _024_avg_sets_match;

				public String get_024_avg_sets_match () {
					return this._024_avg_sets_match;
				}
				
			    public Integer _024_games_won;

				public Integer get_024_games_won () {
					return this._024_games_won;
				}
				
			    public Integer _024_games_lost;

				public Integer get_024_games_lost () {
					return this._024_games_lost;
				}
				
			    public String _024_avg_games_match;

				public String get_024_avg_games_match () {
					return this._024_avg_games_match;
				}
				
			    public Integer _024_titles;

				public Integer get_024_titles () {
					return this._024_titles;
				}
				
			    public Integer _024_finals;

				public Integer get_024_finals () {
					return this._024_finals;
				}
				
			    public Integer _024_semifinals;

				public Integer get_024_semifinals () {
					return this._024_semifinals;
				}
				
			    public Integer _024_best_round;

				public Integer get_024_best_round () {
					return this._024_best_round;
				}
				
			    public Integer _025_matches_played;

				public Integer get_025_matches_played () {
					return this._025_matches_played;
				}
				
			    public Integer _025_matches_won;

				public Integer get_025_matches_won () {
					return this._025_matches_won;
				}
				
			    public String _025_win_pct;

				public String get_025_win_pct () {
					return this._025_win_pct;
				}
				
			    public Integer _025_sets_won;

				public Integer get_025_sets_won () {
					return this._025_sets_won;
				}
				
			    public Integer _025_sets_lost;

				public Integer get_025_sets_lost () {
					return this._025_sets_lost;
				}
				
			    public String _025_avg_sets_match;

				public String get_025_avg_sets_match () {
					return this._025_avg_sets_match;
				}
				
			    public Integer _025_games_won;

				public Integer get_025_games_won () {
					return this._025_games_won;
				}
				
			    public Integer _025_games_lost;

				public Integer get_025_games_lost () {
					return this._025_games_lost;
				}
				
			    public String _025_avg_games_match;

				public String get_025_avg_games_match () {
					return this._025_avg_games_match;
				}
				
			    public Integer _025_titles;

				public Integer get_025_titles () {
					return this._025_titles;
				}
				
			    public Integer _025_finals;

				public Integer get_025_finals () {
					return this._025_finals;
				}
				
			    public Integer _025_semifinals;

				public Integer get_025_semifinals () {
					return this._025_semifinals;
				}
				
			    public Integer _025_best_round;

				public Integer get_025_best_round () {
					return this._025_best_round;
				}
				
			    public Integer _026_matches_played;

				public Integer get_026_matches_played () {
					return this._026_matches_played;
				}
				
			    public Integer _026_matches_won;

				public Integer get_026_matches_won () {
					return this._026_matches_won;
				}
				
			    public String _026_win_pct;

				public String get_026_win_pct () {
					return this._026_win_pct;
				}
				
			    public Integer _026_sets_won;

				public Integer get_026_sets_won () {
					return this._026_sets_won;
				}
				
			    public Integer _026_sets_lost;

				public Integer get_026_sets_lost () {
					return this._026_sets_lost;
				}
				
			    public String _026_avg_sets_match;

				public String get_026_avg_sets_match () {
					return this._026_avg_sets_match;
				}
				
			    public Integer _026_games_won;

				public Integer get_026_games_won () {
					return this._026_games_won;
				}
				
			    public Integer _026_games_lost;

				public Integer get_026_games_lost () {
					return this._026_games_lost;
				}
				
			    public String _026_avg_games_match;

				public String get_026_avg_games_match () {
					return this._026_avg_games_match;
				}
				
			    public Integer _026_titles;

				public Integer get_026_titles () {
					return this._026_titles;
				}
				
			    public Integer _026_finals;

				public Integer get_026_finals () {
					return this._026_finals;
				}
				
			    public Integer _026_semifinals;

				public Integer get_026_semifinals () {
					return this._026_semifinals;
				}
				
			    public Integer _026_best_round;

				public Integer get_026_best_round () {
					return this._026_best_round;
				}
				
			    public Integer followers;

				public Integer getFollowers () {
					return this.followers;
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
			if(length > commonByteArray_DW_PADEL_FACT_PLAYERS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_PLAYERS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_FACT_PLAYERS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_PLAYERS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

        	try {

        		int length = 0;
		
					this.name = readString(dis);
					
						this.ranking = readInteger(dis);
					
					this.gender = readString(dis);
					
					this.nationality = readString(dis);
					
						this.total_points = readInteger(dis);
					
						this.height = readInteger(dis);
					
					this.birthdate = readString(dis);
					
						this.age = readInteger(dis);
					
					this.hand = readString(dis);
					
					this.side = readString(dis);
					
					this.partner = readString(dis);
					
						this._023_matches_played = readInteger(dis);
					
						this._023_matches_won = readInteger(dis);
					
					this._023_win_pct = readString(dis);
					
						this._023_sets_won = readInteger(dis);
					
						this._023_sets_lost = readInteger(dis);
					
					this._023_avg_sets_match = readString(dis);
					
						this._023_games_won = readInteger(dis);
					
						this._023_games_lost = readInteger(dis);
					
					this._023_avg_games_match = readString(dis);
					
						this._023_titles = readInteger(dis);
					
						this._023_finals = readInteger(dis);
					
						this._023_semifinals = readInteger(dis);
					
						this._023_best_round = readInteger(dis);
					
						this._024_matches_played = readInteger(dis);
					
						this._024_matches_won = readInteger(dis);
					
					this._024_win_pct = readString(dis);
					
						this._024_sets_won = readInteger(dis);
					
						this._024_sets_lost = readInteger(dis);
					
					this._024_avg_sets_match = readString(dis);
					
						this._024_games_won = readInteger(dis);
					
						this._024_games_lost = readInteger(dis);
					
					this._024_avg_games_match = readString(dis);
					
						this._024_titles = readInteger(dis);
					
						this._024_finals = readInteger(dis);
					
						this._024_semifinals = readInteger(dis);
					
						this._024_best_round = readInteger(dis);
					
						this._025_matches_played = readInteger(dis);
					
						this._025_matches_won = readInteger(dis);
					
					this._025_win_pct = readString(dis);
					
						this._025_sets_won = readInteger(dis);
					
						this._025_sets_lost = readInteger(dis);
					
					this._025_avg_sets_match = readString(dis);
					
						this._025_games_won = readInteger(dis);
					
						this._025_games_lost = readInteger(dis);
					
					this._025_avg_games_match = readString(dis);
					
						this._025_titles = readInteger(dis);
					
						this._025_finals = readInteger(dis);
					
						this._025_semifinals = readInteger(dis);
					
						this._025_best_round = readInteger(dis);
					
						this._026_matches_played = readInteger(dis);
					
						this._026_matches_won = readInteger(dis);
					
					this._026_win_pct = readString(dis);
					
						this._026_sets_won = readInteger(dis);
					
						this._026_sets_lost = readInteger(dis);
					
					this._026_avg_sets_match = readString(dis);
					
						this._026_games_won = readInteger(dis);
					
						this._026_games_lost = readInteger(dis);
					
					this._026_avg_games_match = readString(dis);
					
						this._026_titles = readInteger(dis);
					
						this._026_finals = readInteger(dis);
					
						this._026_semifinals = readInteger(dis);
					
						this._026_best_round = readInteger(dis);
					
						this.followers = readInteger(dis);
					
						this.interactions = readInteger(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

        	try {

        		int length = 0;
		
					this.name = readString(dis);
					
						this.ranking = readInteger(dis);
					
					this.gender = readString(dis);
					
					this.nationality = readString(dis);
					
						this.total_points = readInteger(dis);
					
						this.height = readInteger(dis);
					
					this.birthdate = readString(dis);
					
						this.age = readInteger(dis);
					
					this.hand = readString(dis);
					
					this.side = readString(dis);
					
					this.partner = readString(dis);
					
						this._023_matches_played = readInteger(dis);
					
						this._023_matches_won = readInteger(dis);
					
					this._023_win_pct = readString(dis);
					
						this._023_sets_won = readInteger(dis);
					
						this._023_sets_lost = readInteger(dis);
					
					this._023_avg_sets_match = readString(dis);
					
						this._023_games_won = readInteger(dis);
					
						this._023_games_lost = readInteger(dis);
					
					this._023_avg_games_match = readString(dis);
					
						this._023_titles = readInteger(dis);
					
						this._023_finals = readInteger(dis);
					
						this._023_semifinals = readInteger(dis);
					
						this._023_best_round = readInteger(dis);
					
						this._024_matches_played = readInteger(dis);
					
						this._024_matches_won = readInteger(dis);
					
					this._024_win_pct = readString(dis);
					
						this._024_sets_won = readInteger(dis);
					
						this._024_sets_lost = readInteger(dis);
					
					this._024_avg_sets_match = readString(dis);
					
						this._024_games_won = readInteger(dis);
					
						this._024_games_lost = readInteger(dis);
					
					this._024_avg_games_match = readString(dis);
					
						this._024_titles = readInteger(dis);
					
						this._024_finals = readInteger(dis);
					
						this._024_semifinals = readInteger(dis);
					
						this._024_best_round = readInteger(dis);
					
						this._025_matches_played = readInteger(dis);
					
						this._025_matches_won = readInteger(dis);
					
					this._025_win_pct = readString(dis);
					
						this._025_sets_won = readInteger(dis);
					
						this._025_sets_lost = readInteger(dis);
					
					this._025_avg_sets_match = readString(dis);
					
						this._025_games_won = readInteger(dis);
					
						this._025_games_lost = readInteger(dis);
					
					this._025_avg_games_match = readString(dis);
					
						this._025_titles = readInteger(dis);
					
						this._025_finals = readInteger(dis);
					
						this._025_semifinals = readInteger(dis);
					
						this._025_best_round = readInteger(dis);
					
						this._026_matches_played = readInteger(dis);
					
						this._026_matches_won = readInteger(dis);
					
					this._026_win_pct = readString(dis);
					
						this._026_sets_won = readInteger(dis);
					
						this._026_sets_lost = readInteger(dis);
					
					this._026_avg_sets_match = readString(dis);
					
						this._026_games_won = readInteger(dis);
					
						this._026_games_lost = readInteger(dis);
					
					this._026_avg_games_match = readString(dis);
					
						this._026_titles = readInteger(dis);
					
						this._026_finals = readInteger(dis);
					
						this._026_semifinals = readInteger(dis);
					
						this._026_best_round = readInteger(dis);
					
						this.followers = readInteger(dis);
					
						this.interactions = readInteger(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.name,dos);
					
					// Integer
				
						writeInteger(this.ranking,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.nationality,dos);
					
					// Integer
				
						writeInteger(this.total_points,dos);
					
					// Integer
				
						writeInteger(this.height,dos);
					
					// String
				
						writeString(this.birthdate,dos);
					
					// Integer
				
						writeInteger(this.age,dos);
					
					// String
				
						writeString(this.hand,dos);
					
					// String
				
						writeString(this.side,dos);
					
					// String
				
						writeString(this.partner,dos);
					
					// Integer
				
						writeInteger(this._023_matches_played,dos);
					
					// Integer
				
						writeInteger(this._023_matches_won,dos);
					
					// String
				
						writeString(this._023_win_pct,dos);
					
					// Integer
				
						writeInteger(this._023_sets_won,dos);
					
					// Integer
				
						writeInteger(this._023_sets_lost,dos);
					
					// String
				
						writeString(this._023_avg_sets_match,dos);
					
					// Integer
				
						writeInteger(this._023_games_won,dos);
					
					// Integer
				
						writeInteger(this._023_games_lost,dos);
					
					// String
				
						writeString(this._023_avg_games_match,dos);
					
					// Integer
				
						writeInteger(this._023_titles,dos);
					
					// Integer
				
						writeInteger(this._023_finals,dos);
					
					// Integer
				
						writeInteger(this._023_semifinals,dos);
					
					// Integer
				
						writeInteger(this._023_best_round,dos);
					
					// Integer
				
						writeInteger(this._024_matches_played,dos);
					
					// Integer
				
						writeInteger(this._024_matches_won,dos);
					
					// String
				
						writeString(this._024_win_pct,dos);
					
					// Integer
				
						writeInteger(this._024_sets_won,dos);
					
					// Integer
				
						writeInteger(this._024_sets_lost,dos);
					
					// String
				
						writeString(this._024_avg_sets_match,dos);
					
					// Integer
				
						writeInteger(this._024_games_won,dos);
					
					// Integer
				
						writeInteger(this._024_games_lost,dos);
					
					// String
				
						writeString(this._024_avg_games_match,dos);
					
					// Integer
				
						writeInteger(this._024_titles,dos);
					
					// Integer
				
						writeInteger(this._024_finals,dos);
					
					// Integer
				
						writeInteger(this._024_semifinals,dos);
					
					// Integer
				
						writeInteger(this._024_best_round,dos);
					
					// Integer
				
						writeInteger(this._025_matches_played,dos);
					
					// Integer
				
						writeInteger(this._025_matches_won,dos);
					
					// String
				
						writeString(this._025_win_pct,dos);
					
					// Integer
				
						writeInteger(this._025_sets_won,dos);
					
					// Integer
				
						writeInteger(this._025_sets_lost,dos);
					
					// String
				
						writeString(this._025_avg_sets_match,dos);
					
					// Integer
				
						writeInteger(this._025_games_won,dos);
					
					// Integer
				
						writeInteger(this._025_games_lost,dos);
					
					// String
				
						writeString(this._025_avg_games_match,dos);
					
					// Integer
				
						writeInteger(this._025_titles,dos);
					
					// Integer
				
						writeInteger(this._025_finals,dos);
					
					// Integer
				
						writeInteger(this._025_semifinals,dos);
					
					// Integer
				
						writeInteger(this._025_best_round,dos);
					
					// Integer
				
						writeInteger(this._026_matches_played,dos);
					
					// Integer
				
						writeInteger(this._026_matches_won,dos);
					
					// String
				
						writeString(this._026_win_pct,dos);
					
					// Integer
				
						writeInteger(this._026_sets_won,dos);
					
					// Integer
				
						writeInteger(this._026_sets_lost,dos);
					
					// String
				
						writeString(this._026_avg_sets_match,dos);
					
					// Integer
				
						writeInteger(this._026_games_won,dos);
					
					// Integer
				
						writeInteger(this._026_games_lost,dos);
					
					// String
				
						writeString(this._026_avg_games_match,dos);
					
					// Integer
				
						writeInteger(this._026_titles,dos);
					
					// Integer
				
						writeInteger(this._026_finals,dos);
					
					// Integer
				
						writeInteger(this._026_semifinals,dos);
					
					// Integer
				
						writeInteger(this._026_best_round,dos);
					
					// Integer
				
						writeInteger(this.followers,dos);
					
					// Integer
				
						writeInteger(this.interactions,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.name,dos);
					
					// Integer
				
						writeInteger(this.ranking,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.nationality,dos);
					
					// Integer
				
						writeInteger(this.total_points,dos);
					
					// Integer
				
						writeInteger(this.height,dos);
					
					// String
				
						writeString(this.birthdate,dos);
					
					// Integer
				
						writeInteger(this.age,dos);
					
					// String
				
						writeString(this.hand,dos);
					
					// String
				
						writeString(this.side,dos);
					
					// String
				
						writeString(this.partner,dos);
					
					// Integer
				
						writeInteger(this._023_matches_played,dos);
					
					// Integer
				
						writeInteger(this._023_matches_won,dos);
					
					// String
				
						writeString(this._023_win_pct,dos);
					
					// Integer
				
						writeInteger(this._023_sets_won,dos);
					
					// Integer
				
						writeInteger(this._023_sets_lost,dos);
					
					// String
				
						writeString(this._023_avg_sets_match,dos);
					
					// Integer
				
						writeInteger(this._023_games_won,dos);
					
					// Integer
				
						writeInteger(this._023_games_lost,dos);
					
					// String
				
						writeString(this._023_avg_games_match,dos);
					
					// Integer
				
						writeInteger(this._023_titles,dos);
					
					// Integer
				
						writeInteger(this._023_finals,dos);
					
					// Integer
				
						writeInteger(this._023_semifinals,dos);
					
					// Integer
				
						writeInteger(this._023_best_round,dos);
					
					// Integer
				
						writeInteger(this._024_matches_played,dos);
					
					// Integer
				
						writeInteger(this._024_matches_won,dos);
					
					// String
				
						writeString(this._024_win_pct,dos);
					
					// Integer
				
						writeInteger(this._024_sets_won,dos);
					
					// Integer
				
						writeInteger(this._024_sets_lost,dos);
					
					// String
				
						writeString(this._024_avg_sets_match,dos);
					
					// Integer
				
						writeInteger(this._024_games_won,dos);
					
					// Integer
				
						writeInteger(this._024_games_lost,dos);
					
					// String
				
						writeString(this._024_avg_games_match,dos);
					
					// Integer
				
						writeInteger(this._024_titles,dos);
					
					// Integer
				
						writeInteger(this._024_finals,dos);
					
					// Integer
				
						writeInteger(this._024_semifinals,dos);
					
					// Integer
				
						writeInteger(this._024_best_round,dos);
					
					// Integer
				
						writeInteger(this._025_matches_played,dos);
					
					// Integer
				
						writeInteger(this._025_matches_won,dos);
					
					// String
				
						writeString(this._025_win_pct,dos);
					
					// Integer
				
						writeInteger(this._025_sets_won,dos);
					
					// Integer
				
						writeInteger(this._025_sets_lost,dos);
					
					// String
				
						writeString(this._025_avg_sets_match,dos);
					
					// Integer
				
						writeInteger(this._025_games_won,dos);
					
					// Integer
				
						writeInteger(this._025_games_lost,dos);
					
					// String
				
						writeString(this._025_avg_games_match,dos);
					
					// Integer
				
						writeInteger(this._025_titles,dos);
					
					// Integer
				
						writeInteger(this._025_finals,dos);
					
					// Integer
				
						writeInteger(this._025_semifinals,dos);
					
					// Integer
				
						writeInteger(this._025_best_round,dos);
					
					// Integer
				
						writeInteger(this._026_matches_played,dos);
					
					// Integer
				
						writeInteger(this._026_matches_won,dos);
					
					// String
				
						writeString(this._026_win_pct,dos);
					
					// Integer
				
						writeInteger(this._026_sets_won,dos);
					
					// Integer
				
						writeInteger(this._026_sets_lost,dos);
					
					// String
				
						writeString(this._026_avg_sets_match,dos);
					
					// Integer
				
						writeInteger(this._026_games_won,dos);
					
					// Integer
				
						writeInteger(this._026_games_lost,dos);
					
					// String
				
						writeString(this._026_avg_games_match,dos);
					
					// Integer
				
						writeInteger(this._026_titles,dos);
					
					// Integer
				
						writeInteger(this._026_finals,dos);
					
					// Integer
				
						writeInteger(this._026_semifinals,dos);
					
					// Integer
				
						writeInteger(this._026_best_round,dos);
					
					// Integer
				
						writeInteger(this.followers,dos);
					
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
		sb.append("name="+name);
		sb.append(",ranking="+String.valueOf(ranking));
		sb.append(",gender="+gender);
		sb.append(",nationality="+nationality);
		sb.append(",total_points="+String.valueOf(total_points));
		sb.append(",height="+String.valueOf(height));
		sb.append(",birthdate="+birthdate);
		sb.append(",age="+String.valueOf(age));
		sb.append(",hand="+hand);
		sb.append(",side="+side);
		sb.append(",partner="+partner);
		sb.append(",_023_matches_played="+String.valueOf(_023_matches_played));
		sb.append(",_023_matches_won="+String.valueOf(_023_matches_won));
		sb.append(",_023_win_pct="+_023_win_pct);
		sb.append(",_023_sets_won="+String.valueOf(_023_sets_won));
		sb.append(",_023_sets_lost="+String.valueOf(_023_sets_lost));
		sb.append(",_023_avg_sets_match="+_023_avg_sets_match);
		sb.append(",_023_games_won="+String.valueOf(_023_games_won));
		sb.append(",_023_games_lost="+String.valueOf(_023_games_lost));
		sb.append(",_023_avg_games_match="+_023_avg_games_match);
		sb.append(",_023_titles="+String.valueOf(_023_titles));
		sb.append(",_023_finals="+String.valueOf(_023_finals));
		sb.append(",_023_semifinals="+String.valueOf(_023_semifinals));
		sb.append(",_023_best_round="+String.valueOf(_023_best_round));
		sb.append(",_024_matches_played="+String.valueOf(_024_matches_played));
		sb.append(",_024_matches_won="+String.valueOf(_024_matches_won));
		sb.append(",_024_win_pct="+_024_win_pct);
		sb.append(",_024_sets_won="+String.valueOf(_024_sets_won));
		sb.append(",_024_sets_lost="+String.valueOf(_024_sets_lost));
		sb.append(",_024_avg_sets_match="+_024_avg_sets_match);
		sb.append(",_024_games_won="+String.valueOf(_024_games_won));
		sb.append(",_024_games_lost="+String.valueOf(_024_games_lost));
		sb.append(",_024_avg_games_match="+_024_avg_games_match);
		sb.append(",_024_titles="+String.valueOf(_024_titles));
		sb.append(",_024_finals="+String.valueOf(_024_finals));
		sb.append(",_024_semifinals="+String.valueOf(_024_semifinals));
		sb.append(",_024_best_round="+String.valueOf(_024_best_round));
		sb.append(",_025_matches_played="+String.valueOf(_025_matches_played));
		sb.append(",_025_matches_won="+String.valueOf(_025_matches_won));
		sb.append(",_025_win_pct="+_025_win_pct);
		sb.append(",_025_sets_won="+String.valueOf(_025_sets_won));
		sb.append(",_025_sets_lost="+String.valueOf(_025_sets_lost));
		sb.append(",_025_avg_sets_match="+_025_avg_sets_match);
		sb.append(",_025_games_won="+String.valueOf(_025_games_won));
		sb.append(",_025_games_lost="+String.valueOf(_025_games_lost));
		sb.append(",_025_avg_games_match="+_025_avg_games_match);
		sb.append(",_025_titles="+String.valueOf(_025_titles));
		sb.append(",_025_finals="+String.valueOf(_025_finals));
		sb.append(",_025_semifinals="+String.valueOf(_025_semifinals));
		sb.append(",_025_best_round="+String.valueOf(_025_best_round));
		sb.append(",_026_matches_played="+String.valueOf(_026_matches_played));
		sb.append(",_026_matches_won="+String.valueOf(_026_matches_won));
		sb.append(",_026_win_pct="+_026_win_pct);
		sb.append(",_026_sets_won="+String.valueOf(_026_sets_won));
		sb.append(",_026_sets_lost="+String.valueOf(_026_sets_lost));
		sb.append(",_026_avg_sets_match="+_026_avg_sets_match);
		sb.append(",_026_games_won="+String.valueOf(_026_games_won));
		sb.append(",_026_games_lost="+String.valueOf(_026_games_lost));
		sb.append(",_026_avg_games_match="+_026_avg_games_match);
		sb.append(",_026_titles="+String.valueOf(_026_titles));
		sb.append(",_026_finals="+String.valueOf(_026_finals));
		sb.append(",_026_semifinals="+String.valueOf(_026_semifinals));
		sb.append(",_026_best_round="+String.valueOf(_026_best_round));
		sb.append(",followers="+String.valueOf(followers));
		sb.append(",interactions="+String.valueOf(interactions));
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(row6Struct other) {

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
    final static byte[] commonByteArrayLock_DW_PADEL_FACT_PLAYERS = new byte[0];
    static byte[] commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[0];

	
			    public String name;

				public String getName () {
					return this.name;
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
				
			    public Integer total_points;

				public Integer getTotal_points () {
					return this.total_points;
				}
				
			    public Integer height;

				public Integer getHeight () {
					return this.height;
				}
				
			    public String birthdate;

				public String getBirthdate () {
					return this.birthdate;
				}
				
			    public Integer age;

				public Integer getAge () {
					return this.age;
				}
				
			    public String hand;

				public String getHand () {
					return this.hand;
				}
				
			    public String side;

				public String getSide () {
					return this.side;
				}
				
			    public String partner;

				public String getPartner () {
					return this.partner;
				}
				
			    public Integer _023_matches_played;

				public Integer get_023_matches_played () {
					return this._023_matches_played;
				}
				
			    public Integer _023_matches_won;

				public Integer get_023_matches_won () {
					return this._023_matches_won;
				}
				
			    public String _023_win_pct;

				public String get_023_win_pct () {
					return this._023_win_pct;
				}
				
			    public Integer _023_sets_won;

				public Integer get_023_sets_won () {
					return this._023_sets_won;
				}
				
			    public Integer _023_sets_lost;

				public Integer get_023_sets_lost () {
					return this._023_sets_lost;
				}
				
			    public String _023_avg_sets_match;

				public String get_023_avg_sets_match () {
					return this._023_avg_sets_match;
				}
				
			    public Integer _023_games_won;

				public Integer get_023_games_won () {
					return this._023_games_won;
				}
				
			    public Integer _023_games_lost;

				public Integer get_023_games_lost () {
					return this._023_games_lost;
				}
				
			    public String _023_avg_games_match;

				public String get_023_avg_games_match () {
					return this._023_avg_games_match;
				}
				
			    public Integer _023_titles;

				public Integer get_023_titles () {
					return this._023_titles;
				}
				
			    public Integer _023_finals;

				public Integer get_023_finals () {
					return this._023_finals;
				}
				
			    public Integer _023_semifinals;

				public Integer get_023_semifinals () {
					return this._023_semifinals;
				}
				
			    public Integer _023_best_round;

				public Integer get_023_best_round () {
					return this._023_best_round;
				}
				
			    public Integer _024_matches_played;

				public Integer get_024_matches_played () {
					return this._024_matches_played;
				}
				
			    public Integer _024_matches_won;

				public Integer get_024_matches_won () {
					return this._024_matches_won;
				}
				
			    public String _024_win_pct;

				public String get_024_win_pct () {
					return this._024_win_pct;
				}
				
			    public Integer _024_sets_won;

				public Integer get_024_sets_won () {
					return this._024_sets_won;
				}
				
			    public Integer _024_sets_lost;

				public Integer get_024_sets_lost () {
					return this._024_sets_lost;
				}
				
			    public String _024_avg_sets_match;

				public String get_024_avg_sets_match () {
					return this._024_avg_sets_match;
				}
				
			    public Integer _024_games_won;

				public Integer get_024_games_won () {
					return this._024_games_won;
				}
				
			    public Integer _024_games_lost;

				public Integer get_024_games_lost () {
					return this._024_games_lost;
				}
				
			    public String _024_avg_games_match;

				public String get_024_avg_games_match () {
					return this._024_avg_games_match;
				}
				
			    public Integer _024_titles;

				public Integer get_024_titles () {
					return this._024_titles;
				}
				
			    public Integer _024_finals;

				public Integer get_024_finals () {
					return this._024_finals;
				}
				
			    public Integer _024_semifinals;

				public Integer get_024_semifinals () {
					return this._024_semifinals;
				}
				
			    public Integer _024_best_round;

				public Integer get_024_best_round () {
					return this._024_best_round;
				}
				
			    public Integer _025_matches_played;

				public Integer get_025_matches_played () {
					return this._025_matches_played;
				}
				
			    public Integer _025_matches_won;

				public Integer get_025_matches_won () {
					return this._025_matches_won;
				}
				
			    public String _025_win_pct;

				public String get_025_win_pct () {
					return this._025_win_pct;
				}
				
			    public Integer _025_sets_won;

				public Integer get_025_sets_won () {
					return this._025_sets_won;
				}
				
			    public Integer _025_sets_lost;

				public Integer get_025_sets_lost () {
					return this._025_sets_lost;
				}
				
			    public String _025_avg_sets_match;

				public String get_025_avg_sets_match () {
					return this._025_avg_sets_match;
				}
				
			    public Integer _025_games_won;

				public Integer get_025_games_won () {
					return this._025_games_won;
				}
				
			    public Integer _025_games_lost;

				public Integer get_025_games_lost () {
					return this._025_games_lost;
				}
				
			    public String _025_avg_games_match;

				public String get_025_avg_games_match () {
					return this._025_avg_games_match;
				}
				
			    public Integer _025_titles;

				public Integer get_025_titles () {
					return this._025_titles;
				}
				
			    public Integer _025_finals;

				public Integer get_025_finals () {
					return this._025_finals;
				}
				
			    public Integer _025_semifinals;

				public Integer get_025_semifinals () {
					return this._025_semifinals;
				}
				
			    public Integer _025_best_round;

				public Integer get_025_best_round () {
					return this._025_best_round;
				}
				
			    public Integer _026_matches_played;

				public Integer get_026_matches_played () {
					return this._026_matches_played;
				}
				
			    public Integer _026_matches_won;

				public Integer get_026_matches_won () {
					return this._026_matches_won;
				}
				
			    public String _026_win_pct;

				public String get_026_win_pct () {
					return this._026_win_pct;
				}
				
			    public Integer _026_sets_won;

				public Integer get_026_sets_won () {
					return this._026_sets_won;
				}
				
			    public Integer _026_sets_lost;

				public Integer get_026_sets_lost () {
					return this._026_sets_lost;
				}
				
			    public String _026_avg_sets_match;

				public String get_026_avg_sets_match () {
					return this._026_avg_sets_match;
				}
				
			    public Integer _026_games_won;

				public Integer get_026_games_won () {
					return this._026_games_won;
				}
				
			    public Integer _026_games_lost;

				public Integer get_026_games_lost () {
					return this._026_games_lost;
				}
				
			    public String _026_avg_games_match;

				public String get_026_avg_games_match () {
					return this._026_avg_games_match;
				}
				
			    public Integer _026_titles;

				public Integer get_026_titles () {
					return this._026_titles;
				}
				
			    public Integer _026_finals;

				public Integer get_026_finals () {
					return this._026_finals;
				}
				
			    public Integer _026_semifinals;

				public Integer get_026_semifinals () {
					return this._026_semifinals;
				}
				
			    public Integer _026_best_round;

				public Integer get_026_best_round () {
					return this._026_best_round;
				}
				
			    public Integer followers;

				public Integer getFollowers () {
					return this.followers;
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
			if(length > commonByteArray_DW_PADEL_FACT_PLAYERS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_PLAYERS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_FACT_PLAYERS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_PLAYERS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

        	try {

        		int length = 0;
		
					this.name = readString(dis);
					
						this.ranking = readInteger(dis);
					
					this.gender = readString(dis);
					
					this.nationality = readString(dis);
					
						this.total_points = readInteger(dis);
					
						this.height = readInteger(dis);
					
					this.birthdate = readString(dis);
					
						this.age = readInteger(dis);
					
					this.hand = readString(dis);
					
					this.side = readString(dis);
					
					this.partner = readString(dis);
					
						this._023_matches_played = readInteger(dis);
					
						this._023_matches_won = readInteger(dis);
					
					this._023_win_pct = readString(dis);
					
						this._023_sets_won = readInteger(dis);
					
						this._023_sets_lost = readInteger(dis);
					
					this._023_avg_sets_match = readString(dis);
					
						this._023_games_won = readInteger(dis);
					
						this._023_games_lost = readInteger(dis);
					
					this._023_avg_games_match = readString(dis);
					
						this._023_titles = readInteger(dis);
					
						this._023_finals = readInteger(dis);
					
						this._023_semifinals = readInteger(dis);
					
						this._023_best_round = readInteger(dis);
					
						this._024_matches_played = readInteger(dis);
					
						this._024_matches_won = readInteger(dis);
					
					this._024_win_pct = readString(dis);
					
						this._024_sets_won = readInteger(dis);
					
						this._024_sets_lost = readInteger(dis);
					
					this._024_avg_sets_match = readString(dis);
					
						this._024_games_won = readInteger(dis);
					
						this._024_games_lost = readInteger(dis);
					
					this._024_avg_games_match = readString(dis);
					
						this._024_titles = readInteger(dis);
					
						this._024_finals = readInteger(dis);
					
						this._024_semifinals = readInteger(dis);
					
						this._024_best_round = readInteger(dis);
					
						this._025_matches_played = readInteger(dis);
					
						this._025_matches_won = readInteger(dis);
					
					this._025_win_pct = readString(dis);
					
						this._025_sets_won = readInteger(dis);
					
						this._025_sets_lost = readInteger(dis);
					
					this._025_avg_sets_match = readString(dis);
					
						this._025_games_won = readInteger(dis);
					
						this._025_games_lost = readInteger(dis);
					
					this._025_avg_games_match = readString(dis);
					
						this._025_titles = readInteger(dis);
					
						this._025_finals = readInteger(dis);
					
						this._025_semifinals = readInteger(dis);
					
						this._025_best_round = readInteger(dis);
					
						this._026_matches_played = readInteger(dis);
					
						this._026_matches_won = readInteger(dis);
					
					this._026_win_pct = readString(dis);
					
						this._026_sets_won = readInteger(dis);
					
						this._026_sets_lost = readInteger(dis);
					
					this._026_avg_sets_match = readString(dis);
					
						this._026_games_won = readInteger(dis);
					
						this._026_games_lost = readInteger(dis);
					
					this._026_avg_games_match = readString(dis);
					
						this._026_titles = readInteger(dis);
					
						this._026_finals = readInteger(dis);
					
						this._026_semifinals = readInteger(dis);
					
						this._026_best_round = readInteger(dis);
					
						this.followers = readInteger(dis);
					
						this.interactions = readInteger(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

        	try {

        		int length = 0;
		
					this.name = readString(dis);
					
						this.ranking = readInteger(dis);
					
					this.gender = readString(dis);
					
					this.nationality = readString(dis);
					
						this.total_points = readInteger(dis);
					
						this.height = readInteger(dis);
					
					this.birthdate = readString(dis);
					
						this.age = readInteger(dis);
					
					this.hand = readString(dis);
					
					this.side = readString(dis);
					
					this.partner = readString(dis);
					
						this._023_matches_played = readInteger(dis);
					
						this._023_matches_won = readInteger(dis);
					
					this._023_win_pct = readString(dis);
					
						this._023_sets_won = readInteger(dis);
					
						this._023_sets_lost = readInteger(dis);
					
					this._023_avg_sets_match = readString(dis);
					
						this._023_games_won = readInteger(dis);
					
						this._023_games_lost = readInteger(dis);
					
					this._023_avg_games_match = readString(dis);
					
						this._023_titles = readInteger(dis);
					
						this._023_finals = readInteger(dis);
					
						this._023_semifinals = readInteger(dis);
					
						this._023_best_round = readInteger(dis);
					
						this._024_matches_played = readInteger(dis);
					
						this._024_matches_won = readInteger(dis);
					
					this._024_win_pct = readString(dis);
					
						this._024_sets_won = readInteger(dis);
					
						this._024_sets_lost = readInteger(dis);
					
					this._024_avg_sets_match = readString(dis);
					
						this._024_games_won = readInteger(dis);
					
						this._024_games_lost = readInteger(dis);
					
					this._024_avg_games_match = readString(dis);
					
						this._024_titles = readInteger(dis);
					
						this._024_finals = readInteger(dis);
					
						this._024_semifinals = readInteger(dis);
					
						this._024_best_round = readInteger(dis);
					
						this._025_matches_played = readInteger(dis);
					
						this._025_matches_won = readInteger(dis);
					
					this._025_win_pct = readString(dis);
					
						this._025_sets_won = readInteger(dis);
					
						this._025_sets_lost = readInteger(dis);
					
					this._025_avg_sets_match = readString(dis);
					
						this._025_games_won = readInteger(dis);
					
						this._025_games_lost = readInteger(dis);
					
					this._025_avg_games_match = readString(dis);
					
						this._025_titles = readInteger(dis);
					
						this._025_finals = readInteger(dis);
					
						this._025_semifinals = readInteger(dis);
					
						this._025_best_round = readInteger(dis);
					
						this._026_matches_played = readInteger(dis);
					
						this._026_matches_won = readInteger(dis);
					
					this._026_win_pct = readString(dis);
					
						this._026_sets_won = readInteger(dis);
					
						this._026_sets_lost = readInteger(dis);
					
					this._026_avg_sets_match = readString(dis);
					
						this._026_games_won = readInteger(dis);
					
						this._026_games_lost = readInteger(dis);
					
					this._026_avg_games_match = readString(dis);
					
						this._026_titles = readInteger(dis);
					
						this._026_finals = readInteger(dis);
					
						this._026_semifinals = readInteger(dis);
					
						this._026_best_round = readInteger(dis);
					
						this.followers = readInteger(dis);
					
						this.interactions = readInteger(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.name,dos);
					
					// Integer
				
						writeInteger(this.ranking,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.nationality,dos);
					
					// Integer
				
						writeInteger(this.total_points,dos);
					
					// Integer
				
						writeInteger(this.height,dos);
					
					// String
				
						writeString(this.birthdate,dos);
					
					// Integer
				
						writeInteger(this.age,dos);
					
					// String
				
						writeString(this.hand,dos);
					
					// String
				
						writeString(this.side,dos);
					
					// String
				
						writeString(this.partner,dos);
					
					// Integer
				
						writeInteger(this._023_matches_played,dos);
					
					// Integer
				
						writeInteger(this._023_matches_won,dos);
					
					// String
				
						writeString(this._023_win_pct,dos);
					
					// Integer
				
						writeInteger(this._023_sets_won,dos);
					
					// Integer
				
						writeInteger(this._023_sets_lost,dos);
					
					// String
				
						writeString(this._023_avg_sets_match,dos);
					
					// Integer
				
						writeInteger(this._023_games_won,dos);
					
					// Integer
				
						writeInteger(this._023_games_lost,dos);
					
					// String
				
						writeString(this._023_avg_games_match,dos);
					
					// Integer
				
						writeInteger(this._023_titles,dos);
					
					// Integer
				
						writeInteger(this._023_finals,dos);
					
					// Integer
				
						writeInteger(this._023_semifinals,dos);
					
					// Integer
				
						writeInteger(this._023_best_round,dos);
					
					// Integer
				
						writeInteger(this._024_matches_played,dos);
					
					// Integer
				
						writeInteger(this._024_matches_won,dos);
					
					// String
				
						writeString(this._024_win_pct,dos);
					
					// Integer
				
						writeInteger(this._024_sets_won,dos);
					
					// Integer
				
						writeInteger(this._024_sets_lost,dos);
					
					// String
				
						writeString(this._024_avg_sets_match,dos);
					
					// Integer
				
						writeInteger(this._024_games_won,dos);
					
					// Integer
				
						writeInteger(this._024_games_lost,dos);
					
					// String
				
						writeString(this._024_avg_games_match,dos);
					
					// Integer
				
						writeInteger(this._024_titles,dos);
					
					// Integer
				
						writeInteger(this._024_finals,dos);
					
					// Integer
				
						writeInteger(this._024_semifinals,dos);
					
					// Integer
				
						writeInteger(this._024_best_round,dos);
					
					// Integer
				
						writeInteger(this._025_matches_played,dos);
					
					// Integer
				
						writeInteger(this._025_matches_won,dos);
					
					// String
				
						writeString(this._025_win_pct,dos);
					
					// Integer
				
						writeInteger(this._025_sets_won,dos);
					
					// Integer
				
						writeInteger(this._025_sets_lost,dos);
					
					// String
				
						writeString(this._025_avg_sets_match,dos);
					
					// Integer
				
						writeInteger(this._025_games_won,dos);
					
					// Integer
				
						writeInteger(this._025_games_lost,dos);
					
					// String
				
						writeString(this._025_avg_games_match,dos);
					
					// Integer
				
						writeInteger(this._025_titles,dos);
					
					// Integer
				
						writeInteger(this._025_finals,dos);
					
					// Integer
				
						writeInteger(this._025_semifinals,dos);
					
					// Integer
				
						writeInteger(this._025_best_round,dos);
					
					// Integer
				
						writeInteger(this._026_matches_played,dos);
					
					// Integer
				
						writeInteger(this._026_matches_won,dos);
					
					// String
				
						writeString(this._026_win_pct,dos);
					
					// Integer
				
						writeInteger(this._026_sets_won,dos);
					
					// Integer
				
						writeInteger(this._026_sets_lost,dos);
					
					// String
				
						writeString(this._026_avg_sets_match,dos);
					
					// Integer
				
						writeInteger(this._026_games_won,dos);
					
					// Integer
				
						writeInteger(this._026_games_lost,dos);
					
					// String
				
						writeString(this._026_avg_games_match,dos);
					
					// Integer
				
						writeInteger(this._026_titles,dos);
					
					// Integer
				
						writeInteger(this._026_finals,dos);
					
					// Integer
				
						writeInteger(this._026_semifinals,dos);
					
					// Integer
				
						writeInteger(this._026_best_round,dos);
					
					// Integer
				
						writeInteger(this.followers,dos);
					
					// Integer
				
						writeInteger(this.interactions,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.name,dos);
					
					// Integer
				
						writeInteger(this.ranking,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.nationality,dos);
					
					// Integer
				
						writeInteger(this.total_points,dos);
					
					// Integer
				
						writeInteger(this.height,dos);
					
					// String
				
						writeString(this.birthdate,dos);
					
					// Integer
				
						writeInteger(this.age,dos);
					
					// String
				
						writeString(this.hand,dos);
					
					// String
				
						writeString(this.side,dos);
					
					// String
				
						writeString(this.partner,dos);
					
					// Integer
				
						writeInteger(this._023_matches_played,dos);
					
					// Integer
				
						writeInteger(this._023_matches_won,dos);
					
					// String
				
						writeString(this._023_win_pct,dos);
					
					// Integer
				
						writeInteger(this._023_sets_won,dos);
					
					// Integer
				
						writeInteger(this._023_sets_lost,dos);
					
					// String
				
						writeString(this._023_avg_sets_match,dos);
					
					// Integer
				
						writeInteger(this._023_games_won,dos);
					
					// Integer
				
						writeInteger(this._023_games_lost,dos);
					
					// String
				
						writeString(this._023_avg_games_match,dos);
					
					// Integer
				
						writeInteger(this._023_titles,dos);
					
					// Integer
				
						writeInteger(this._023_finals,dos);
					
					// Integer
				
						writeInteger(this._023_semifinals,dos);
					
					// Integer
				
						writeInteger(this._023_best_round,dos);
					
					// Integer
				
						writeInteger(this._024_matches_played,dos);
					
					// Integer
				
						writeInteger(this._024_matches_won,dos);
					
					// String
				
						writeString(this._024_win_pct,dos);
					
					// Integer
				
						writeInteger(this._024_sets_won,dos);
					
					// Integer
				
						writeInteger(this._024_sets_lost,dos);
					
					// String
				
						writeString(this._024_avg_sets_match,dos);
					
					// Integer
				
						writeInteger(this._024_games_won,dos);
					
					// Integer
				
						writeInteger(this._024_games_lost,dos);
					
					// String
				
						writeString(this._024_avg_games_match,dos);
					
					// Integer
				
						writeInteger(this._024_titles,dos);
					
					// Integer
				
						writeInteger(this._024_finals,dos);
					
					// Integer
				
						writeInteger(this._024_semifinals,dos);
					
					// Integer
				
						writeInteger(this._024_best_round,dos);
					
					// Integer
				
						writeInteger(this._025_matches_played,dos);
					
					// Integer
				
						writeInteger(this._025_matches_won,dos);
					
					// String
				
						writeString(this._025_win_pct,dos);
					
					// Integer
				
						writeInteger(this._025_sets_won,dos);
					
					// Integer
				
						writeInteger(this._025_sets_lost,dos);
					
					// String
				
						writeString(this._025_avg_sets_match,dos);
					
					// Integer
				
						writeInteger(this._025_games_won,dos);
					
					// Integer
				
						writeInteger(this._025_games_lost,dos);
					
					// String
				
						writeString(this._025_avg_games_match,dos);
					
					// Integer
				
						writeInteger(this._025_titles,dos);
					
					// Integer
				
						writeInteger(this._025_finals,dos);
					
					// Integer
				
						writeInteger(this._025_semifinals,dos);
					
					// Integer
				
						writeInteger(this._025_best_round,dos);
					
					// Integer
				
						writeInteger(this._026_matches_played,dos);
					
					// Integer
				
						writeInteger(this._026_matches_won,dos);
					
					// String
				
						writeString(this._026_win_pct,dos);
					
					// Integer
				
						writeInteger(this._026_sets_won,dos);
					
					// Integer
				
						writeInteger(this._026_sets_lost,dos);
					
					// String
				
						writeString(this._026_avg_sets_match,dos);
					
					// Integer
				
						writeInteger(this._026_games_won,dos);
					
					// Integer
				
						writeInteger(this._026_games_lost,dos);
					
					// String
				
						writeString(this._026_avg_games_match,dos);
					
					// Integer
				
						writeInteger(this._026_titles,dos);
					
					// Integer
				
						writeInteger(this._026_finals,dos);
					
					// Integer
				
						writeInteger(this._026_semifinals,dos);
					
					// Integer
				
						writeInteger(this._026_best_round,dos);
					
					// Integer
				
						writeInteger(this.followers,dos);
					
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
		sb.append("name="+name);
		sb.append(",ranking="+String.valueOf(ranking));
		sb.append(",gender="+gender);
		sb.append(",nationality="+nationality);
		sb.append(",total_points="+String.valueOf(total_points));
		sb.append(",height="+String.valueOf(height));
		sb.append(",birthdate="+birthdate);
		sb.append(",age="+String.valueOf(age));
		sb.append(",hand="+hand);
		sb.append(",side="+side);
		sb.append(",partner="+partner);
		sb.append(",_023_matches_played="+String.valueOf(_023_matches_played));
		sb.append(",_023_matches_won="+String.valueOf(_023_matches_won));
		sb.append(",_023_win_pct="+_023_win_pct);
		sb.append(",_023_sets_won="+String.valueOf(_023_sets_won));
		sb.append(",_023_sets_lost="+String.valueOf(_023_sets_lost));
		sb.append(",_023_avg_sets_match="+_023_avg_sets_match);
		sb.append(",_023_games_won="+String.valueOf(_023_games_won));
		sb.append(",_023_games_lost="+String.valueOf(_023_games_lost));
		sb.append(",_023_avg_games_match="+_023_avg_games_match);
		sb.append(",_023_titles="+String.valueOf(_023_titles));
		sb.append(",_023_finals="+String.valueOf(_023_finals));
		sb.append(",_023_semifinals="+String.valueOf(_023_semifinals));
		sb.append(",_023_best_round="+String.valueOf(_023_best_round));
		sb.append(",_024_matches_played="+String.valueOf(_024_matches_played));
		sb.append(",_024_matches_won="+String.valueOf(_024_matches_won));
		sb.append(",_024_win_pct="+_024_win_pct);
		sb.append(",_024_sets_won="+String.valueOf(_024_sets_won));
		sb.append(",_024_sets_lost="+String.valueOf(_024_sets_lost));
		sb.append(",_024_avg_sets_match="+_024_avg_sets_match);
		sb.append(",_024_games_won="+String.valueOf(_024_games_won));
		sb.append(",_024_games_lost="+String.valueOf(_024_games_lost));
		sb.append(",_024_avg_games_match="+_024_avg_games_match);
		sb.append(",_024_titles="+String.valueOf(_024_titles));
		sb.append(",_024_finals="+String.valueOf(_024_finals));
		sb.append(",_024_semifinals="+String.valueOf(_024_semifinals));
		sb.append(",_024_best_round="+String.valueOf(_024_best_round));
		sb.append(",_025_matches_played="+String.valueOf(_025_matches_played));
		sb.append(",_025_matches_won="+String.valueOf(_025_matches_won));
		sb.append(",_025_win_pct="+_025_win_pct);
		sb.append(",_025_sets_won="+String.valueOf(_025_sets_won));
		sb.append(",_025_sets_lost="+String.valueOf(_025_sets_lost));
		sb.append(",_025_avg_sets_match="+_025_avg_sets_match);
		sb.append(",_025_games_won="+String.valueOf(_025_games_won));
		sb.append(",_025_games_lost="+String.valueOf(_025_games_lost));
		sb.append(",_025_avg_games_match="+_025_avg_games_match);
		sb.append(",_025_titles="+String.valueOf(_025_titles));
		sb.append(",_025_finals="+String.valueOf(_025_finals));
		sb.append(",_025_semifinals="+String.valueOf(_025_semifinals));
		sb.append(",_025_best_round="+String.valueOf(_025_best_round));
		sb.append(",_026_matches_played="+String.valueOf(_026_matches_played));
		sb.append(",_026_matches_won="+String.valueOf(_026_matches_won));
		sb.append(",_026_win_pct="+_026_win_pct);
		sb.append(",_026_sets_won="+String.valueOf(_026_sets_won));
		sb.append(",_026_sets_lost="+String.valueOf(_026_sets_lost));
		sb.append(",_026_avg_sets_match="+_026_avg_sets_match);
		sb.append(",_026_games_won="+String.valueOf(_026_games_won));
		sb.append(",_026_games_lost="+String.valueOf(_026_games_lost));
		sb.append(",_026_avg_games_match="+_026_avg_games_match);
		sb.append(",_026_titles="+String.valueOf(_026_titles));
		sb.append(",_026_finals="+String.valueOf(_026_finals));
		sb.append(",_026_semifinals="+String.valueOf(_026_semifinals));
		sb.append(",_026_best_round="+String.valueOf(_026_best_round));
		sb.append(",followers="+String.valueOf(followers));
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


		tDBInput_6Process(globalMap);
		tDBInput_7Process(globalMap);

		row6Struct row6 = new row6Struct();
out2Struct out2 = new out2Struct();





	
	/**
	 * [tDBOutput_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tDBOutput_1", false);
		start_Hash.put("tDBOutput_1", System.currentTimeMillis());
		
	
	currentComponent="tDBOutput_1";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"out2");
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

 
	final String decryptedPassword_tDBOutput_1 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:fZF9Q4BU+z8xVCZ3AtWxf5+qlxxgVIXXPdtsR7x4zjKAPA8=");

    String dbPwd_tDBOutput_1 = decryptedPassword_tDBOutput_1;	
    conn_tDBOutput_1 = java.sql.DriverManager.getConnection(url_tDBOutput_1,dbUser_tDBOutput_1,dbPwd_tDBOutput_1);
	
		resourceMap.put("conn_tDBOutput_1", conn_tDBOutput_1);
	
        conn_tDBOutput_1.setAutoCommit(false);
        int commitEvery_tDBOutput_1 = 10000;
        int commitCounter_tDBOutput_1 = 0;

   int batchSize_tDBOutput_1 = 10000;
   int batchSizeCounter_tDBOutput_1=0;

if(dbschema_tDBOutput_1 == null || dbschema_tDBOutput_1.trim().length() == 0) {
    tableName_tDBOutput_1 = "FACT_PLAYERS";
} else {
    tableName_tDBOutput_1 = dbschema_tDBOutput_1 + "].[" + "FACT_PLAYERS";
}
	int count_tDBOutput_1=0;

        String insert_tDBOutput_1 = "INSERT INTO [" + tableName_tDBOutput_1 + "] ([player_id],[ranking],[height],[birthdate],[_023_matches_played],[_023_games_won],[_023_games_lost],[_024_matches_played],[_024_matches_won],[_024_games_won],[_024_games_lost],[_025_matches_played],[_025_matches_won],[_025_games_won],[_025_games_lost],[_026_matches_played],[_026_matches_won],[_026_games_won],[_026_games_lost]) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        java.sql.PreparedStatement pstmt_tDBOutput_1 = conn_tDBOutput_1.prepareStatement(insert_tDBOutput_1);
        resourceMap.put("pstmt_tDBOutput_1", pstmt_tDBOutput_1);


 



/**
 * [tDBOutput_1 begin ] stop
 */



	
	/**
	 * [tMap_2 begin ] start
	 */

	

	
		
		ok_Hash.put("tMap_2", false);
		start_Hash.put("tMap_2", System.currentTimeMillis());
		
	
	currentComponent="tMap_2";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row6");
					}
				
		int tos_count_tMap_2 = 0;
		




// ###############################
// # Lookup's keys initialization
	
		org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<out3Struct> tHash_Lookup_out3 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<out3Struct>) 
				((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<out3Struct>) 
					globalMap.get( "tHash_Lookup_out3" ))
					;					
					
	

out3Struct out3HashKey = new out3Struct();
out3Struct out3Default = new out3Struct();
	
		org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row9Struct> tHash_Lookup_row9 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row9Struct>) 
				((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row9Struct>) 
					globalMap.get( "tHash_Lookup_row9" ))
					;					
					
	

row9Struct row9HashKey = new row9Struct();
row9Struct row9Default = new row9Struct();
// ###############################        

// ###############################
// # Vars initialization
class  Var__tMap_2__Struct  {
}
Var__tMap_2__Struct Var__tMap_2 = new Var__tMap_2__Struct();
// ###############################

// ###############################
// # Outputs initialization
out2Struct out2_tmp = new out2Struct();
// ###############################

        
        



        









 



/**
 * [tMap_2 begin ] stop
 */



	
	/**
	 * [tFileInputExcel_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tFileInputExcel_1", false);
		start_Hash.put("tFileInputExcel_1", System.currentTimeMillis());
		
	
	currentComponent="tFileInputExcel_1";

	
		int tos_count_tFileInputExcel_1 = 0;
		

 
	final String decryptedPassword_tFileInputExcel_1 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:JF31B23VuB3BuKC20BdRaJ7lKexPI0ny+WSinQ==");
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

		Object source_tFileInputExcel_1 = "C:/pi/players.xlsx";
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
		    row6 = null;
					int tempRowLength_tFileInputExcel_1 = 65;
				
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
			row6 = new row6Struct();
			int curColNum_tFileInputExcel_1 = -1;
			String curColName_tFileInputExcel_1 = "";
			try{
							columnIndex_tFileInputExcel_1 = 0;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "name";

				row6.name = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row6.name = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 1;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "ranking";

				row6.ranking = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6.ranking = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 2;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "gender";

				row6.gender = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row6.gender = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 3;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "nationality";

				row6.nationality = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row6.nationality = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 4;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "total_points";

				row6.total_points = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6.total_points = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 5;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "height";

				row6.height = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6.height = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 6;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "birthdate";

				row6.birthdate = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row6.birthdate = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 7;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "age";

				row6.age = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6.age = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 8;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "hand";

				row6.hand = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row6.hand = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 9;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "side";

				row6.side = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row6.side = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 10;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "partner";

				row6.partner = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row6.partner = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 11;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_023_matches_played";

				row6._023_matches_played = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._023_matches_played = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 12;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_023_matches_won";

				row6._023_matches_won = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._023_matches_won = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 13;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_023_win_pct";

				row6._023_win_pct = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row6._023_win_pct = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 14;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_023_sets_won";

				row6._023_sets_won = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._023_sets_won = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 15;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_023_sets_lost";

				row6._023_sets_lost = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._023_sets_lost = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 16;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_023_avg_sets_match";

				row6._023_avg_sets_match = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row6._023_avg_sets_match = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 17;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_023_games_won";

				row6._023_games_won = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._023_games_won = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 18;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_023_games_lost";

				row6._023_games_lost = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._023_games_lost = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 19;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_023_avg_games_match";

				row6._023_avg_games_match = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row6._023_avg_games_match = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 20;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_023_titles";

				row6._023_titles = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._023_titles = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 21;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_023_finals";

				row6._023_finals = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._023_finals = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 22;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_023_semifinals";

				row6._023_semifinals = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._023_semifinals = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 23;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_023_best_round";

				row6._023_best_round = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._023_best_round = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 24;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_024_matches_played";

				row6._024_matches_played = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._024_matches_played = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 25;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_024_matches_won";

				row6._024_matches_won = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._024_matches_won = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 26;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_024_win_pct";

				row6._024_win_pct = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row6._024_win_pct = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 27;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_024_sets_won";

				row6._024_sets_won = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._024_sets_won = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 28;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_024_sets_lost";

				row6._024_sets_lost = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._024_sets_lost = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 29;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_024_avg_sets_match";

				row6._024_avg_sets_match = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row6._024_avg_sets_match = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 30;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_024_games_won";

				row6._024_games_won = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._024_games_won = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 31;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_024_games_lost";

				row6._024_games_lost = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._024_games_lost = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 32;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_024_avg_games_match";

				row6._024_avg_games_match = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row6._024_avg_games_match = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 33;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_024_titles";

				row6._024_titles = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._024_titles = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 34;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_024_finals";

				row6._024_finals = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._024_finals = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 35;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_024_semifinals";

				row6._024_semifinals = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._024_semifinals = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 36;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_024_best_round";

				row6._024_best_round = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._024_best_round = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 37;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_025_matches_played";

				row6._025_matches_played = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._025_matches_played = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 38;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_025_matches_won";

				row6._025_matches_won = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._025_matches_won = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 39;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_025_win_pct";

				row6._025_win_pct = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row6._025_win_pct = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 40;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_025_sets_won";

				row6._025_sets_won = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._025_sets_won = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 41;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_025_sets_lost";

				row6._025_sets_lost = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._025_sets_lost = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 42;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_025_avg_sets_match";

				row6._025_avg_sets_match = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row6._025_avg_sets_match = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 43;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_025_games_won";

				row6._025_games_won = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._025_games_won = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 44;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_025_games_lost";

				row6._025_games_lost = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._025_games_lost = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 45;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_025_avg_games_match";

				row6._025_avg_games_match = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row6._025_avg_games_match = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 46;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_025_titles";

				row6._025_titles = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._025_titles = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 47;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_025_finals";

				row6._025_finals = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._025_finals = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 48;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_025_semifinals";

				row6._025_semifinals = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._025_semifinals = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 49;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_025_best_round";

				row6._025_best_round = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._025_best_round = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 50;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_026_matches_played";

				row6._026_matches_played = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._026_matches_played = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 51;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_026_matches_won";

				row6._026_matches_won = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._026_matches_won = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 52;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_026_win_pct";

				row6._026_win_pct = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row6._026_win_pct = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 53;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_026_sets_won";

				row6._026_sets_won = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._026_sets_won = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 54;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_026_sets_lost";

				row6._026_sets_lost = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._026_sets_lost = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 55;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_026_avg_sets_match";

				row6._026_avg_sets_match = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row6._026_avg_sets_match = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 56;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_026_games_won";

				row6._026_games_won = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._026_games_won = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 57;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_026_games_lost";

				row6._026_games_lost = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._026_games_lost = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 58;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_026_avg_games_match";

				row6._026_avg_games_match = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row6._026_avg_games_match = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 59;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_026_titles";

				row6._026_titles = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._026_titles = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 60;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_026_finals";

				row6._026_finals = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._026_finals = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 61;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_026_semifinals";

				row6._026_semifinals = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._026_semifinals = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 62;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "_026_best_round";

				row6._026_best_round = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6._026_best_round = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 63;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "followers";

				row6.followers = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6.followers = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 64;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "interactions";

				row6.interactions = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row6.interactions = null;
				emptyColumnCount_tFileInputExcel_1++;
			}

				nb_line_tFileInputExcel_1++;
				
			}catch(java.lang.Exception e){
globalMap.put("tFileInputExcel_1_ERROR_MESSAGE",e.getMessage());
			whetherReject_tFileInputExcel_1 = true;
					 System.err.println(e.getMessage());
					 row6 = null;
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
// Start of branch "row6"
if(row6 != null) { 



	
	/**
	 * [tMap_2 main ] start
	 */

	

	
	
	currentComponent="tMap_2";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row6"
						
						);
					}
					

		
		
		boolean hasCasePrimitiveKeyWithNull_tMap_2 = false;
		

        // ###############################
        // # Input tables (lookups)
		  boolean rejectedInnerJoin_tMap_2 = false;
		  boolean mainRowRejected_tMap_2 = false;
            				    								  
		

				///////////////////////////////////////////////
				// Starting Lookup Table "out3" 
				///////////////////////////////////////////////


				
				
                            
 					    boolean forceLoopout3 = false;
       		  	    	
       		  	    	
 							out3Struct out3ObjectFromLookup = null;
                          
		           		  	if(!rejectedInnerJoin_tMap_2) { // G_TM_M_020

								
								hasCasePrimitiveKeyWithNull_tMap_2 = false;
								
                        		    		    out3HashKey.full_name = row6.name ;
                        		    		

								
		                        	out3HashKey.hashCodeDirty = true;
                        		
	  					
	  							
			  					
			  					
	  					
		  							tHash_Lookup_out3.lookup( out3HashKey );

	  							

	  							

 								
								  
								  if(!tHash_Lookup_out3.hasNext()) { // G_TM_M_090

  								
		  				
	  								
			  							rejectedInnerJoin_tMap_2 = true;
	  								
						
									
  									  		
 								
								  
								  } // G_TM_M_090

  								



							} // G_TM_M_020
			           		  	  
							
				           		if(tHash_Lookup_out3 != null && tHash_Lookup_out3.getCount(out3HashKey) > 1) { // G 071
			  							
			  						
									 		
									//System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'out3' and it contains more one result from keys :  out3.full_name = '" + out3HashKey.full_name + "'");
								} // G 071
							

							out3Struct out3 = null;
                    		  	 
							   
                    		  	 
	       		  	    	out3Struct fromLookup_out3 = null;
							out3 = out3Default;
										 
							
								 
							
							
								if (tHash_Lookup_out3 !=null && tHash_Lookup_out3.hasNext()) { // G 099
								
							
								
								fromLookup_out3 = tHash_Lookup_out3.next();

							
							
								} // G 099
							
							

							if(fromLookup_out3 != null) {
								out3 = fromLookup_out3;
							}
							
							
							
			  							
								
	                    		  	
		                    
	            	
	           	
	            	
	            	
	            

				///////////////////////////////////////////////
				// Starting Lookup Table "row9" 
				///////////////////////////////////////////////


				
				
                            
 					    boolean forceLooprow9 = false;
       		  	    	
       		  	    	
 							row9Struct row9ObjectFromLookup = null;
                          
		           		  	if(!rejectedInnerJoin_tMap_2) { // G_TM_M_020

								
								hasCasePrimitiveKeyWithNull_tMap_2 = false;
								
                        		    		    row9HashKey.full_date = DateUtils.toDate(row6.birthdate)  == null ? null : new java.util.Date(DateUtils.toDate(row6.birthdate) .getTime());
                        		    		

								
		                        	row9HashKey.hashCodeDirty = true;
                        		
	  					
	  							
			  					
			  					
	  					
		  							tHash_Lookup_row9.lookup( row9HashKey );

	  							

	  							

 								
								  
								  if(!tHash_Lookup_row9.hasNext()) { // G_TM_M_090

  								
		  				
	  								
			  							rejectedInnerJoin_tMap_2 = true;
	  								
						
									
  									  		
 								
								  
								  } // G_TM_M_090

  								



							} // G_TM_M_020
			           		  	  
							
				           		if(tHash_Lookup_row9 != null && tHash_Lookup_row9.getCount(row9HashKey) > 1) { // G 071
			  							
			  						
									 		
									//System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row9' and it contains more one result from keys :  row9.full_date = '" + row9HashKey.full_date + "'");
								} // G 071
							

							row9Struct row9 = null;
                    		  	 
							   
                    		  	 
	       		  	    	row9Struct fromLookup_row9 = null;
							row9 = row9Default;
										 
							
								 
							
							
								if (tHash_Lookup_row9 !=null && tHash_Lookup_row9.hasNext()) { // G 099
								
							
								
								fromLookup_row9 = tHash_Lookup_row9.next();

							
							
								} // G 099
							
							

							if(fromLookup_row9 != null) {
								row9 = fromLookup_row9;
							}
							
							
							
			  							
								
	                    		  	
		                    
	            	
	            	
	            // ###############################
        { // start of Var scope
        
	        // ###############################
        	// # Vars tables
        
Var__tMap_2__Struct Var = Var__tMap_2;// ###############################
        // ###############################
        // # Output tables

out2 = null;

if(!rejectedInnerJoin_tMap_2 ) {

// # Output table : 'out2'
out2_tmp.player_id = out3.player_id ;
out2_tmp.ranking = row6.ranking ;
out2_tmp.height = row6.height ;
out2_tmp.birthdate = row9.date_key ;
out2_tmp._023_matches_played = row6._023_matches_played ;
out2_tmp._023_games_won = row6._023_games_won ;
out2_tmp._023_games_lost = row6._023_games_lost ;
out2_tmp._024_matches_played = row6._024_matches_played ;
out2_tmp._024_matches_won = row6._024_matches_won  ;
out2_tmp._024_games_won = row6._024_games_won ;
out2_tmp._024_games_lost = row6._024_games_lost ;
out2_tmp._025_matches_played = row6._025_matches_played ;
out2_tmp._025_matches_won = row6._025_matches_won ;
out2_tmp._025_games_won = row6._025_games_won ;
out2_tmp._025_games_lost = row6._025_games_lost ;
out2_tmp._026_matches_played = row6._026_matches_played ;
out2_tmp._026_matches_won = row6._026_matches_won ;
out2_tmp._026_games_won = row6._026_games_won ;
out2_tmp._026_games_lost = row6._026_games_lost ;
out2 = out2_tmp;
}  // closing inner join bracket (2)
// ###############################

} // end of Var scope

rejectedInnerJoin_tMap_2 = false;










 


	tos_count_tMap_2++;

/**
 * [tMap_2 main ] stop
 */
	
	/**
	 * [tMap_2 process_data_begin ] start
	 */

	

	
	
	currentComponent="tMap_2";

	

 



/**
 * [tMap_2 process_data_begin ] stop
 */
// Start of branch "out2"
if(out2 != null) { 



	
	/**
	 * [tDBOutput_1 main ] start
	 */

	

	
	
	currentComponent="tDBOutput_1";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"out2"
						
						);
					}
					



        whetherReject_tDBOutput_1 = false;
                    if(out2.player_id == null) {
pstmt_tDBOutput_1.setNull(1, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(1, out2.player_id);
}

                    if(out2.ranking == null) {
pstmt_tDBOutput_1.setNull(2, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(2, out2.ranking);
}

                    if(out2.height == null) {
pstmt_tDBOutput_1.setNull(3, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(3, out2.height);
}

                    if(out2.birthdate == null) {
pstmt_tDBOutput_1.setNull(4, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(4, out2.birthdate);
}

                    if(out2._023_matches_played == null) {
pstmt_tDBOutput_1.setNull(5, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(5, out2._023_matches_played);
}

                    if(out2._023_games_won == null) {
pstmt_tDBOutput_1.setNull(6, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(6, out2._023_games_won);
}

                    if(out2._023_games_lost == null) {
pstmt_tDBOutput_1.setNull(7, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(7, out2._023_games_lost);
}

                    if(out2._024_matches_played == null) {
pstmt_tDBOutput_1.setNull(8, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(8, out2._024_matches_played);
}

                    if(out2._024_matches_won == null) {
pstmt_tDBOutput_1.setNull(9, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(9, out2._024_matches_won);
}

                    if(out2._024_games_won == null) {
pstmt_tDBOutput_1.setNull(10, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(10, out2._024_games_won);
}

                    if(out2._024_games_lost == null) {
pstmt_tDBOutput_1.setNull(11, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(11, out2._024_games_lost);
}

                    if(out2._025_matches_played == null) {
pstmt_tDBOutput_1.setNull(12, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(12, out2._025_matches_played);
}

                    if(out2._025_matches_won == null) {
pstmt_tDBOutput_1.setNull(13, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(13, out2._025_matches_won);
}

                    if(out2._025_games_won == null) {
pstmt_tDBOutput_1.setNull(14, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(14, out2._025_games_won);
}

                    if(out2._025_games_lost == null) {
pstmt_tDBOutput_1.setNull(15, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(15, out2._025_games_lost);
}

                    if(out2._026_matches_played == null) {
pstmt_tDBOutput_1.setNull(16, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(16, out2._026_matches_played);
}

                    if(out2._026_matches_won == null) {
pstmt_tDBOutput_1.setNull(17, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(17, out2._026_matches_won);
}

                    if(out2._026_games_won == null) {
pstmt_tDBOutput_1.setNull(18, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(18, out2._026_games_won);
}

                    if(out2._026_games_lost == null) {
pstmt_tDBOutput_1.setNull(19, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(19, out2._026_games_lost);
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

} // End of branch "out2"




	
	/**
	 * [tMap_2 process_data_end ] start
	 */

	

	
	
	currentComponent="tMap_2";

	

 



/**
 * [tMap_2 process_data_end ] stop
 */

} // End of branch "row6"




	
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
	 * [tMap_2 end ] start
	 */

	

	
	
	currentComponent="tMap_2";

	


// ###############################
// # Lookup hashes releasing
					if(tHash_Lookup_out3 != null) {
						tHash_Lookup_out3.endGet();
					}
					globalMap.remove( "tHash_Lookup_out3" );

					
					
				
					if(tHash_Lookup_row9 != null) {
						tHash_Lookup_row9.endGet();
					}
					globalMap.remove( "tHash_Lookup_row9" );

					
					
				
// ###############################      





				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row6");
			  	}
			  	
 

ok_Hash.put("tMap_2", true);
end_Hash.put("tMap_2", System.currentTimeMillis());




/**
 * [tMap_2 end ] stop
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
			  		runStat.updateStat(resourceMap,iterateId,2,0,"out2");
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
				
					     			//free memory for "tMap_2"
					     			globalMap.remove("tHash_Lookup_out3"); 
				     			
					     			//free memory for "tMap_2"
					     			globalMap.remove("tHash_Lookup_row9"); 
				     			
				try{
					
	
	/**
	 * [tFileInputExcel_1 finally ] start
	 */

	

	
	
	currentComponent="tFileInputExcel_1";

	

 



/**
 * [tFileInputExcel_1 finally ] stop
 */

	
	/**
	 * [tMap_2 finally ] start
	 */

	

	
	
	currentComponent="tMap_2";

	

 



/**
 * [tMap_2 finally ] stop
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
	


public static class out3Struct implements routines.system.IPersistableComparableLookupRow<out3Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_FACT_PLAYERS = new byte[0];
    static byte[] commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[0];
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
		final out3Struct other = (out3Struct) obj;
		
						if (this.full_name == null) {
							if (other.full_name != null)
								return false;
						
						} else if (!this.full_name.equals(other.full_name))
						
							return false;
					

		return true;
    }

	public void copyDataTo(out3Struct other) {

		other.player_id = this.player_id;
	            other.full_name = this.full_name;
	            
	}

	public void copyKeysDataTo(out3Struct other) {

		other.full_name = this.full_name;
	            	
	}




	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_FACT_PLAYERS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_PLAYERS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_FACT_PLAYERS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_PLAYERS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length, utf8Charset);
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

    public void readKeysData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

        	try {

        		int length = 0;
		
					this.full_name = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

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
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }
    
    public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
        try {
			int length = 0;
		
			            this.player_id = objectIn.readInt();
					
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
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}

    }
    
    public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut){
                try {

		
					objectOut.writeInt(this.player_id);
					
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
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(out3Struct other) {

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

public static class row7Struct implements routines.system.IPersistableRow<row7Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_FACT_PLAYERS = new byte[0];
    static byte[] commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[0];

	
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
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_FACT_PLAYERS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_PLAYERS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_FACT_PLAYERS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_PLAYERS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

        	try {

        		int length = 0;
		
			        this.player_id = dis.readInt();
					
					this.full_name = readString(dis);
					
						this.ranking = readInteger(dis);
					
					this.gender = readString(dis);
					
					this.nationality = readString(dis);
					
					this.birthdate = readDate(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.height_cm = null;
           				} else {
           			    	this.height_cm = dis.readShort();
           				}
					
					this.playing_hand = readString(dis);
					
					this.court_side = readString(dis);
					
					this.partner_name = readString(dis);
					
						this.total_points = readInteger(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

        	try {

        		int length = 0;
		
			        this.player_id = dis.readInt();
					
					this.full_name = readString(dis);
					
						this.ranking = readInteger(dis);
					
					this.gender = readString(dis);
					
					this.nationality = readString(dis);
					
					this.birthdate = readDate(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.height_cm = null;
           				} else {
           			    	this.height_cm = dis.readShort();
           				}
					
					this.playing_hand = readString(dis);
					
					this.court_side = readString(dis);
					
					this.partner_name = readString(dis);
					
						this.total_points = readInteger(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// int
				
		            	dos.writeInt(this.player_id);
					
					// String
				
						writeString(this.full_name,dos);
					
					// Integer
				
						writeInteger(this.ranking,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.nationality,dos);
					
					// java.util.Date
				
						writeDate(this.birthdate,dos);
					
					// Short
				
						if(this.height_cm == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeShort(this.height_cm);
		            	}
					
					// String
				
						writeString(this.playing_hand,dos);
					
					// String
				
						writeString(this.court_side,dos);
					
					// String
				
						writeString(this.partner_name,dos);
					
					// Integer
				
						writeInteger(this.total_points,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// int
				
		            	dos.writeInt(this.player_id);
					
					// String
				
						writeString(this.full_name,dos);
					
					// Integer
				
						writeInteger(this.ranking,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.nationality,dos);
					
					// java.util.Date
				
						writeDate(this.birthdate,dos);
					
					// Short
				
						if(this.height_cm == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeShort(this.height_cm);
		            	}
					
					// String
				
						writeString(this.playing_hand,dos);
					
					// String
				
						writeString(this.court_side,dos);
					
					// String
				
						writeString(this.partner_name,dos);
					
					// Integer
				
						writeInteger(this.total_points,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


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

public static class after_tDBInput_6Struct implements routines.system.IPersistableRow<after_tDBInput_6Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_FACT_PLAYERS = new byte[0];
    static byte[] commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[0];
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
	
							result = prime * result + (int) this.player_id;
						
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
		final after_tDBInput_6Struct other = (after_tDBInput_6Struct) obj;
		
						if (this.player_id != other.player_id)
							return false;
					

		return true;
    }

	public void copyDataTo(after_tDBInput_6Struct other) {

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

	public void copyKeysDataTo(after_tDBInput_6Struct other) {

		other.player_id = this.player_id;
	            	
	}




	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_FACT_PLAYERS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_PLAYERS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_FACT_PLAYERS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_PLAYERS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

        	try {

        		int length = 0;
		
			        this.player_id = dis.readInt();
					
					this.full_name = readString(dis);
					
						this.ranking = readInteger(dis);
					
					this.gender = readString(dis);
					
					this.nationality = readString(dis);
					
					this.birthdate = readDate(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.height_cm = null;
           				} else {
           			    	this.height_cm = dis.readShort();
           				}
					
					this.playing_hand = readString(dis);
					
					this.court_side = readString(dis);
					
					this.partner_name = readString(dis);
					
						this.total_points = readInteger(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

        	try {

        		int length = 0;
		
			        this.player_id = dis.readInt();
					
					this.full_name = readString(dis);
					
						this.ranking = readInteger(dis);
					
					this.gender = readString(dis);
					
					this.nationality = readString(dis);
					
					this.birthdate = readDate(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.height_cm = null;
           				} else {
           			    	this.height_cm = dis.readShort();
           				}
					
					this.playing_hand = readString(dis);
					
					this.court_side = readString(dis);
					
					this.partner_name = readString(dis);
					
						this.total_points = readInteger(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// int
				
		            	dos.writeInt(this.player_id);
					
					// String
				
						writeString(this.full_name,dos);
					
					// Integer
				
						writeInteger(this.ranking,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.nationality,dos);
					
					// java.util.Date
				
						writeDate(this.birthdate,dos);
					
					// Short
				
						if(this.height_cm == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeShort(this.height_cm);
		            	}
					
					// String
				
						writeString(this.playing_hand,dos);
					
					// String
				
						writeString(this.court_side,dos);
					
					// String
				
						writeString(this.partner_name,dos);
					
					// Integer
				
						writeInteger(this.total_points,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// int
				
		            	dos.writeInt(this.player_id);
					
					// String
				
						writeString(this.full_name,dos);
					
					// Integer
				
						writeInteger(this.ranking,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.nationality,dos);
					
					// java.util.Date
				
						writeDate(this.birthdate,dos);
					
					// Short
				
						if(this.height_cm == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeShort(this.height_cm);
		            	}
					
					// String
				
						writeString(this.playing_hand,dos);
					
					// String
				
						writeString(this.court_side,dos);
					
					// String
				
						writeString(this.partner_name,dos);
					
					// Integer
				
						writeInteger(this.total_points,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


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
    public int compareTo(after_tDBInput_6Struct other) {

		int returnValue = -1;
		
						returnValue = checkNullsAndCompare(this.player_id, other.player_id);
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


		tDBInput_2Process(globalMap);

		row7Struct row7 = new row7Struct();
out3Struct out3 = new out3Struct();





	
	/**
	 * [tAdvancedHash_out3 begin ] start
	 */

	

	
		
		ok_Hash.put("tAdvancedHash_out3", false);
		start_Hash.put("tAdvancedHash_out3", System.currentTimeMillis());
		
	
	currentComponent="tAdvancedHash_out3";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"out3");
					}
				
		int tos_count_tAdvancedHash_out3 = 0;
		

			   		// connection name:out3
			   		// source node:tMap_3 - inputs:(row7,row8) outputs:(out3,out3) | target node:tAdvancedHash_out3 - inputs:(out3) outputs:()
			   		// linked node: tMap_2 - inputs:(row6,out3,row9) outputs:(out2)
			   
			   		org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_out3 = 
			   			org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;
			   			
			   
	   			org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<out3Struct> tHash_Lookup_out3 =org.talend.designer.components.lookup.memory.AdvancedMemoryLookup.
	   						<out3Struct>getLookup(matchingModeEnum_out3);
	   						   
		   	   	   globalMap.put("tHash_Lookup_out3", tHash_Lookup_out3);
		   	   	   
				
           

 



/**
 * [tAdvancedHash_out3 begin ] stop
 */



	
	/**
	 * [tMap_3 begin ] start
	 */

	

	
		
		ok_Hash.put("tMap_3", false);
		start_Hash.put("tMap_3", System.currentTimeMillis());
		
	
	currentComponent="tMap_3";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row7");
					}
				
		int tos_count_tMap_3 = 0;
		




// ###############################
// # Lookup's keys initialization
	
		org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct> tHash_Lookup_row8 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct>) 
				((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct>) 
					globalMap.get( "tHash_Lookup_row8" ))
					;					
					
	

row8Struct row8HashKey = new row8Struct();
row8Struct row8Default = new row8Struct();
// ###############################        

// ###############################
// # Vars initialization
class  Var__tMap_3__Struct  {
}
Var__tMap_3__Struct Var__tMap_3 = new Var__tMap_3__Struct();
// ###############################

// ###############################
// # Outputs initialization
out3Struct out3_tmp = new out3Struct();
// ###############################

        
        



        









 



/**
 * [tMap_3 begin ] stop
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
				
				 
	final String decryptedPassword_tDBInput_6 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:B3zO/9QoHT/jDvActfOI1RtLAszeKeBwsrVEHYYTLwge30o=");
				
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
								row7.player_id = 0;
							} else {
		                          
            row7.player_id = rs_tDBInput_6.getInt(1);
            if(rs_tDBInput_6.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
							if(colQtyInRs_tDBInput_6 < 2) {
								row7.full_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_6 = rs_tDBInput_6.getString(2);
            if(tmpContent_tDBInput_6 != null) {
            	if (talendToDBList_tDBInput_6 .contains(rsmd_tDBInput_6.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
            		row7.full_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_6);
            	} else {
                	row7.full_name = tmpContent_tDBInput_6;
                }
            } else {
                row7.full_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_6 < 3) {
								row7.ranking = null;
							} else {
		                          
            row7.ranking = rs_tDBInput_6.getInt(3);
            if(rs_tDBInput_6.wasNull()){
                    row7.ranking = null;
            }
		                    }
							if(colQtyInRs_tDBInput_6 < 4) {
								row7.gender = null;
							} else {
	                         		
           		tmpContent_tDBInput_6 = rs_tDBInput_6.getString(4);
            if(tmpContent_tDBInput_6 != null) {
            	if (talendToDBList_tDBInput_6 .contains(rsmd_tDBInput_6.getColumnTypeName(4).toUpperCase(java.util.Locale.ENGLISH))) {
            		row7.gender = FormatterUtils.formatUnwithE(tmpContent_tDBInput_6);
            	} else {
                	row7.gender = tmpContent_tDBInput_6;
                }
            } else {
                row7.gender = null;
            }
		                    }
							if(colQtyInRs_tDBInput_6 < 5) {
								row7.nationality = null;
							} else {
	                         		
           		tmpContent_tDBInput_6 = rs_tDBInput_6.getString(5);
            if(tmpContent_tDBInput_6 != null) {
            	if (talendToDBList_tDBInput_6 .contains(rsmd_tDBInput_6.getColumnTypeName(5).toUpperCase(java.util.Locale.ENGLISH))) {
            		row7.nationality = FormatterUtils.formatUnwithE(tmpContent_tDBInput_6);
            	} else {
                	row7.nationality = tmpContent_tDBInput_6;
                }
            } else {
                row7.nationality = null;
            }
		                    }
							if(colQtyInRs_tDBInput_6 < 6) {
								row7.birthdate = null;
							} else {
										
			row7.birthdate = mssqlGTU_tDBInput_6.getDate(rsmd_tDBInput_6, rs_tDBInput_6, 6);
			
		                    }
							if(colQtyInRs_tDBInput_6 < 7) {
								row7.height_cm = null;
							} else {
		                          
            row7.height_cm = rs_tDBInput_6.getShort(7);
            if(rs_tDBInput_6.wasNull()){
                    row7.height_cm = null;
            }
		                    }
							if(colQtyInRs_tDBInput_6 < 8) {
								row7.playing_hand = null;
							} else {
	                         		
           		tmpContent_tDBInput_6 = rs_tDBInput_6.getString(8);
            if(tmpContent_tDBInput_6 != null) {
            	if (talendToDBList_tDBInput_6 .contains(rsmd_tDBInput_6.getColumnTypeName(8).toUpperCase(java.util.Locale.ENGLISH))) {
            		row7.playing_hand = FormatterUtils.formatUnwithE(tmpContent_tDBInput_6);
            	} else {
                	row7.playing_hand = tmpContent_tDBInput_6;
                }
            } else {
                row7.playing_hand = null;
            }
		                    }
							if(colQtyInRs_tDBInput_6 < 9) {
								row7.court_side = null;
							} else {
	                         		
           		tmpContent_tDBInput_6 = rs_tDBInput_6.getString(9);
            if(tmpContent_tDBInput_6 != null) {
            	if (talendToDBList_tDBInput_6 .contains(rsmd_tDBInput_6.getColumnTypeName(9).toUpperCase(java.util.Locale.ENGLISH))) {
            		row7.court_side = FormatterUtils.formatUnwithE(tmpContent_tDBInput_6);
            	} else {
                	row7.court_side = tmpContent_tDBInput_6;
                }
            } else {
                row7.court_side = null;
            }
		                    }
							if(colQtyInRs_tDBInput_6 < 10) {
								row7.partner_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_6 = rs_tDBInput_6.getString(10);
            if(tmpContent_tDBInput_6 != null) {
            	if (talendToDBList_tDBInput_6 .contains(rsmd_tDBInput_6.getColumnTypeName(10).toUpperCase(java.util.Locale.ENGLISH))) {
            		row7.partner_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_6);
            	} else {
                	row7.partner_name = tmpContent_tDBInput_6;
                }
            } else {
                row7.partner_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_6 < 11) {
								row7.total_points = null;
							} else {
		                          
            row7.total_points = rs_tDBInput_6.getInt(11);
            if(rs_tDBInput_6.wasNull()){
                    row7.total_points = null;
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
	 * [tMap_3 main ] start
	 */

	

	
	
	currentComponent="tMap_3";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row7"
						
						);
					}
					

		
		
		boolean hasCasePrimitiveKeyWithNull_tMap_3 = false;
		

        // ###############################
        // # Input tables (lookups)
		  boolean rejectedInnerJoin_tMap_3 = false;
		  boolean mainRowRejected_tMap_3 = false;
            				    								  
		

				///////////////////////////////////////////////
				// Starting Lookup Table "row8" 
				///////////////////////////////////////////////


				
				
                            
 					    boolean forceLooprow8 = false;
       		  	    	
       		  	    	
 							row8Struct row8ObjectFromLookup = null;
                          
		           		  	if(!rejectedInnerJoin_tMap_3) { // G_TM_M_020

								
								hasCasePrimitiveKeyWithNull_tMap_3 = false;
								
                        		    		    row8HashKey.player_full_name1 = row7.full_name ;
                        		    		
                        		    		    row8HashKey.player_full_name = row7.full_name ;
                        		    		
                        		    		    row8HashKey.full_name = row7.full_name ;
                        		    		
                        		    		    row8HashKey.full_name_1 = row7.full_name ;
                        		    		

								
		                        	row8HashKey.hashCodeDirty = true;
                        		
	  					
	  							
			  					
			  					
	  					
		  							tHash_Lookup_row8.lookup( row8HashKey );

	  							

	  							

 								
		  				
	  								
						
									
  									  		
 								



							} // G_TM_M_020
			           		  	  
							
				           		if(tHash_Lookup_row8 != null && tHash_Lookup_row8.getCount(row8HashKey) > 1) { // G 071
			  							
			  						
									 		
									//System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row8' and it contains more one result from keys :  row8.player_full_name1 = '" + row8HashKey.player_full_name1 + "', row8.player_full_name = '" + row8HashKey.player_full_name + "', row8.full_name = '" + row8HashKey.full_name + "', row8.full_name_1 = '" + row8HashKey.full_name_1 + "'");
								} // G 071
							

							row8Struct row8 = null;
                    		  	 
							   
                    		  	 
	       		  	    	row8Struct fromLookup_row8 = null;
							row8 = row8Default;
										 
							
								 
							
							
								if (tHash_Lookup_row8 !=null && tHash_Lookup_row8.hasNext()) { // G 099
								
							
								
								fromLookup_row8 = tHash_Lookup_row8.next();

							
							
								} // G 099
							
							

							if(fromLookup_row8 != null) {
								row8 = fromLookup_row8;
							}
							
							
							
			  							
								
	                    		  	
		                    
	            	
	            	
	            // ###############################
        { // start of Var scope
        
	        // ###############################
        	// # Vars tables
        
Var__tMap_3__Struct Var = Var__tMap_3;// ###############################
        // ###############################
        // # Output tables

out3 = null;


// # Output table : 'out3'
out3_tmp.player_id = row7.player_id ;
out3_tmp.full_name = row7.full_name ;
out3 = out3_tmp;
// ###############################

} // end of Var scope

rejectedInnerJoin_tMap_3 = false;










 


	tos_count_tMap_3++;

/**
 * [tMap_3 main ] stop
 */
	
	/**
	 * [tMap_3 process_data_begin ] start
	 */

	

	
	
	currentComponent="tMap_3";

	

 



/**
 * [tMap_3 process_data_begin ] stop
 */
// Start of branch "out3"
if(out3 != null) { 



	
	/**
	 * [tAdvancedHash_out3 main ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_out3";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"out3"
						
						);
					}
					


			   
			   

					out3Struct out3_HashRow = new out3Struct();
		   	   	   
				
				out3_HashRow.player_id = out3.player_id;
				
				out3_HashRow.full_name = out3.full_name;
				
			tHash_Lookup_out3.put(out3_HashRow);
			
            




 


	tos_count_tAdvancedHash_out3++;

/**
 * [tAdvancedHash_out3 main ] stop
 */
	
	/**
	 * [tAdvancedHash_out3 process_data_begin ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_out3";

	

 



/**
 * [tAdvancedHash_out3 process_data_begin ] stop
 */
	
	/**
	 * [tAdvancedHash_out3 process_data_end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_out3";

	

 



/**
 * [tAdvancedHash_out3 process_data_end ] stop
 */

} // End of branch "out3"




	
	/**
	 * [tMap_3 process_data_end ] start
	 */

	

	
	
	currentComponent="tMap_3";

	

 



/**
 * [tMap_3 process_data_end ] stop
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
	 * [tMap_3 end ] start
	 */

	

	
	
	currentComponent="tMap_3";

	


// ###############################
// # Lookup hashes releasing
					if(tHash_Lookup_row8 != null) {
						tHash_Lookup_row8.endGet();
					}
					globalMap.remove( "tHash_Lookup_row8" );

					
					
				
// ###############################      





				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row7");
			  	}
			  	
 

ok_Hash.put("tMap_3", true);
end_Hash.put("tMap_3", System.currentTimeMillis());




/**
 * [tMap_3 end ] stop
 */

	
	/**
	 * [tAdvancedHash_out3 end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_out3";

	

tHash_Lookup_out3.endPut();

				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"out3");
			  	}
			  	
 

ok_Hash.put("tAdvancedHash_out3", true);
end_Hash.put("tAdvancedHash_out3", System.currentTimeMillis());




/**
 * [tAdvancedHash_out3 end ] stop
 */






				}//end the resume

				



	
			}catch(java.lang.Exception e){	
				
				TalendException te = new TalendException(e, currentComponent, globalMap);
				
				throw te;
			}catch(java.lang.Error error){	
				
					runStat.stopThreadStat();
				
				throw error;
			}finally{
				
					     			//free memory for "tMap_3"
					     			globalMap.remove("tHash_Lookup_row8"); 
				     			
				try{
					
	
	/**
	 * [tDBInput_6 finally ] start
	 */

	

	
	
	currentComponent="tDBInput_6";

	

 



/**
 * [tDBInput_6 finally ] stop
 */

	
	/**
	 * [tMap_3 finally ] start
	 */

	

	
	
	currentComponent="tMap_3";

	

 



/**
 * [tMap_3 finally ] stop
 */

	
	/**
	 * [tAdvancedHash_out3 finally ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_out3";

	

 



/**
 * [tAdvancedHash_out3 finally ] stop
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
	


public static class row8Struct implements routines.system.IPersistableComparableLookupRow<row8Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_FACT_PLAYERS = new byte[0];
    static byte[] commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[0];
	protected static final int DEFAULT_HASHCODE = 1;
    protected static final int PRIME = 31;
    protected int hashCode = DEFAULT_HASHCODE;
    public boolean hashCodeDirty = true;

    public String loopKey;



	
			    public String player_full_name1;

				public String getPlayer_full_name1 () {
					return this.player_full_name1;
				}
				
			    public String player_full_name;

				public String getPlayer_full_name () {
					return this.player_full_name;
				}
				
			    public String full_name;

				public String getFull_name () {
					return this.full_name;
				}
				
			    public String full_name_1;

				public String getFull_name_1 () {
					return this.full_name_1;
				}
				


	@Override
	public int hashCode() {
		if (this.hashCodeDirty) {
			final int prime = PRIME;
			int result = DEFAULT_HASHCODE;
	
						result = prime * result + ((this.player_full_name1 == null) ? 0 : this.player_full_name1.hashCode());
					
						result = prime * result + ((this.player_full_name == null) ? 0 : this.player_full_name.hashCode());
					
						result = prime * result + ((this.full_name == null) ? 0 : this.full_name.hashCode());
					
						result = prime * result + ((this.full_name_1 == null) ? 0 : this.full_name_1.hashCode());
					
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
		final row8Struct other = (row8Struct) obj;
		
						if (this.player_full_name1 == null) {
							if (other.player_full_name1 != null)
								return false;
						
						} else if (!this.player_full_name1.equals(other.player_full_name1))
						
							return false;
					
						if (this.player_full_name == null) {
							if (other.player_full_name != null)
								return false;
						
						} else if (!this.player_full_name.equals(other.player_full_name))
						
							return false;
					
						if (this.full_name == null) {
							if (other.full_name != null)
								return false;
						
						} else if (!this.full_name.equals(other.full_name))
						
							return false;
					
						if (this.full_name_1 == null) {
							if (other.full_name_1 != null)
								return false;
						
						} else if (!this.full_name_1.equals(other.full_name_1))
						
							return false;
					

		return true;
    }

	public void copyDataTo(row8Struct other) {

		other.player_full_name1 = this.player_full_name1;
	            other.player_full_name = this.player_full_name;
	            other.full_name = this.full_name;
	            other.full_name_1 = this.full_name_1;
	            
	}

	public void copyKeysDataTo(row8Struct other) {

		other.player_full_name1 = this.player_full_name1;
	            	other.player_full_name = this.player_full_name;
	            	other.full_name = this.full_name;
	            	other.full_name_1 = this.full_name_1;
	            	
	}




	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_FACT_PLAYERS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_PLAYERS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_FACT_PLAYERS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_PLAYERS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length, utf8Charset);
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

    public void readKeysData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

        	try {

        		int length = 0;
		
					this.player_full_name1 = readString(dis);
					
					this.player_full_name = readString(dis);
					
					this.full_name = readString(dis);
					
					this.full_name_1 = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

        	try {

        		int length = 0;
		
					this.player_full_name1 = readString(dis);
					
					this.player_full_name = readString(dis);
					
					this.full_name = readString(dis);
					
					this.full_name_1 = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeKeysData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.player_full_name1,dos);
					
					// String
				
						writeString(this.player_full_name,dos);
					
					// String
				
						writeString(this.full_name,dos);
					
					// String
				
						writeString(this.full_name_1,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.player_full_name1,dos);
					
					// String
				
						writeString(this.player_full_name,dos);
					
					// String
				
						writeString(this.full_name,dos);
					
					// String
				
						writeString(this.full_name_1,dos);
					
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
		

		

        }

		
        	finally {}

    }
    
    public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
        try {
			int length = 0;
		

		

        }

		
        	finally {}

    }

    /**
     * Return a byte array which represents Values data.
     */
    public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
        try {

		
        	}
        	finally {}

    }
    
    public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut){
                try {

		
        	}
        	finally {}
    }


    
    public boolean supportMarshaller(){
        return true;
    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("player_full_name1="+player_full_name1);
		sb.append(",player_full_name="+player_full_name);
		sb.append(",full_name="+full_name);
		sb.append(",full_name_1="+full_name_1);
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(row8Struct other) {

		int returnValue = -1;
		
						returnValue = checkNullsAndCompare(this.player_full_name1, other.player_full_name1);
						if(returnValue != 0) {
							return returnValue;
						}

					
						returnValue = checkNullsAndCompare(this.player_full_name, other.player_full_name);
						if(returnValue != 0) {
							return returnValue;
						}

					
						returnValue = checkNullsAndCompare(this.full_name, other.full_name);
						if(returnValue != 0) {
							return returnValue;
						}

					
						returnValue = checkNullsAndCompare(this.full_name_1, other.full_name_1);
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

public static class out1Struct implements routines.system.IPersistableRow<out1Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_FACT_PLAYERS = new byte[0];
    static byte[] commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[0];

	
			    public String player_full_name1;

				public String getPlayer_full_name1 () {
					return this.player_full_name1;
				}
				
			    public String player_full_name;

				public String getPlayer_full_name () {
					return this.player_full_name;
				}
				
			    public String full_name;

				public String getFull_name () {
					return this.full_name;
				}
				
			    public String full_name_1;

				public String getFull_name_1 () {
					return this.full_name_1;
				}
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_FACT_PLAYERS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_PLAYERS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_FACT_PLAYERS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_PLAYERS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

        	try {

        		int length = 0;
		
					this.player_full_name1 = readString(dis);
					
					this.player_full_name = readString(dis);
					
					this.full_name = readString(dis);
					
					this.full_name_1 = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

        	try {

        		int length = 0;
		
					this.player_full_name1 = readString(dis);
					
					this.player_full_name = readString(dis);
					
					this.full_name = readString(dis);
					
					this.full_name_1 = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.player_full_name1,dos);
					
					// String
				
						writeString(this.player_full_name,dos);
					
					// String
				
						writeString(this.full_name,dos);
					
					// String
				
						writeString(this.full_name_1,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.player_full_name1,dos);
					
					// String
				
						writeString(this.player_full_name,dos);
					
					// String
				
						writeString(this.full_name,dos);
					
					// String
				
						writeString(this.full_name_1,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("player_full_name1="+player_full_name1);
		sb.append(",player_full_name="+player_full_name);
		sb.append(",full_name="+full_name);
		sb.append(",full_name_1="+full_name_1);
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(out1Struct other) {

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
    final static byte[] commonByteArrayLock_DW_PADEL_FACT_PLAYERS = new byte[0];
    static byte[] commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[0];

	
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
			if(length > commonByteArray_DW_PADEL_FACT_PLAYERS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_PLAYERS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_FACT_PLAYERS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_PLAYERS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

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

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

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

public static class after_tDBInput_2Struct implements routines.system.IPersistableRow<after_tDBInput_2Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_FACT_PLAYERS = new byte[0];
    static byte[] commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[0];
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
		final after_tDBInput_2Struct other = (after_tDBInput_2Struct) obj;
		
						if (this.match_id != other.match_id)
							return false;
					

		return true;
    }

	public void copyDataTo(after_tDBInput_2Struct other) {

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

	public void copyKeysDataTo(after_tDBInput_2Struct other) {

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
			if(length > commonByteArray_DW_PADEL_FACT_PLAYERS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_PLAYERS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_FACT_PLAYERS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_PLAYERS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

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

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

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
    public int compareTo(after_tDBInput_2Struct other) {

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


		tDBInput_1Process(globalMap);
		tDBInput_3Process(globalMap);
		tDBInput_4Process(globalMap);
		tDBInput_5Process(globalMap);

		row1Struct row1 = new row1Struct();
out1Struct out1 = new out1Struct();
row8Struct row8 = new row8Struct();






	
	/**
	 * [tAdvancedHash_row8 begin ] start
	 */

	

	
		
		ok_Hash.put("tAdvancedHash_row8", false);
		start_Hash.put("tAdvancedHash_row8", System.currentTimeMillis());
		
	
	currentComponent="tAdvancedHash_row8";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row8");
					}
				
		int tos_count_tAdvancedHash_row8 = 0;
		

			   		// connection name:row8
			   		// source node:tUniqRow_1 - inputs:(out1) outputs:(row8,row8) | target node:tAdvancedHash_row8 - inputs:(row8) outputs:()
			   		// linked node: tMap_3 - inputs:(row7,row8) outputs:(out3,out3)
			   
			   		org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row8 = 
			   			org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;
			   			
			   
	   			org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct> tHash_Lookup_row8 =org.talend.designer.components.lookup.memory.AdvancedMemoryLookup.
	   						<row8Struct>getLookup(matchingModeEnum_row8);
	   						   
		   	   	   globalMap.put("tHash_Lookup_row8", tHash_Lookup_row8);
		   	   	   
				
           

 



/**
 * [tAdvancedHash_row8 begin ] stop
 */



	
	/**
	 * [tUniqRow_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tUniqRow_1", false);
		start_Hash.put("tUniqRow_1", System.currentTimeMillis());
		
	
	currentComponent="tUniqRow_1";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"out1");
					}
				
		int tos_count_tUniqRow_1 = 0;
		

int nb_uniques_tUniqRow_1 = 0;
int nb_duplicates_tUniqRow_1 = 0; 

 



/**
 * [tUniqRow_1 begin ] stop
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
	
		org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct> tHash_Lookup_row3 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct>) 
				((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct>) 
					globalMap.get( "tHash_Lookup_row3" ))
					;					
					
	

row3Struct row3HashKey = new row3Struct();
row3Struct row3Default = new row3Struct();
	
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
// ###############################        

// ###############################
// # Vars initialization
class  Var__tMap_1__Struct  {
}
Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
out1Struct out1_tmp = new out1Struct();
// ###############################

        
        



        









 



/**
 * [tMap_1 begin ] stop
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
				
				 
	final String decryptedPassword_tDBInput_2 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:QX9wYuGk/zQI9U4GjK97wKAB2bg33PEhVP3ydXKg9A3BNkY=");
				
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
								row1.match_id = 0;
							} else {
		                          
            row1.match_id = rs_tDBInput_2.getInt(1);
            if(rs_tDBInput_2.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 2) {
								row1.match_number = null;
							} else {
		                          
            row1.match_number = rs_tDBInput_2.getInt(2);
            if(rs_tDBInput_2.wasNull()){
                    row1.match_number = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 3) {
								row1.tournament_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(3);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(3).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.tournament_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row1.tournament_name = tmpContent_tDBInput_2;
                }
            } else {
                row1.tournament_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 4) {
								row1.round = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(4);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(4).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.round = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row1.round = tmpContent_tDBInput_2;
                }
            } else {
                row1.round = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 5) {
								row1.winner = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(5);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(5).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.winner = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row1.winner = tmpContent_tDBInput_2;
                }
            } else {
                row1.winner = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 6) {
								row1.total_points_won_t1 = null;
							} else {
	                         		
            row1.total_points_won_t1 = rs_tDBInput_2.getDouble(6);
            if(rs_tDBInput_2.wasNull()){
                    row1.total_points_won_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 7) {
								row1.total_points_won_t2 = null;
							} else {
	                         		
            row1.total_points_won_t2 = rs_tDBInput_2.getDouble(7);
            if(rs_tDBInput_2.wasNull()){
                    row1.total_points_won_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 8) {
								row1.break_points_converted_t1 = null;
							} else {
	                         		
            row1.break_points_converted_t1 = rs_tDBInput_2.getDouble(8);
            if(rs_tDBInput_2.wasNull()){
                    row1.break_points_converted_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 9) {
								row1.break_points_converted_t2 = null;
							} else {
	                         		
            row1.break_points_converted_t2 = rs_tDBInput_2.getDouble(9);
            if(rs_tDBInput_2.wasNull()){
                    row1.break_points_converted_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 10) {
								row1.longest_streak_t1 = null;
							} else {
		                          
            row1.longest_streak_t1 = rs_tDBInput_2.getInt(10);
            if(rs_tDBInput_2.wasNull()){
                    row1.longest_streak_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 11) {
								row1.longest_streak_t2 = null;
							} else {
		                          
            row1.longest_streak_t2 = rs_tDBInput_2.getInt(11);
            if(rs_tDBInput_2.wasNull()){
                    row1.longest_streak_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 12) {
								row1.aces_t1 = null;
							} else {
		                          
            row1.aces_t1 = rs_tDBInput_2.getInt(12);
            if(rs_tDBInput_2.wasNull()){
                    row1.aces_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 13) {
								row1.aces_t2 = null;
							} else {
		                          
            row1.aces_t2 = rs_tDBInput_2.getInt(13);
            if(rs_tDBInput_2.wasNull()){
                    row1.aces_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 14) {
								row1.double_faults_t1 = null;
							} else {
		                          
            row1.double_faults_t1 = rs_tDBInput_2.getInt(14);
            if(rs_tDBInput_2.wasNull()){
                    row1.double_faults_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 15) {
								row1.double_faults_t2 = null;
							} else {
		                          
            row1.double_faults_t2 = rs_tDBInput_2.getInt(15);
            if(rs_tDBInput_2.wasNull()){
                    row1.double_faults_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 16) {
								row1.won_on_1st_serve_t1 = null;
							} else {
	                         		
            row1.won_on_1st_serve_t1 = rs_tDBInput_2.getDouble(16);
            if(rs_tDBInput_2.wasNull()){
                    row1.won_on_1st_serve_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 17) {
								row1.won_on_1st_serve_t2 = null;
							} else {
	                         		
            row1.won_on_1st_serve_t2 = rs_tDBInput_2.getDouble(17);
            if(rs_tDBInput_2.wasNull()){
                    row1.won_on_1st_serve_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 18) {
								row1.won_on_2nd_serve_t1 = null;
							} else {
	                         		
            row1.won_on_2nd_serve_t1 = rs_tDBInput_2.getDouble(18);
            if(rs_tDBInput_2.wasNull()){
                    row1.won_on_2nd_serve_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 19) {
								row1.won_on_2nd_serve_t2 = null;
							} else {
	                         		
            row1.won_on_2nd_serve_t2 = rs_tDBInput_2.getDouble(19);
            if(rs_tDBInput_2.wasNull()){
                    row1.won_on_2nd_serve_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 20) {
								row1.service_games_t1 = null;
							} else {
		                          
            row1.service_games_t1 = rs_tDBInput_2.getInt(20);
            if(rs_tDBInput_2.wasNull()){
                    row1.service_games_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 21) {
								row1.service_games_t2 = null;
							} else {
		                          
            row1.service_games_t2 = rs_tDBInput_2.getInt(21);
            if(rs_tDBInput_2.wasNull()){
                    row1.service_games_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 22) {
								row1.won_on_1st_return_t1 = null;
							} else {
	                         		
            row1.won_on_1st_return_t1 = rs_tDBInput_2.getDouble(22);
            if(rs_tDBInput_2.wasNull()){
                    row1.won_on_1st_return_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 23) {
								row1.won_on_1st_return_t2 = null;
							} else {
	                         		
            row1.won_on_1st_return_t2 = rs_tDBInput_2.getDouble(23);
            if(rs_tDBInput_2.wasNull()){
                    row1.won_on_1st_return_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 24) {
								row1.won_on_2nd_return_t1 = null;
							} else {
	                         		
            row1.won_on_2nd_return_t1 = rs_tDBInput_2.getDouble(24);
            if(rs_tDBInput_2.wasNull()){
                    row1.won_on_2nd_return_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 25) {
								row1.won_on_2nd_return_t2 = null;
							} else {
	                         		
            row1.won_on_2nd_return_t2 = rs_tDBInput_2.getDouble(25);
            if(rs_tDBInput_2.wasNull()){
                    row1.won_on_2nd_return_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 26) {
								row1.return_games_t1 = null;
							} else {
		                          
            row1.return_games_t1 = rs_tDBInput_2.getInt(26);
            if(rs_tDBInput_2.wasNull()){
                    row1.return_games_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 27) {
								row1.return_games_t2 = null;
							} else {
		                          
            row1.return_games_t2 = rs_tDBInput_2.getInt(27);
            if(rs_tDBInput_2.wasNull()){
                    row1.return_games_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 28) {
								row1.total_won_on_serve_t1 = null;
							} else {
	                         		
            row1.total_won_on_serve_t1 = rs_tDBInput_2.getDouble(28);
            if(rs_tDBInput_2.wasNull()){
                    row1.total_won_on_serve_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 29) {
								row1.total_won_on_serve_t2 = null;
							} else {
	                         		
            row1.total_won_on_serve_t2 = rs_tDBInput_2.getDouble(29);
            if(rs_tDBInput_2.wasNull()){
                    row1.total_won_on_serve_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 30) {
								row1.total_won_on_return_t1 = null;
							} else {
	                         		
            row1.total_won_on_return_t1 = rs_tDBInput_2.getDouble(30);
            if(rs_tDBInput_2.wasNull()){
                    row1.total_won_on_return_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 31) {
								row1.total_won_on_return_t2 = null;
							} else {
	                         		
            row1.total_won_on_return_t2 = rs_tDBInput_2.getDouble(31);
            if(rs_tDBInput_2.wasNull()){
                    row1.total_won_on_return_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 32) {
								row1.date = null;
							} else {
										
			row1.date = mssqlGTU_tDBInput_2.getDate(rsmd_tDBInput_2, rs_tDBInput_2, 32);
			
		                    }
							if(colQtyInRs_tDBInput_2 < 33) {
								row1.team1_player1_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(33);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(33).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.team1_player1_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row1.team1_player1_name = tmpContent_tDBInput_2;
                }
            } else {
                row1.team1_player1_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 34) {
								row1.team1_player2_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(34);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(34).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.team1_player2_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row1.team1_player2_name = tmpContent_tDBInput_2;
                }
            } else {
                row1.team1_player2_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 35) {
								row1.team2_player1_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(35);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(35).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.team2_player1_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row1.team2_player1_name = tmpContent_tDBInput_2;
                }
            } else {
                row1.team2_player1_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 36) {
								row1.team2_player2_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(36);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(36).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.team2_player2_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row1.team2_player2_name = tmpContent_tDBInput_2;
                }
            } else {
                row1.team2_player2_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 37) {
								row1.match_info_added = null;
							} else {
		                          
            row1.match_info_added = rs_tDBInput_2.getInt(37);
            if(rs_tDBInput_2.wasNull()){
                    row1.match_info_added = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 38) {
								row1.t1_s1 = null;
							} else {
		                          
            row1.t1_s1 = rs_tDBInput_2.getInt(38);
            if(rs_tDBInput_2.wasNull()){
                    row1.t1_s1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 39) {
								row1.t2_s1 = null;
							} else {
		                          
            row1.t2_s1 = rs_tDBInput_2.getInt(39);
            if(rs_tDBInput_2.wasNull()){
                    row1.t2_s1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 40) {
								row1.t1_s2 = null;
							} else {
		                          
            row1.t1_s2 = rs_tDBInput_2.getInt(40);
            if(rs_tDBInput_2.wasNull()){
                    row1.t1_s2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 41) {
								row1.t2_s2 = null;
							} else {
		                          
            row1.t2_s2 = rs_tDBInput_2.getInt(41);
            if(rs_tDBInput_2.wasNull()){
                    row1.t2_s2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 42) {
								row1.t1_s3 = null;
							} else {
		                          
            row1.t1_s3 = rs_tDBInput_2.getInt(42);
            if(rs_tDBInput_2.wasNull()){
                    row1.t1_s3 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 43) {
								row1.t2_s3 = null;
							} else {
		                          
            row1.t2_s3 = rs_tDBInput_2.getInt(43);
            if(rs_tDBInput_2.wasNull()){
                    row1.t2_s3 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 44) {
								row1.views = null;
							} else {
		                          
            row1.views = rs_tDBInput_2.getInt(44);
            if(rs_tDBInput_2.wasNull()){
                    row1.views = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 45) {
								row1.interactions = null;
							} else {
		                          
            row1.interactions = rs_tDBInput_2.getInt(45);
            if(rs_tDBInput_2.wasNull()){
                    row1.interactions = null;
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
								
                        		    		    row2HashKey.full_name = row1.team1_player1_name ;
                        		    		

								
		                        	row2HashKey.hashCodeDirty = true;
                        		
	  					
	  							
			  					
			  					
	  					
		  							tHash_Lookup_row2.lookup( row2HashKey );

	  							

	  							

 								
								  
								  if(!tHash_Lookup_row2.hasNext()) { // G_TM_M_090

  								
		  				
	  								
			  							rejectedInnerJoin_tMap_1 = true;
	  								
						
									
  									  		
 								
								  
								  } // G_TM_M_090

  								



							} // G_TM_M_020
			           		  	  
							
				           		if(tHash_Lookup_row2 != null && tHash_Lookup_row2.getCount(row2HashKey) > 1) { // G 071
			  							
			  						
									 		
									//System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row2' and it contains more one result from keys :  row2.full_name = '" + row2HashKey.full_name + "'");
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
				// Starting Lookup Table "row3" 
				///////////////////////////////////////////////


				
				
                            
 					    boolean forceLooprow3 = false;
       		  	    	
       		  	    	
 							row3Struct row3ObjectFromLookup = null;
                          
		           		  	if(!rejectedInnerJoin_tMap_1) { // G_TM_M_020

								
								hasCasePrimitiveKeyWithNull_tMap_1 = false;
								
                        		    		    row3HashKey.full_name = row1.team1_player2_name ;
                        		    		

								
		                        	row3HashKey.hashCodeDirty = true;
                        		
	  					
	  							
			  					
			  					
	  					
		  							tHash_Lookup_row3.lookup( row3HashKey );

	  							

	  							

 								
								  
								  if(!tHash_Lookup_row3.hasNext()) { // G_TM_M_090

  								
		  				
	  								
			  							rejectedInnerJoin_tMap_1 = true;
	  								
						
									
  									  		
 								
								  
								  } // G_TM_M_090

  								



							} // G_TM_M_020
			           		  	  
							
				           		if(tHash_Lookup_row3 != null && tHash_Lookup_row3.getCount(row3HashKey) > 1) { // G 071
			  							
			  						
									 		
									//System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row3' and it contains more one result from keys :  row3.full_name = '" + row3HashKey.full_name + "'");
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
							
							
							
			  							
								
	                    		  	
		                    
	            	
	           	
	            	
	            	
	            

				///////////////////////////////////////////////
				// Starting Lookup Table "row4" 
				///////////////////////////////////////////////


				
				
                            
 					    boolean forceLooprow4 = false;
       		  	    	
       		  	    	
 							row4Struct row4ObjectFromLookup = null;
                          
		           		  	if(!rejectedInnerJoin_tMap_1) { // G_TM_M_020

								
								hasCasePrimitiveKeyWithNull_tMap_1 = false;
								
                        		    		    row4HashKey.full_name = row1.team2_player1_name ;
                        		    		

								
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
								
                        		    		    row5HashKey.full_name = row1.team2_player2_name ;
                        		    		

								
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
							
							
							
			  							
								
	                    		  	
		                    
	            	
	            	
	            // ###############################
        { // start of Var scope
        
	        // ###############################
        	// # Vars tables
        
Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
        // ###############################
        // # Output tables

out1 = null;

if(!rejectedInnerJoin_tMap_1 ) {

// # Output table : 'out1'
out1_tmp.player_full_name1 = row2.full_name ;
out1_tmp.player_full_name = row3.full_name ;
out1_tmp.full_name = row4.full_name ;
out1_tmp.full_name_1 = row5.full_name ;
out1 = out1_tmp;
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
// Start of branch "out1"
if(out1 != null) { 



	
	/**
	 * [tUniqRow_1 main ] start
	 */

	

	
	
	currentComponent="tUniqRow_1";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"out1"
						
						);
					}
					
row8.player_full_name1 = out1.player_full_name1;			row8.player_full_name = out1.player_full_name;			row8.full_name = out1.full_name;			row8.full_name_1 = out1.full_name_1;			row8.player_full_name1 = out1.player_full_name1;			row8.player_full_name = out1.player_full_name;			row8.full_name = out1.full_name;			row8.full_name_1 = out1.full_name_1;			

 


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
// Start of branch "row8"
if(row8 != null) { 



	
	/**
	 * [tAdvancedHash_row8 main ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row8";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row8"
						
						);
					}
					


			   
			   

					row8Struct row8_HashRow = new row8Struct();
		   	   	   
				
				row8_HashRow.player_full_name1 = row8.player_full_name1;
				
				row8_HashRow.player_full_name = row8.player_full_name;
				
				row8_HashRow.full_name = row8.full_name;
				
				row8_HashRow.full_name_1 = row8.full_name_1;
				
			tHash_Lookup_row8.put(row8_HashRow);
			
            




 


	tos_count_tAdvancedHash_row8++;

/**
 * [tAdvancedHash_row8 main ] stop
 */
	
	/**
	 * [tAdvancedHash_row8 process_data_begin ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row8";

	

 



/**
 * [tAdvancedHash_row8 process_data_begin ] stop
 */
	
	/**
	 * [tAdvancedHash_row8 process_data_end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row8";

	

 



/**
 * [tAdvancedHash_row8 process_data_end ] stop
 */

} // End of branch "row8"




	
	/**
	 * [tUniqRow_1 process_data_end ] start
	 */

	

	
	
	currentComponent="tUniqRow_1";

	

 



/**
 * [tUniqRow_1 process_data_end ] stop
 */

} // End of branch "out1"




	
	/**
	 * [tMap_1 process_data_end ] start
	 */

	

	
	
	currentComponent="tMap_1";

	

 



/**
 * [tMap_1 process_data_end ] stop
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
	 * [tMap_1 end ] start
	 */

	

	
	
	currentComponent="tMap_1";

	


// ###############################
// # Lookup hashes releasing
					if(tHash_Lookup_row2 != null) {
						tHash_Lookup_row2.endGet();
					}
					globalMap.remove( "tHash_Lookup_row2" );

					
					
				
					if(tHash_Lookup_row3 != null) {
						tHash_Lookup_row3.endGet();
					}
					globalMap.remove( "tHash_Lookup_row3" );

					
					
				
					if(tHash_Lookup_row4 != null) {
						tHash_Lookup_row4.endGet();
					}
					globalMap.remove( "tHash_Lookup_row4" );

					
					
				
					if(tHash_Lookup_row5 != null) {
						tHash_Lookup_row5.endGet();
					}
					globalMap.remove( "tHash_Lookup_row5" );

					
					
				
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
	 * [tUniqRow_1 end ] start
	 */

	

	
	
	currentComponent="tUniqRow_1";

	

globalMap.put("tUniqRow_1_NB_UNIQUES",nb_uniques_tUniqRow_1);
globalMap.put("tUniqRow_1_NB_DUPLICATES",nb_duplicates_tUniqRow_1);

				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"out1");
			  	}
			  	
 

ok_Hash.put("tUniqRow_1", true);
end_Hash.put("tUniqRow_1", System.currentTimeMillis());




/**
 * [tUniqRow_1 end ] stop
 */

	
	/**
	 * [tAdvancedHash_row8 end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row8";

	

tHash_Lookup_row8.endPut();

				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row8");
			  	}
			  	
 

ok_Hash.put("tAdvancedHash_row8", true);
end_Hash.put("tAdvancedHash_row8", System.currentTimeMillis());




/**
 * [tAdvancedHash_row8 end ] stop
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
					     			globalMap.remove("tHash_Lookup_row3"); 
				     			
					     			//free memory for "tMap_1"
					     			globalMap.remove("tHash_Lookup_row4"); 
				     			
					     			//free memory for "tMap_1"
					     			globalMap.remove("tHash_Lookup_row5"); 
				     			
				try{
					
	
	/**
	 * [tDBInput_2 finally ] start
	 */

	

	
	
	currentComponent="tDBInput_2";

	

 



/**
 * [tDBInput_2 finally ] stop
 */

	
	/**
	 * [tMap_1 finally ] start
	 */

	

	
	
	currentComponent="tMap_1";

	

 



/**
 * [tMap_1 finally ] stop
 */

	
	/**
	 * [tUniqRow_1 finally ] start
	 */

	

	
	
	currentComponent="tUniqRow_1";

	

 



/**
 * [tUniqRow_1 finally ] stop
 */

	
	/**
	 * [tAdvancedHash_row8 finally ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row8";

	

 



/**
 * [tAdvancedHash_row8 finally ] stop
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
	


public static class row2Struct implements routines.system.IPersistableComparableLookupRow<row2Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_FACT_PLAYERS = new byte[0];
    static byte[] commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[0];
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
		final row2Struct other = (row2Struct) obj;
		
						if (this.full_name == null) {
							if (other.full_name != null)
								return false;
						
						} else if (!this.full_name.equals(other.full_name))
						
							return false;
					

		return true;
    }

	public void copyDataTo(row2Struct other) {

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

	public void copyKeysDataTo(row2Struct other) {

		other.full_name = this.full_name;
	            	
	}




	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_FACT_PLAYERS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_PLAYERS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_FACT_PLAYERS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_PLAYERS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

        	try {

        		int length = 0;
		
					this.full_name = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

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
    public int compareTo(row2Struct other) {

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
			   		// source node:tDBInput_1 - inputs:(after_tDBInput_2) outputs:(row2,row2) | target node:tAdvancedHash_row2 - inputs:(row2) outputs:()
			   		// linked node: tMap_1 - inputs:(row1,row2,row3,row4,row5) outputs:(out1)
			   
			   		org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row2 = 
			   			org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;
			   			
			   
	   			org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct> tHash_Lookup_row2 =org.talend.designer.components.lookup.memory.AdvancedMemoryLookup.
	   						<row2Struct>getLookup(matchingModeEnum_row2);
	   						   
		   	   	   globalMap.put("tHash_Lookup_row2", tHash_Lookup_row2);
		   	   	   
				
           

 



/**
 * [tAdvancedHash_row2 begin ] stop
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
				
				 
	final String decryptedPassword_tDBInput_1 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:yN1HXnCmadE1uRcKrK/EORiNHXGNJow8Z5yQwYiNqQWcq98=");
				
				String dbPwd_tDBInput_1 = decryptedPassword_tDBInput_1;
				
		    String port_tDBInput_1 = "1433";
		    String dbname_tDBInput_1 = "DW_padel" ;
			String url_tDBInput_1 = "jdbc:jtds:sqlserver://" + "DESKTOP-QJ70MNR" ;
		    if (!"".equals(port_tDBInput_1)) {
		    	url_tDBInput_1 += ":" + "1433";
		    }
		    if (!"".equals(dbname_tDBInput_1)) {
				url_tDBInput_1 += "//" + "DW_padel"; 
		    }
		    url_tDBInput_1 += ";appName=" + projectName + ";" + "";
		    String dbschema_tDBInput_1 = "";
				
				conn_tDBInput_1 = java.sql.DriverManager.getConnection(url_tDBInput_1,dbUser_tDBInput_1,dbPwd_tDBInput_1);
		        
		    
			java.sql.Statement stmt_tDBInput_1 = conn_tDBInput_1.createStatement();

		    String dbquery_tDBInput_1 = "SELECT dim_player.player_id,\n		dim_player.full_name,\n		dim_player.ranking,\n		dim_player.gender,\n		dim_player.nationalit"
+"y,\n		dim_player.birthdate,\n		dim_player.height_cm,\n		dim_player.playing_hand,\n		dim_player.court_side,\n		dim_player.part"
+"ner_name,\n		dim_player.total_points\nFROM	dim_player";
		    

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
								row2.player_id = 0;
							} else {
		                          
            row2.player_id = rs_tDBInput_1.getInt(1);
            if(rs_tDBInput_1.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 2) {
								row2.full_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(2);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
            		row2.full_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row2.full_name = tmpContent_tDBInput_1;
                }
            } else {
                row2.full_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 3) {
								row2.ranking = null;
							} else {
		                          
            row2.ranking = rs_tDBInput_1.getInt(3);
            if(rs_tDBInput_1.wasNull()){
                    row2.ranking = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 4) {
								row2.gender = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(4);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(4).toUpperCase(java.util.Locale.ENGLISH))) {
            		row2.gender = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row2.gender = tmpContent_tDBInput_1;
                }
            } else {
                row2.gender = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 5) {
								row2.nationality = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(5);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(5).toUpperCase(java.util.Locale.ENGLISH))) {
            		row2.nationality = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row2.nationality = tmpContent_tDBInput_1;
                }
            } else {
                row2.nationality = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 6) {
								row2.birthdate = null;
							} else {
										
			row2.birthdate = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 6);
			
		                    }
							if(colQtyInRs_tDBInput_1 < 7) {
								row2.height_cm = null;
							} else {
		                          
            row2.height_cm = rs_tDBInput_1.getShort(7);
            if(rs_tDBInput_1.wasNull()){
                    row2.height_cm = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 8) {
								row2.playing_hand = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(8);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(8).toUpperCase(java.util.Locale.ENGLISH))) {
            		row2.playing_hand = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row2.playing_hand = tmpContent_tDBInput_1;
                }
            } else {
                row2.playing_hand = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 9) {
								row2.court_side = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(9);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(9).toUpperCase(java.util.Locale.ENGLISH))) {
            		row2.court_side = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row2.court_side = tmpContent_tDBInput_1;
                }
            } else {
                row2.court_side = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 10) {
								row2.partner_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(10);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(10).toUpperCase(java.util.Locale.ENGLISH))) {
            		row2.partner_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row2.partner_name = tmpContent_tDBInput_1;
                }
            } else {
                row2.partner_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 11) {
								row2.total_points = null;
							} else {
		                          
            row2.total_points = rs_tDBInput_1.getInt(11);
            if(rs_tDBInput_1.wasNull()){
                    row2.total_points = null;
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
	 * [tAdvancedHash_row2 main ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row2";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row2"
						
						);
					}
					


			   
			   

					row2Struct row2_HashRow = new row2Struct();
		   	   	   
				
				row2_HashRow.player_id = row2.player_id;
				
				row2_HashRow.full_name = row2.full_name;
				
				row2_HashRow.ranking = row2.ranking;
				
				row2_HashRow.gender = row2.gender;
				
				row2_HashRow.nationality = row2.nationality;
				
				row2_HashRow.birthdate = row2.birthdate;
				
				row2_HashRow.height_cm = row2.height_cm;
				
				row2_HashRow.playing_hand = row2.playing_hand;
				
				row2_HashRow.court_side = row2.court_side;
				
				row2_HashRow.partner_name = row2.partner_name;
				
				row2_HashRow.total_points = row2.total_points;
				
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
	 * [tDBInput_1 finally ] start
	 */

	

	
	
	currentComponent="tDBInput_1";

	

 



/**
 * [tDBInput_1 finally ] stop
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
		

		globalMap.put("tDBInput_1_SUBPROCESS_STATE", 1);
	}
	


public static class row3Struct implements routines.system.IPersistableComparableLookupRow<row3Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_FACT_PLAYERS = new byte[0];
    static byte[] commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[0];
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
		final row3Struct other = (row3Struct) obj;
		
						if (this.full_name == null) {
							if (other.full_name != null)
								return false;
						
						} else if (!this.full_name.equals(other.full_name))
						
							return false;
					

		return true;
    }

	public void copyDataTo(row3Struct other) {

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

	public void copyKeysDataTo(row3Struct other) {

		other.full_name = this.full_name;
	            	
	}




	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_FACT_PLAYERS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_PLAYERS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_FACT_PLAYERS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_PLAYERS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

        	try {

        		int length = 0;
		
					this.full_name = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

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
    public int compareTo(row3Struct other) {

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
			   		// source node:tDBInput_3 - inputs:(after_tDBInput_2) outputs:(row3,row3) | target node:tAdvancedHash_row3 - inputs:(row3) outputs:()
			   		// linked node: tMap_1 - inputs:(row1,row2,row3,row4,row5) outputs:(out1)
			   
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
				
				 
	final String decryptedPassword_tDBInput_3 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:9FxmvEr7Z5tp/bMQIfrboVM/juljDvtDZJo5+f9hwbEb1i8=");
				
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

		    String dbquery_tDBInput_3 = "SELECT dim_player.player_id,\n		dim_player.full_name,\n		dim_player.ranking,\n		dim_player.gender,\n		dim_player.nationalit"
+"y,\n		dim_player.birthdate,\n		dim_player.height_cm,\n		dim_player.playing_hand,\n		dim_player.court_side,\n		dim_player.part"
+"ner_name,\n		dim_player.total_points\nFROM	dim_player";
		    

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
								row3.player_id = 0;
							} else {
		                          
            row3.player_id = rs_tDBInput_3.getInt(1);
            if(rs_tDBInput_3.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
							if(colQtyInRs_tDBInput_3 < 2) {
								row3.full_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_3 = rs_tDBInput_3.getString(2);
            if(tmpContent_tDBInput_3 != null) {
            	if (talendToDBList_tDBInput_3 .contains(rsmd_tDBInput_3.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.full_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_3);
            	} else {
                	row3.full_name = tmpContent_tDBInput_3;
                }
            } else {
                row3.full_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_3 < 3) {
								row3.ranking = null;
							} else {
		                          
            row3.ranking = rs_tDBInput_3.getInt(3);
            if(rs_tDBInput_3.wasNull()){
                    row3.ranking = null;
            }
		                    }
							if(colQtyInRs_tDBInput_3 < 4) {
								row3.gender = null;
							} else {
	                         		
           		tmpContent_tDBInput_3 = rs_tDBInput_3.getString(4);
            if(tmpContent_tDBInput_3 != null) {
            	if (talendToDBList_tDBInput_3 .contains(rsmd_tDBInput_3.getColumnTypeName(4).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.gender = FormatterUtils.formatUnwithE(tmpContent_tDBInput_3);
            	} else {
                	row3.gender = tmpContent_tDBInput_3;
                }
            } else {
                row3.gender = null;
            }
		                    }
							if(colQtyInRs_tDBInput_3 < 5) {
								row3.nationality = null;
							} else {
	                         		
           		tmpContent_tDBInput_3 = rs_tDBInput_3.getString(5);
            if(tmpContent_tDBInput_3 != null) {
            	if (talendToDBList_tDBInput_3 .contains(rsmd_tDBInput_3.getColumnTypeName(5).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.nationality = FormatterUtils.formatUnwithE(tmpContent_tDBInput_3);
            	} else {
                	row3.nationality = tmpContent_tDBInput_3;
                }
            } else {
                row3.nationality = null;
            }
		                    }
							if(colQtyInRs_tDBInput_3 < 6) {
								row3.birthdate = null;
							} else {
										
			row3.birthdate = mssqlGTU_tDBInput_3.getDate(rsmd_tDBInput_3, rs_tDBInput_3, 6);
			
		                    }
							if(colQtyInRs_tDBInput_3 < 7) {
								row3.height_cm = null;
							} else {
		                          
            row3.height_cm = rs_tDBInput_3.getShort(7);
            if(rs_tDBInput_3.wasNull()){
                    row3.height_cm = null;
            }
		                    }
							if(colQtyInRs_tDBInput_3 < 8) {
								row3.playing_hand = null;
							} else {
	                         		
           		tmpContent_tDBInput_3 = rs_tDBInput_3.getString(8);
            if(tmpContent_tDBInput_3 != null) {
            	if (talendToDBList_tDBInput_3 .contains(rsmd_tDBInput_3.getColumnTypeName(8).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.playing_hand = FormatterUtils.formatUnwithE(tmpContent_tDBInput_3);
            	} else {
                	row3.playing_hand = tmpContent_tDBInput_3;
                }
            } else {
                row3.playing_hand = null;
            }
		                    }
							if(colQtyInRs_tDBInput_3 < 9) {
								row3.court_side = null;
							} else {
	                         		
           		tmpContent_tDBInput_3 = rs_tDBInput_3.getString(9);
            if(tmpContent_tDBInput_3 != null) {
            	if (talendToDBList_tDBInput_3 .contains(rsmd_tDBInput_3.getColumnTypeName(9).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.court_side = FormatterUtils.formatUnwithE(tmpContent_tDBInput_3);
            	} else {
                	row3.court_side = tmpContent_tDBInput_3;
                }
            } else {
                row3.court_side = null;
            }
		                    }
							if(colQtyInRs_tDBInput_3 < 10) {
								row3.partner_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_3 = rs_tDBInput_3.getString(10);
            if(tmpContent_tDBInput_3 != null) {
            	if (talendToDBList_tDBInput_3 .contains(rsmd_tDBInput_3.getColumnTypeName(10).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.partner_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_3);
            	} else {
                	row3.partner_name = tmpContent_tDBInput_3;
                }
            } else {
                row3.partner_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_3 < 11) {
								row3.total_points = null;
							} else {
		                          
            row3.total_points = rs_tDBInput_3.getInt(11);
            if(rs_tDBInput_3.wasNull()){
                    row3.total_points = null;
            }
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
		   	   	   
				
				row3_HashRow.player_id = row3.player_id;
				
				row3_HashRow.full_name = row3.full_name;
				
				row3_HashRow.ranking = row3.ranking;
				
				row3_HashRow.gender = row3.gender;
				
				row3_HashRow.nationality = row3.nationality;
				
				row3_HashRow.birthdate = row3.birthdate;
				
				row3_HashRow.height_cm = row3.height_cm;
				
				row3_HashRow.playing_hand = row3.playing_hand;
				
				row3_HashRow.court_side = row3.court_side;
				
				row3_HashRow.partner_name = row3.partner_name;
				
				row3_HashRow.total_points = row3.total_points;
				
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
	


public static class row4Struct implements routines.system.IPersistableComparableLookupRow<row4Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_FACT_PLAYERS = new byte[0];
    static byte[] commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[0];
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
			if(length > commonByteArray_DW_PADEL_FACT_PLAYERS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_PLAYERS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_FACT_PLAYERS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_PLAYERS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

        	try {

        		int length = 0;
		
					this.full_name = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

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
			   		// source node:tDBInput_4 - inputs:(after_tDBInput_2) outputs:(row4,row4) | target node:tAdvancedHash_row4 - inputs:(row4) outputs:()
			   		// linked node: tMap_1 - inputs:(row1,row2,row3,row4,row5) outputs:(out1)
			   
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
				
				 
	final String decryptedPassword_tDBInput_4 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:hOup8L66BZGuRBbXbRZSnskQPBTsPVGvR6tpFAXbNeEq70E=");
				
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
    final static byte[] commonByteArrayLock_DW_PADEL_FACT_PLAYERS = new byte[0];
    static byte[] commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[0];
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
			if(length > commonByteArray_DW_PADEL_FACT_PLAYERS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_PLAYERS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_FACT_PLAYERS.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_FACT_PLAYERS.length == 0) {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_FACT_PLAYERS, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

        	try {

        		int length = 0;
		
					this.full_name = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

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
			   		// source node:tDBInput_5 - inputs:(after_tDBInput_2) outputs:(row5,row5) | target node:tAdvancedHash_row5 - inputs:(row5) outputs:()
			   		// linked node: tMap_1 - inputs:(row1,row2,row3,row4,row5) outputs:(out1)
			   
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
				
				 
	final String decryptedPassword_tDBInput_5 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:FpoCqMNdC/6FAW6RtO2jW/3jVtoQwrfXv6iqF1xmgMNvy20=");
				
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
	


public static class row9Struct implements routines.system.IPersistableComparableLookupRow<row9Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_FACT_PLAYERS = new byte[0];
    static byte[] commonByteArray_DW_PADEL_FACT_PLAYERS = new byte[0];
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
		final row9Struct other = (row9Struct) obj;
		
						if (this.full_date == null) {
							if (other.full_date != null)
								return false;
						
						} else if (!this.full_date.equals(other.full_date))
						
							return false;
					

		return true;
    }

	public void copyDataTo(row9Struct other) {

		other.date_key = this.date_key;
	            other.full_date = this.full_date;
	            other.annee = this.annee;
	            other.mois = this.mois;
	            other.nom_mois = this.nom_mois;
	            other.jour_semaine = this.jour_semaine;
	            other.trimestre = this.trimestre;
	            
	}

	public void copyKeysDataTo(row9Struct other) {

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

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

        	try {

        		int length = 0;
		
					this.full_date = readDate(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_FACT_PLAYERS) {

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
    public int compareTo(row9Struct other) {

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



		row9Struct row9 = new row9Struct();




	
	/**
	 * [tAdvancedHash_row9 begin ] start
	 */

	

	
		
		ok_Hash.put("tAdvancedHash_row9", false);
		start_Hash.put("tAdvancedHash_row9", System.currentTimeMillis());
		
	
	currentComponent="tAdvancedHash_row9";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row9");
					}
				
		int tos_count_tAdvancedHash_row9 = 0;
		

			   		// connection name:row9
			   		// source node:tDBInput_7 - inputs:(after_tFileInputExcel_1) outputs:(row9,row9) | target node:tAdvancedHash_row9 - inputs:(row9) outputs:()
			   		// linked node: tMap_2 - inputs:(row6,out3,row9) outputs:(out2)
			   
			   		org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row9 = 
			   			org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;
			   			
			   
	   			org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row9Struct> tHash_Lookup_row9 =org.talend.designer.components.lookup.memory.AdvancedMemoryLookup.
	   						<row9Struct>getLookup(matchingModeEnum_row9);
	   						   
		   	   	   globalMap.put("tHash_Lookup_row9", tHash_Lookup_row9);
		   	   	   
				
           

 



/**
 * [tAdvancedHash_row9 begin ] stop
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
				
				 
	final String decryptedPassword_tDBInput_7 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:rw+k4TQB7F8L8XeSDh2nRek2vc2uxBAeSmIKhCj+WDRlUIY=");
				
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

		    String dbquery_tDBInput_7 = "SELECT dim_date.date_key,\n		dim_date.full_date,\n		dim_date.annee,\n		dim_date.mois,\n		dim_date.nom_mois,\n		dim_date.jour"
+"_semaine,\n		dim_date.trimestre\nFROM	dim_date";
		    

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
								row9.date_key = null;
							} else {
		                          
            row9.date_key = rs_tDBInput_7.getInt(1);
            if(rs_tDBInput_7.wasNull()){
                    row9.date_key = null;
            }
		                    }
							if(colQtyInRs_tDBInput_7 < 2) {
								row9.full_date = null;
							} else {
										
			row9.full_date = mssqlGTU_tDBInput_7.getDate(rsmd_tDBInput_7, rs_tDBInput_7, 2);
			
		                    }
							if(colQtyInRs_tDBInput_7 < 3) {
								row9.annee = null;
							} else {
		                          
            row9.annee = rs_tDBInput_7.getInt(3);
            if(rs_tDBInput_7.wasNull()){
                    row9.annee = null;
            }
		                    }
							if(colQtyInRs_tDBInput_7 < 4) {
								row9.mois = null;
							} else {
		                          
            row9.mois = rs_tDBInput_7.getInt(4);
            if(rs_tDBInput_7.wasNull()){
                    row9.mois = null;
            }
		                    }
							if(colQtyInRs_tDBInput_7 < 5) {
								row9.nom_mois = null;
							} else {
	                         		
           		tmpContent_tDBInput_7 = rs_tDBInput_7.getString(5);
            if(tmpContent_tDBInput_7 != null) {
            	if (talendToDBList_tDBInput_7 .contains(rsmd_tDBInput_7.getColumnTypeName(5).toUpperCase(java.util.Locale.ENGLISH))) {
            		row9.nom_mois = FormatterUtils.formatUnwithE(tmpContent_tDBInput_7);
            	} else {
                	row9.nom_mois = tmpContent_tDBInput_7;
                }
            } else {
                row9.nom_mois = null;
            }
		                    }
							if(colQtyInRs_tDBInput_7 < 6) {
								row9.jour_semaine = null;
							} else {
	                         		
           		tmpContent_tDBInput_7 = rs_tDBInput_7.getString(6);
            if(tmpContent_tDBInput_7 != null) {
            	if (talendToDBList_tDBInput_7 .contains(rsmd_tDBInput_7.getColumnTypeName(6).toUpperCase(java.util.Locale.ENGLISH))) {
            		row9.jour_semaine = FormatterUtils.formatUnwithE(tmpContent_tDBInput_7);
            	} else {
                	row9.jour_semaine = tmpContent_tDBInput_7;
                }
            } else {
                row9.jour_semaine = null;
            }
		                    }
							if(colQtyInRs_tDBInput_7 < 7) {
								row9.trimestre = null;
							} else {
		                          
            row9.trimestre = rs_tDBInput_7.getInt(7);
            if(rs_tDBInput_7.wasNull()){
                    row9.trimestre = null;
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
	 * [tAdvancedHash_row9 main ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row9";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row9"
						
						);
					}
					


			   
			   

					row9Struct row9_HashRow = new row9Struct();
		   	   	   
				
				row9_HashRow.date_key = row9.date_key;
				
				row9_HashRow.full_date = row9.full_date;
				
				row9_HashRow.annee = row9.annee;
				
				row9_HashRow.mois = row9.mois;
				
				row9_HashRow.nom_mois = row9.nom_mois;
				
				row9_HashRow.jour_semaine = row9.jour_semaine;
				
				row9_HashRow.trimestre = row9.trimestre;
				
			tHash_Lookup_row9.put(row9_HashRow);
			
            




 


	tos_count_tAdvancedHash_row9++;

/**
 * [tAdvancedHash_row9 main ] stop
 */
	
	/**
	 * [tAdvancedHash_row9 process_data_begin ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row9";

	

 



/**
 * [tAdvancedHash_row9 process_data_begin ] stop
 */
	
	/**
	 * [tAdvancedHash_row9 process_data_end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row9";

	

 



/**
 * [tAdvancedHash_row9 process_data_end ] stop
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
	 * [tAdvancedHash_row9 end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row9";

	

tHash_Lookup_row9.endPut();

				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row9");
			  	}
			  	
 

ok_Hash.put("tAdvancedHash_row9", true);
end_Hash.put("tAdvancedHash_row9", System.currentTimeMillis());




/**
 * [tAdvancedHash_row9 end ] stop
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
	 * [tAdvancedHash_row9 finally ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row9";

	

 



/**
 * [tAdvancedHash_row9 finally ] stop
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
        final FACT_PLAYERS FACT_PLAYERSClass = new FACT_PLAYERS();

        int exitCode = FACT_PLAYERSClass.runJobInTOS(args);

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
            java.io.InputStream inContext = FACT_PLAYERS.class.getClassLoader().getResourceAsStream("dw_padel/fact_players_0_1/contexts/" + contextStr + ".properties");
            if (inContext == null) {
                inContext = FACT_PLAYERS.class.getClassLoader().getResourceAsStream("config/contexts/" + contextStr + ".properties");
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
            System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : FACT_PLAYERS");
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
 *     530615 characters generated by Talend Open Studio for Data Integration 
 *     on the 29 avril 2026 à 03:24:44 WAT
 ************************************************************************************************/