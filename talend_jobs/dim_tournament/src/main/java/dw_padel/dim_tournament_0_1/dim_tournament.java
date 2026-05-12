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


package dw_padel.dim_tournament_0_1;

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
 * Job: dim_tournament Purpose: <br>
 * Description:  <br>
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status 
 */
public class dim_tournament implements TalendJob {

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
	private final String jobName = "dim_tournament";
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
				dim_tournament.this.exception = e;
			}
		}
		if (!(e instanceof TalendException)) {
		try {
			for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
				if (m.getName().compareTo(currentComponent + "_error") == 0) {
					m.invoke(dim_tournament.this, new Object[] { e , currentComponent, globalMap});
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
			
			public void tAdvancedHash_row2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_1_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
	






public static class DIMStruct implements routines.system.IPersistableRow<DIMStruct> {
    final static byte[] commonByteArrayLock_DW_PADEL_dim_tournament = new byte[0];
    static byte[] commonByteArray_DW_PADEL_dim_tournament = new byte[0];
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
	
							result = prime * result + (int) this.tournament_id;
						
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
		final DIMStruct other = (DIMStruct) obj;
		
						if (this.tournament_id != other.tournament_id)
							return false;
					

		return true;
    }

	public void copyDataTo(DIMStruct other) {

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

	public void copyKeysDataTo(DIMStruct other) {

		other.tournament_id = this.tournament_id;
	            	
	}




	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_dim_tournament.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_tournament.length == 0) {
   					commonByteArray_DW_PADEL_dim_tournament = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_tournament = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_dim_tournament, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_tournament, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_dim_tournament.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_tournament.length == 0) {
   					commonByteArray_DW_PADEL_dim_tournament = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_tournament = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_dim_tournament, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_tournament, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_dim_tournament) {

        	try {

        		int length = 0;
		
			        this.tournament_id = dis.readInt();
					
					this.tournament_name = readString(dis);
					
					this.location = readString(dis);
					
						this.prize_money = readInteger(dis);
					
					this.balls_brand = readString(dis);
					
					this.venue_type = readString(dis);
					
					this.court_manufacturer = readString(dis);
					
					this.turf_type = readString(dis);
					
					this.date = readDate(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_dim_tournament) {

        	try {

        		int length = 0;
		
			        this.tournament_id = dis.readInt();
					
					this.tournament_name = readString(dis);
					
					this.location = readString(dis);
					
						this.prize_money = readInteger(dis);
					
					this.balls_brand = readString(dis);
					
					this.venue_type = readString(dis);
					
					this.court_manufacturer = readString(dis);
					
					this.turf_type = readString(dis);
					
					this.date = readDate(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// int
				
		            	dos.writeInt(this.tournament_id);
					
					// String
				
						writeString(this.tournament_name,dos);
					
					// String
				
						writeString(this.location,dos);
					
					// Integer
				
						writeInteger(this.prize_money,dos);
					
					// String
				
						writeString(this.balls_brand,dos);
					
					// String
				
						writeString(this.venue_type,dos);
					
					// String
				
						writeString(this.court_manufacturer,dos);
					
					// String
				
						writeString(this.turf_type,dos);
					
					// java.util.Date
				
						writeDate(this.date,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// int
				
		            	dos.writeInt(this.tournament_id);
					
					// String
				
						writeString(this.tournament_name,dos);
					
					// String
				
						writeString(this.location,dos);
					
					// Integer
				
						writeInteger(this.prize_money,dos);
					
					// String
				
						writeString(this.balls_brand,dos);
					
					// String
				
						writeString(this.venue_type,dos);
					
					// String
				
						writeString(this.court_manufacturer,dos);
					
					// String
				
						writeString(this.turf_type,dos);
					
					// java.util.Date
				
						writeDate(this.date,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


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
    public int compareTo(DIMStruct other) {

		int returnValue = -1;
		
						returnValue = checkNullsAndCompare(this.tournament_id, other.tournament_id);
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

public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_dim_tournament = new byte[0];
    static byte[] commonByteArray_DW_PADEL_dim_tournament = new byte[0];

	
			    public String title;

				public String getTitle () {
					return this.title;
				}
				
			    public String Country;

				public String getCountry () {
					return this.Country;
				}
				
			    public Integer prize_money;

				public Integer getPrize_money () {
					return this.prize_money;
				}
				
			    public String VENUE_TYPE;

				public String getVENUE_TYPE () {
					return this.VENUE_TYPE;
				}
				
			    public String COURT_MANUFACTURER;

				public String getCOURT_MANUFACTURER () {
					return this.COURT_MANUFACTURER;
				}
				
			    public String turf;

				public String getTurf () {
					return this.turf;
				}
				
			    public String balls;

				public String getBalls () {
					return this.balls;
				}
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_dim_tournament.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_tournament.length == 0) {
   					commonByteArray_DW_PADEL_dim_tournament = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_tournament = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_dim_tournament, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_tournament, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_dim_tournament.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_tournament.length == 0) {
   					commonByteArray_DW_PADEL_dim_tournament = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_tournament = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_dim_tournament, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_tournament, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_dim_tournament) {

        	try {

        		int length = 0;
		
					this.title = readString(dis);
					
					this.Country = readString(dis);
					
						this.prize_money = readInteger(dis);
					
					this.VENUE_TYPE = readString(dis);
					
					this.COURT_MANUFACTURER = readString(dis);
					
					this.turf = readString(dis);
					
					this.balls = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_dim_tournament) {

        	try {

        		int length = 0;
		
					this.title = readString(dis);
					
					this.Country = readString(dis);
					
						this.prize_money = readInteger(dis);
					
					this.VENUE_TYPE = readString(dis);
					
					this.COURT_MANUFACTURER = readString(dis);
					
					this.turf = readString(dis);
					
					this.balls = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.title,dos);
					
					// String
				
						writeString(this.Country,dos);
					
					// Integer
				
						writeInteger(this.prize_money,dos);
					
					// String
				
						writeString(this.VENUE_TYPE,dos);
					
					// String
				
						writeString(this.COURT_MANUFACTURER,dos);
					
					// String
				
						writeString(this.turf,dos);
					
					// String
				
						writeString(this.balls,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.title,dos);
					
					// String
				
						writeString(this.Country,dos);
					
					// Integer
				
						writeInteger(this.prize_money,dos);
					
					// String
				
						writeString(this.VENUE_TYPE,dos);
					
					// String
				
						writeString(this.COURT_MANUFACTURER,dos);
					
					// String
				
						writeString(this.turf,dos);
					
					// String
				
						writeString(this.balls,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("title="+title);
		sb.append(",Country="+Country);
		sb.append(",prize_money="+String.valueOf(prize_money));
		sb.append(",VENUE_TYPE="+VENUE_TYPE);
		sb.append(",COURT_MANUFACTURER="+COURT_MANUFACTURER);
		sb.append(",turf="+turf);
		sb.append(",balls="+balls);
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(row3Struct other) {

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
    final static byte[] commonByteArrayLock_DW_PADEL_dim_tournament = new byte[0];
    static byte[] commonByteArray_DW_PADEL_dim_tournament = new byte[0];

	
			    public String title;

				public String getTitle () {
					return this.title;
				}
				
			    public String Country;

				public String getCountry () {
					return this.Country;
				}
				
			    public Integer prize_money;

				public Integer getPrize_money () {
					return this.prize_money;
				}
				
			    public String VENUE_TYPE;

				public String getVENUE_TYPE () {
					return this.VENUE_TYPE;
				}
				
			    public String COURT_MANUFACTURER;

				public String getCOURT_MANUFACTURER () {
					return this.COURT_MANUFACTURER;
				}
				
			    public String turf;

				public String getTurf () {
					return this.turf;
				}
				
			    public String balls;

				public String getBalls () {
					return this.balls;
				}
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_dim_tournament.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_tournament.length == 0) {
   					commonByteArray_DW_PADEL_dim_tournament = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_tournament = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_dim_tournament, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_tournament, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_dim_tournament.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_tournament.length == 0) {
   					commonByteArray_DW_PADEL_dim_tournament = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_tournament = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_dim_tournament, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_tournament, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_dim_tournament) {

        	try {

        		int length = 0;
		
					this.title = readString(dis);
					
					this.Country = readString(dis);
					
						this.prize_money = readInteger(dis);
					
					this.VENUE_TYPE = readString(dis);
					
					this.COURT_MANUFACTURER = readString(dis);
					
					this.turf = readString(dis);
					
					this.balls = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_dim_tournament) {

        	try {

        		int length = 0;
		
					this.title = readString(dis);
					
					this.Country = readString(dis);
					
						this.prize_money = readInteger(dis);
					
					this.VENUE_TYPE = readString(dis);
					
					this.COURT_MANUFACTURER = readString(dis);
					
					this.turf = readString(dis);
					
					this.balls = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.title,dos);
					
					// String
				
						writeString(this.Country,dos);
					
					// Integer
				
						writeInteger(this.prize_money,dos);
					
					// String
				
						writeString(this.VENUE_TYPE,dos);
					
					// String
				
						writeString(this.COURT_MANUFACTURER,dos);
					
					// String
				
						writeString(this.turf,dos);
					
					// String
				
						writeString(this.balls,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.title,dos);
					
					// String
				
						writeString(this.Country,dos);
					
					// Integer
				
						writeInteger(this.prize_money,dos);
					
					// String
				
						writeString(this.VENUE_TYPE,dos);
					
					// String
				
						writeString(this.COURT_MANUFACTURER,dos);
					
					// String
				
						writeString(this.turf,dos);
					
					// String
				
						writeString(this.balls,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("title="+title);
		sb.append(",Country="+Country);
		sb.append(",prize_money="+String.valueOf(prize_money));
		sb.append(",VENUE_TYPE="+VENUE_TYPE);
		sb.append(",COURT_MANUFACTURER="+COURT_MANUFACTURER);
		sb.append(",turf="+turf);
		sb.append(",balls="+balls);
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

public static class after_tDBInput_1Struct implements routines.system.IPersistableRow<after_tDBInput_1Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_dim_tournament = new byte[0];
    static byte[] commonByteArray_DW_PADEL_dim_tournament = new byte[0];

	
			    public String title;

				public String getTitle () {
					return this.title;
				}
				
			    public String Country;

				public String getCountry () {
					return this.Country;
				}
				
			    public Integer prize_money;

				public Integer getPrize_money () {
					return this.prize_money;
				}
				
			    public String VENUE_TYPE;

				public String getVENUE_TYPE () {
					return this.VENUE_TYPE;
				}
				
			    public String COURT_MANUFACTURER;

				public String getCOURT_MANUFACTURER () {
					return this.COURT_MANUFACTURER;
				}
				
			    public String turf;

				public String getTurf () {
					return this.turf;
				}
				
			    public String balls;

				public String getBalls () {
					return this.balls;
				}
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_dim_tournament.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_tournament.length == 0) {
   					commonByteArray_DW_PADEL_dim_tournament = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_tournament = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_dim_tournament, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_tournament, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_dim_tournament.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_tournament.length == 0) {
   					commonByteArray_DW_PADEL_dim_tournament = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_tournament = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_dim_tournament, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_tournament, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_dim_tournament) {

        	try {

        		int length = 0;
		
					this.title = readString(dis);
					
					this.Country = readString(dis);
					
						this.prize_money = readInteger(dis);
					
					this.VENUE_TYPE = readString(dis);
					
					this.COURT_MANUFACTURER = readString(dis);
					
					this.turf = readString(dis);
					
					this.balls = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_dim_tournament) {

        	try {

        		int length = 0;
		
					this.title = readString(dis);
					
					this.Country = readString(dis);
					
						this.prize_money = readInteger(dis);
					
					this.VENUE_TYPE = readString(dis);
					
					this.COURT_MANUFACTURER = readString(dis);
					
					this.turf = readString(dis);
					
					this.balls = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.title,dos);
					
					// String
				
						writeString(this.Country,dos);
					
					// Integer
				
						writeInteger(this.prize_money,dos);
					
					// String
				
						writeString(this.VENUE_TYPE,dos);
					
					// String
				
						writeString(this.COURT_MANUFACTURER,dos);
					
					// String
				
						writeString(this.turf,dos);
					
					// String
				
						writeString(this.balls,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.title,dos);
					
					// String
				
						writeString(this.Country,dos);
					
					// Integer
				
						writeInteger(this.prize_money,dos);
					
					// String
				
						writeString(this.VENUE_TYPE,dos);
					
					// String
				
						writeString(this.COURT_MANUFACTURER,dos);
					
					// String
				
						writeString(this.turf,dos);
					
					// String
				
						writeString(this.balls,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("title="+title);
		sb.append(",Country="+Country);
		sb.append(",prize_money="+String.valueOf(prize_money));
		sb.append(",VENUE_TYPE="+VENUE_TYPE);
		sb.append(",COURT_MANUFACTURER="+COURT_MANUFACTURER);
		sb.append(",turf="+turf);
		sb.append(",balls="+balls);
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

		row1Struct row1 = new row1Struct();
row3Struct row3 = new row3Struct();
DIMStruct DIM = new DIMStruct();






	
	/**
	 * [tDBOutput_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tDBOutput_1", false);
		start_Hash.put("tDBOutput_1", System.currentTimeMillis());
		
	
	currentComponent="tDBOutput_1";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"DIM");
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

 
	final String decryptedPassword_tDBOutput_1 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:oikrZJtjSzMPUSAnSPaBdSCsqyDfAO3z+2JItYm0soyM6Es=");

    String dbPwd_tDBOutput_1 = decryptedPassword_tDBOutput_1;	
    conn_tDBOutput_1 = java.sql.DriverManager.getConnection(url_tDBOutput_1,dbUser_tDBOutput_1,dbPwd_tDBOutput_1);
	
		resourceMap.put("conn_tDBOutput_1", conn_tDBOutput_1);
	
        conn_tDBOutput_1.setAutoCommit(false);
        int commitEvery_tDBOutput_1 = 10000;
        int commitCounter_tDBOutput_1 = 0;

   int batchSize_tDBOutput_1 = 10000;
   int batchSizeCounter_tDBOutput_1=0;

if(dbschema_tDBOutput_1 == null || dbschema_tDBOutput_1.trim().length() == 0) {
    tableName_tDBOutput_1 = "dim_tournament";
} else {
    tableName_tDBOutput_1 = dbschema_tDBOutput_1 + "].[" + "dim_tournament";
}
	int count_tDBOutput_1=0;

        String insert_tDBOutput_1 = "INSERT INTO [" + tableName_tDBOutput_1 + "] ([tournament_id],[tournament_name],[location],[prize_money],[balls_brand],[venue_type],[court_manufacturer],[turf_type],[date]) VALUES (?,?,?,?,?,?,?,?,?)";
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
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row3");
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
// ###############################        

// ###############################
// # Vars initialization
class  Var__tMap_1__Struct  {
}
Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
DIMStruct DIM_tmp = new DIMStruct();
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
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row1");
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
				
				 
	final String decryptedPassword_tDBInput_1 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:jI83jIQpPhK0M3DrNujANw6U+/CvMcdzAe4G/Y1gqd5aA2I=");
				
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

		    String dbquery_tDBInput_1 = "SELECT Tournois.title,\n		Tournois.Country,\n		Tournois.prize_money,\n		Tournois.VENUE_TYPE,\n		Tournois.COURT_MANUFACTURER"
+",\n		Tournois.turf,\n		Tournois.balls\nFROM	Tournois";
		    

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
								row1.title = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(1);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(1).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.title = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.title = tmpContent_tDBInput_1;
                }
            } else {
                row1.title = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 2) {
								row1.Country = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(2);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.Country = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.Country = tmpContent_tDBInput_1;
                }
            } else {
                row1.Country = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 3) {
								row1.prize_money = null;
							} else {
		                          
            row1.prize_money = rs_tDBInput_1.getInt(3);
            if(rs_tDBInput_1.wasNull()){
                    row1.prize_money = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 4) {
								row1.VENUE_TYPE = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(4);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(4).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.VENUE_TYPE = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.VENUE_TYPE = tmpContent_tDBInput_1;
                }
            } else {
                row1.VENUE_TYPE = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 5) {
								row1.COURT_MANUFACTURER = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(5);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(5).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.COURT_MANUFACTURER = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.COURT_MANUFACTURER = tmpContent_tDBInput_1;
                }
            } else {
                row1.COURT_MANUFACTURER = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 6) {
								row1.turf = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(6);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(6).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.turf = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.turf = tmpContent_tDBInput_1;
                }
            } else {
                row1.turf = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 7) {
								row1.balls = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(7);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(7).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.balls = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.balls = tmpContent_tDBInput_1;
                }
            } else {
                row1.balls = null;
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
	 * [tFilterRow_1 main ] start
	 */

	

	
	
	currentComponent="tFilterRow_1";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row1"
						
						);
					}
					

          row3 = null;
    Operator_tFilterRow_1 ope_tFilterRow_1 = new Operator_tFilterRow_1("&&");
	        ope_tFilterRow_1.matches((row1.title != null)
	                       , "title!=null failed");
    
    if (ope_tFilterRow_1.getMatchFlag()) {
              if(row3 == null){ 
                row3 = new row3Struct();
              }
               row3.title = row1.title;
               row3.Country = row1.Country;
               row3.prize_money = row1.prize_money;
               row3.VENUE_TYPE = row1.VENUE_TYPE;
               row3.COURT_MANUFACTURER = row1.COURT_MANUFACTURER;
               row3.turf = row1.turf;
               row3.balls = row1.balls;    
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
// Start of branch "row3"
if(row3 != null) { 



	
	/**
	 * [tMap_1 main ] start
	 */

	

	
	
	currentComponent="tMap_1";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row3"
						
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
								
                        		    		    row2HashKey.tournament_name = row3.title ;
                        		    		

								
		                        	row2HashKey.hashCodeDirty = true;
                        		
	  					
	  							
			  					
			  					
	  					
		  							tHash_Lookup_row2.lookup( row2HashKey );

	  							

	  							

 								
		  				
	  								
						
									
  									  		
 								



							} // G_TM_M_020
			           		  	  
							
				           		if(tHash_Lookup_row2 != null && tHash_Lookup_row2.getCount(row2HashKey) > 1) { // G 071
			  							
			  						
									 		
									//System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row2' and it contains more one result from keys :  row2.tournament_name = '" + row2HashKey.tournament_name + "'");
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
							
							
							
			  							
								
	                    		  	
		                    
	            	
	            	
	            // ###############################
        { // start of Var scope
        
	        // ###############################
        	// # Vars tables
        
Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
        // ###############################
        // # Output tables

DIM = null;


// # Output table : 'DIM'
DIM_tmp.tournament_id = Numeric.sequence("s1",1,1) ;
DIM_tmp.tournament_name = row3.title ;
DIM_tmp.location = row3.Country ;
DIM_tmp.prize_money = row3.prize_money ;
DIM_tmp.balls_brand = row3.balls ;
DIM_tmp.venue_type = row3.VENUE_TYPE ;
DIM_tmp.court_manufacturer = row3.COURT_MANUFACTURER ;
DIM_tmp.turf_type = row3.turf ;
DIM_tmp.date = row2.date ;
DIM = DIM_tmp;
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
// Start of branch "DIM"
if(DIM != null) { 



	
	/**
	 * [tDBOutput_1 main ] start
	 */

	

	
	
	currentComponent="tDBOutput_1";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"DIM"
						
						);
					}
					



        whetherReject_tDBOutput_1 = false;
                    pstmt_tDBOutput_1.setInt(1, DIM.tournament_id);

                    if(DIM.tournament_name == null) {
pstmt_tDBOutput_1.setNull(2, java.sql.Types.VARCHAR);
} else {pstmt_tDBOutput_1.setString(2, DIM.tournament_name);
}

                    if(DIM.location == null) {
pstmt_tDBOutput_1.setNull(3, java.sql.Types.VARCHAR);
} else {pstmt_tDBOutput_1.setString(3, DIM.location);
}

                    if(DIM.prize_money == null) {
pstmt_tDBOutput_1.setNull(4, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(4, DIM.prize_money);
}

                    if(DIM.balls_brand == null) {
pstmt_tDBOutput_1.setNull(5, java.sql.Types.VARCHAR);
} else {pstmt_tDBOutput_1.setString(5, DIM.balls_brand);
}

                    if(DIM.venue_type == null) {
pstmt_tDBOutput_1.setNull(6, java.sql.Types.VARCHAR);
} else {pstmt_tDBOutput_1.setString(6, DIM.venue_type);
}

                    if(DIM.court_manufacturer == null) {
pstmt_tDBOutput_1.setNull(7, java.sql.Types.VARCHAR);
} else {pstmt_tDBOutput_1.setString(7, DIM.court_manufacturer);
}

                    if(DIM.turf_type == null) {
pstmt_tDBOutput_1.setNull(8, java.sql.Types.VARCHAR);
} else {pstmt_tDBOutput_1.setString(8, DIM.turf_type);
}

                    if(DIM.date != null) {
pstmt_tDBOutput_1.setTimestamp(9, new java.sql.Timestamp(DIM.date.getTime()));
} else {
pstmt_tDBOutput_1.setNull(9, java.sql.Types.TIMESTAMP);
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

} // End of branch "DIM"




	
	/**
	 * [tMap_1 process_data_end ] start
	 */

	

	
	
	currentComponent="tMap_1";

	

 



/**
 * [tMap_1 process_data_end ] stop
 */

} // End of branch "row3"




	
	/**
	 * [tFilterRow_1 process_data_end ] start
	 */

	

	
	
	currentComponent="tFilterRow_1";

	

 



/**
 * [tFilterRow_1 process_data_end ] stop
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
	 * [tFilterRow_1 end ] start
	 */

	

	
	
	currentComponent="tFilterRow_1";

	
    globalMap.put("tFilterRow_1_NB_LINE", nb_line_tFilterRow_1);
    globalMap.put("tFilterRow_1_NB_LINE_OK", nb_line_ok_tFilterRow_1);
    globalMap.put("tFilterRow_1_NB_LINE_REJECT", nb_line_reject_tFilterRow_1);
    

				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row1");
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
					if(tHash_Lookup_row2 != null) {
						tHash_Lookup_row2.endGet();
					}
					globalMap.remove( "tHash_Lookup_row2" );

					
					
				
// ###############################      





				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row3");
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
			  		runStat.updateStat(resourceMap,iterateId,2,0,"DIM");
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
				     			
				try{
					
	
	/**
	 * [tDBInput_1 finally ] start
	 */

	

	
	
	currentComponent="tDBInput_1";

	

 



/**
 * [tDBInput_1 finally ] stop
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
	


public static class row2Struct implements routines.system.IPersistableComparableLookupRow<row2Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_dim_tournament = new byte[0];
    static byte[] commonByteArray_DW_PADEL_dim_tournament = new byte[0];
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
		final row2Struct other = (row2Struct) obj;
		
						if (this.tournament_name == null) {
							if (other.tournament_name != null)
								return false;
						
						} else if (!this.tournament_name.equals(other.tournament_name))
						
							return false;
					

		return true;
    }

	public void copyDataTo(row2Struct other) {

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

	public void copyKeysDataTo(row2Struct other) {

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
			if(length > commonByteArray_DW_PADEL_dim_tournament.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_tournament.length == 0) {
   					commonByteArray_DW_PADEL_dim_tournament = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_tournament = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_dim_tournament, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_tournament, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_dim_tournament.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_tournament.length == 0) {
   					commonByteArray_DW_PADEL_dim_tournament = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_tournament = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_dim_tournament, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_tournament, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_dim_tournament) {

        	try {

        		int length = 0;
		
					this.tournament_name = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_dim_tournament) {

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
    public int compareTo(row2Struct other) {

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
			   		// source node:tDBInput_2 - inputs:(after_tDBInput_1) outputs:(row2,row2) | target node:tAdvancedHash_row2 - inputs:(row2) outputs:()
			   		// linked node: tMap_1 - inputs:(row3,row2) outputs:(DIM)
			   
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
				
				 
	final String decryptedPassword_tDBInput_2 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:JenLA/PpJ1yurC80xTMagmusLm4JuwzfBU0B3NHkBaGQ/JA=");
				
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
								row2.match_id = 0;
							} else {
		                          
            row2.match_id = rs_tDBInput_2.getInt(1);
            if(rs_tDBInput_2.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 2) {
								row2.match_number = null;
							} else {
		                          
            row2.match_number = rs_tDBInput_2.getInt(2);
            if(rs_tDBInput_2.wasNull()){
                    row2.match_number = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 3) {
								row2.tournament_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(3);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(3).toUpperCase(java.util.Locale.ENGLISH))) {
            		row2.tournament_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row2.tournament_name = tmpContent_tDBInput_2;
                }
            } else {
                row2.tournament_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 4) {
								row2.round = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(4);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(4).toUpperCase(java.util.Locale.ENGLISH))) {
            		row2.round = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row2.round = tmpContent_tDBInput_2;
                }
            } else {
                row2.round = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 5) {
								row2.winner = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(5);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(5).toUpperCase(java.util.Locale.ENGLISH))) {
            		row2.winner = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row2.winner = tmpContent_tDBInput_2;
                }
            } else {
                row2.winner = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 6) {
								row2.total_points_won_t1 = null;
							} else {
	                         		
            row2.total_points_won_t1 = rs_tDBInput_2.getDouble(6);
            if(rs_tDBInput_2.wasNull()){
                    row2.total_points_won_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 7) {
								row2.total_points_won_t2 = null;
							} else {
	                         		
            row2.total_points_won_t2 = rs_tDBInput_2.getDouble(7);
            if(rs_tDBInput_2.wasNull()){
                    row2.total_points_won_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 8) {
								row2.break_points_converted_t1 = null;
							} else {
	                         		
            row2.break_points_converted_t1 = rs_tDBInput_2.getDouble(8);
            if(rs_tDBInput_2.wasNull()){
                    row2.break_points_converted_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 9) {
								row2.break_points_converted_t2 = null;
							} else {
	                         		
            row2.break_points_converted_t2 = rs_tDBInput_2.getDouble(9);
            if(rs_tDBInput_2.wasNull()){
                    row2.break_points_converted_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 10) {
								row2.longest_streak_t1 = null;
							} else {
		                          
            row2.longest_streak_t1 = rs_tDBInput_2.getInt(10);
            if(rs_tDBInput_2.wasNull()){
                    row2.longest_streak_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 11) {
								row2.longest_streak_t2 = null;
							} else {
		                          
            row2.longest_streak_t2 = rs_tDBInput_2.getInt(11);
            if(rs_tDBInput_2.wasNull()){
                    row2.longest_streak_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 12) {
								row2.aces_t1 = null;
							} else {
		                          
            row2.aces_t1 = rs_tDBInput_2.getInt(12);
            if(rs_tDBInput_2.wasNull()){
                    row2.aces_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 13) {
								row2.aces_t2 = null;
							} else {
		                          
            row2.aces_t2 = rs_tDBInput_2.getInt(13);
            if(rs_tDBInput_2.wasNull()){
                    row2.aces_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 14) {
								row2.double_faults_t1 = null;
							} else {
		                          
            row2.double_faults_t1 = rs_tDBInput_2.getInt(14);
            if(rs_tDBInput_2.wasNull()){
                    row2.double_faults_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 15) {
								row2.double_faults_t2 = null;
							} else {
		                          
            row2.double_faults_t2 = rs_tDBInput_2.getInt(15);
            if(rs_tDBInput_2.wasNull()){
                    row2.double_faults_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 16) {
								row2.won_on_1st_serve_t1 = null;
							} else {
	                         		
            row2.won_on_1st_serve_t1 = rs_tDBInput_2.getDouble(16);
            if(rs_tDBInput_2.wasNull()){
                    row2.won_on_1st_serve_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 17) {
								row2.won_on_1st_serve_t2 = null;
							} else {
	                         		
            row2.won_on_1st_serve_t2 = rs_tDBInput_2.getDouble(17);
            if(rs_tDBInput_2.wasNull()){
                    row2.won_on_1st_serve_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 18) {
								row2.won_on_2nd_serve_t1 = null;
							} else {
	                         		
            row2.won_on_2nd_serve_t1 = rs_tDBInput_2.getDouble(18);
            if(rs_tDBInput_2.wasNull()){
                    row2.won_on_2nd_serve_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 19) {
								row2.won_on_2nd_serve_t2 = null;
							} else {
	                         		
            row2.won_on_2nd_serve_t2 = rs_tDBInput_2.getDouble(19);
            if(rs_tDBInput_2.wasNull()){
                    row2.won_on_2nd_serve_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 20) {
								row2.service_games_t1 = null;
							} else {
		                          
            row2.service_games_t1 = rs_tDBInput_2.getInt(20);
            if(rs_tDBInput_2.wasNull()){
                    row2.service_games_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 21) {
								row2.service_games_t2 = null;
							} else {
		                          
            row2.service_games_t2 = rs_tDBInput_2.getInt(21);
            if(rs_tDBInput_2.wasNull()){
                    row2.service_games_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 22) {
								row2.won_on_1st_return_t1 = null;
							} else {
	                         		
            row2.won_on_1st_return_t1 = rs_tDBInput_2.getDouble(22);
            if(rs_tDBInput_2.wasNull()){
                    row2.won_on_1st_return_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 23) {
								row2.won_on_1st_return_t2 = null;
							} else {
	                         		
            row2.won_on_1st_return_t2 = rs_tDBInput_2.getDouble(23);
            if(rs_tDBInput_2.wasNull()){
                    row2.won_on_1st_return_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 24) {
								row2.won_on_2nd_return_t1 = null;
							} else {
	                         		
            row2.won_on_2nd_return_t1 = rs_tDBInput_2.getDouble(24);
            if(rs_tDBInput_2.wasNull()){
                    row2.won_on_2nd_return_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 25) {
								row2.won_on_2nd_return_t2 = null;
							} else {
	                         		
            row2.won_on_2nd_return_t2 = rs_tDBInput_2.getDouble(25);
            if(rs_tDBInput_2.wasNull()){
                    row2.won_on_2nd_return_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 26) {
								row2.return_games_t1 = null;
							} else {
		                          
            row2.return_games_t1 = rs_tDBInput_2.getInt(26);
            if(rs_tDBInput_2.wasNull()){
                    row2.return_games_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 27) {
								row2.return_games_t2 = null;
							} else {
		                          
            row2.return_games_t2 = rs_tDBInput_2.getInt(27);
            if(rs_tDBInput_2.wasNull()){
                    row2.return_games_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 28) {
								row2.total_won_on_serve_t1 = null;
							} else {
	                         		
            row2.total_won_on_serve_t1 = rs_tDBInput_2.getDouble(28);
            if(rs_tDBInput_2.wasNull()){
                    row2.total_won_on_serve_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 29) {
								row2.total_won_on_serve_t2 = null;
							} else {
	                         		
            row2.total_won_on_serve_t2 = rs_tDBInput_2.getDouble(29);
            if(rs_tDBInput_2.wasNull()){
                    row2.total_won_on_serve_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 30) {
								row2.total_won_on_return_t1 = null;
							} else {
	                         		
            row2.total_won_on_return_t1 = rs_tDBInput_2.getDouble(30);
            if(rs_tDBInput_2.wasNull()){
                    row2.total_won_on_return_t1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 31) {
								row2.total_won_on_return_t2 = null;
							} else {
	                         		
            row2.total_won_on_return_t2 = rs_tDBInput_2.getDouble(31);
            if(rs_tDBInput_2.wasNull()){
                    row2.total_won_on_return_t2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 32) {
								row2.date = null;
							} else {
										
			row2.date = mssqlGTU_tDBInput_2.getDate(rsmd_tDBInput_2, rs_tDBInput_2, 32);
			
		                    }
							if(colQtyInRs_tDBInput_2 < 33) {
								row2.team1_player1_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(33);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(33).toUpperCase(java.util.Locale.ENGLISH))) {
            		row2.team1_player1_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row2.team1_player1_name = tmpContent_tDBInput_2;
                }
            } else {
                row2.team1_player1_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 34) {
								row2.team1_player2_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(34);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(34).toUpperCase(java.util.Locale.ENGLISH))) {
            		row2.team1_player2_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row2.team1_player2_name = tmpContent_tDBInput_2;
                }
            } else {
                row2.team1_player2_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 35) {
								row2.team2_player1_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(35);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(35).toUpperCase(java.util.Locale.ENGLISH))) {
            		row2.team2_player1_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row2.team2_player1_name = tmpContent_tDBInput_2;
                }
            } else {
                row2.team2_player1_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 36) {
								row2.team2_player2_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(36);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(36).toUpperCase(java.util.Locale.ENGLISH))) {
            		row2.team2_player2_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row2.team2_player2_name = tmpContent_tDBInput_2;
                }
            } else {
                row2.team2_player2_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 37) {
								row2.match_info_added = null;
							} else {
		                          
            row2.match_info_added = rs_tDBInput_2.getInt(37);
            if(rs_tDBInput_2.wasNull()){
                    row2.match_info_added = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 38) {
								row2.t1_s1 = null;
							} else {
		                          
            row2.t1_s1 = rs_tDBInput_2.getInt(38);
            if(rs_tDBInput_2.wasNull()){
                    row2.t1_s1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 39) {
								row2.t2_s1 = null;
							} else {
		                          
            row2.t2_s1 = rs_tDBInput_2.getInt(39);
            if(rs_tDBInput_2.wasNull()){
                    row2.t2_s1 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 40) {
								row2.t1_s2 = null;
							} else {
		                          
            row2.t1_s2 = rs_tDBInput_2.getInt(40);
            if(rs_tDBInput_2.wasNull()){
                    row2.t1_s2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 41) {
								row2.t2_s2 = null;
							} else {
		                          
            row2.t2_s2 = rs_tDBInput_2.getInt(41);
            if(rs_tDBInput_2.wasNull()){
                    row2.t2_s2 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 42) {
								row2.t1_s3 = null;
							} else {
		                          
            row2.t1_s3 = rs_tDBInput_2.getInt(42);
            if(rs_tDBInput_2.wasNull()){
                    row2.t1_s3 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 43) {
								row2.t2_s3 = null;
							} else {
		                          
            row2.t2_s3 = rs_tDBInput_2.getInt(43);
            if(rs_tDBInput_2.wasNull()){
                    row2.t2_s3 = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 44) {
								row2.views = null;
							} else {
		                          
            row2.views = rs_tDBInput_2.getInt(44);
            if(rs_tDBInput_2.wasNull()){
                    row2.views = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 45) {
								row2.interactions = null;
							} else {
		                          
            row2.interactions = rs_tDBInput_2.getInt(45);
            if(rs_tDBInput_2.wasNull()){
                    row2.interactions = null;
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
		   	   	   
				
				row2_HashRow.match_id = row2.match_id;
				
				row2_HashRow.match_number = row2.match_number;
				
				row2_HashRow.tournament_name = row2.tournament_name;
				
				row2_HashRow.round = row2.round;
				
				row2_HashRow.winner = row2.winner;
				
				row2_HashRow.total_points_won_t1 = row2.total_points_won_t1;
				
				row2_HashRow.total_points_won_t2 = row2.total_points_won_t2;
				
				row2_HashRow.break_points_converted_t1 = row2.break_points_converted_t1;
				
				row2_HashRow.break_points_converted_t2 = row2.break_points_converted_t2;
				
				row2_HashRow.longest_streak_t1 = row2.longest_streak_t1;
				
				row2_HashRow.longest_streak_t2 = row2.longest_streak_t2;
				
				row2_HashRow.aces_t1 = row2.aces_t1;
				
				row2_HashRow.aces_t2 = row2.aces_t2;
				
				row2_HashRow.double_faults_t1 = row2.double_faults_t1;
				
				row2_HashRow.double_faults_t2 = row2.double_faults_t2;
				
				row2_HashRow.won_on_1st_serve_t1 = row2.won_on_1st_serve_t1;
				
				row2_HashRow.won_on_1st_serve_t2 = row2.won_on_1st_serve_t2;
				
				row2_HashRow.won_on_2nd_serve_t1 = row2.won_on_2nd_serve_t1;
				
				row2_HashRow.won_on_2nd_serve_t2 = row2.won_on_2nd_serve_t2;
				
				row2_HashRow.service_games_t1 = row2.service_games_t1;
				
				row2_HashRow.service_games_t2 = row2.service_games_t2;
				
				row2_HashRow.won_on_1st_return_t1 = row2.won_on_1st_return_t1;
				
				row2_HashRow.won_on_1st_return_t2 = row2.won_on_1st_return_t2;
				
				row2_HashRow.won_on_2nd_return_t1 = row2.won_on_2nd_return_t1;
				
				row2_HashRow.won_on_2nd_return_t2 = row2.won_on_2nd_return_t2;
				
				row2_HashRow.return_games_t1 = row2.return_games_t1;
				
				row2_HashRow.return_games_t2 = row2.return_games_t2;
				
				row2_HashRow.total_won_on_serve_t1 = row2.total_won_on_serve_t1;
				
				row2_HashRow.total_won_on_serve_t2 = row2.total_won_on_serve_t2;
				
				row2_HashRow.total_won_on_return_t1 = row2.total_won_on_return_t1;
				
				row2_HashRow.total_won_on_return_t2 = row2.total_won_on_return_t2;
				
				row2_HashRow.date = row2.date;
				
				row2_HashRow.team1_player1_name = row2.team1_player1_name;
				
				row2_HashRow.team1_player2_name = row2.team1_player2_name;
				
				row2_HashRow.team2_player1_name = row2.team2_player1_name;
				
				row2_HashRow.team2_player2_name = row2.team2_player2_name;
				
				row2_HashRow.match_info_added = row2.match_info_added;
				
				row2_HashRow.t1_s1 = row2.t1_s1;
				
				row2_HashRow.t2_s1 = row2.t2_s1;
				
				row2_HashRow.t1_s2 = row2.t1_s2;
				
				row2_HashRow.t2_s2 = row2.t2_s2;
				
				row2_HashRow.t1_s3 = row2.t1_s3;
				
				row2_HashRow.t2_s3 = row2.t2_s3;
				
				row2_HashRow.views = row2.views;
				
				row2_HashRow.interactions = row2.interactions;
				
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
        final dim_tournament dim_tournamentClass = new dim_tournament();

        int exitCode = dim_tournamentClass.runJobInTOS(args);

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
            java.io.InputStream inContext = dim_tournament.class.getClassLoader().getResourceAsStream("dw_padel/dim_tournament_0_1/contexts/" + contextStr + ".properties");
            if (inContext == null) {
                inContext = dim_tournament.class.getClassLoader().getResourceAsStream("config/contexts/" + contextStr + ".properties");
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
            System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : dim_tournament");
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
 *     169801 characters generated by Talend Open Studio for Data Integration 
 *     on the 29 avril 2026 à 03:24:09 WAT
 ************************************************************************************************/