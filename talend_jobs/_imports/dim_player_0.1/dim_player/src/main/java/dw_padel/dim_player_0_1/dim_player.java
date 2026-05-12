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


package dw_padel.dim_player_0_1;

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
 * Job: dim_player Purpose: <br>
 * Description:  <br>
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status 
 */
public class dim_player implements TalendJob {

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
	private final String jobName = "dim_player";
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
				dim_player.this.exception = e;
			}
		}
		if (!(e instanceof TalendException)) {
		try {
			for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
				if (m.getName().compareTo(currentComponent + "_error") == 0) {
					m.invoke(dim_player.this, new Object[] { e , currentComponent, globalMap});
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
			
			public void tAdvancedHash_row4_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_1_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
	






public static class DIMStruct implements routines.system.IPersistableRow<DIMStruct> {
    final static byte[] commonByteArrayLock_DW_PADEL_dim_player = new byte[0];
    static byte[] commonByteArray_DW_PADEL_dim_player = new byte[0];
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
		final DIMStruct other = (DIMStruct) obj;
		
						if (this.player_id != other.player_id)
							return false;
					

		return true;
    }

	public void copyDataTo(DIMStruct other) {

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

	public void copyKeysDataTo(DIMStruct other) {

		other.player_id = this.player_id;
	            	
	}




	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_dim_player.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_player.length == 0) {
   					commonByteArray_DW_PADEL_dim_player = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_player = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_dim_player, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_player, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_dim_player.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_player.length == 0) {
   					commonByteArray_DW_PADEL_dim_player = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_player = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_dim_player, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_player, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_dim_player) {

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

		synchronized(commonByteArrayLock_DW_PADEL_dim_player) {

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
    public int compareTo(DIMStruct other) {

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

public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_dim_player = new byte[0];
    static byte[] commonByteArray_DW_PADEL_dim_player = new byte[0];

	
			    public String name;

				public String getName () {
					return this.name;
				}
				
			    public String ranking;

				public String getRanking () {
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
				
			    public String total_points;

				public String getTotal_points () {
					return this.total_points;
				}
				
			    public String height;

				public String getHeight () {
					return this.height;
				}
				
			    public String birthdate;

				public String getBirthdate () {
					return this.birthdate;
				}
				
			    public String age;

				public String getAge () {
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
				
			    public String _023_matches_played;

				public String get_023_matches_played () {
					return this._023_matches_played;
				}
				
			    public String _023_matches_won;

				public String get_023_matches_won () {
					return this._023_matches_won;
				}
				
			    public String _023_win_pct;

				public String get_023_win_pct () {
					return this._023_win_pct;
				}
				
			    public String _023_sets_won;

				public String get_023_sets_won () {
					return this._023_sets_won;
				}
				
			    public String _023_sets_lost;

				public String get_023_sets_lost () {
					return this._023_sets_lost;
				}
				
			    public String _023_avg_sets_match;

				public String get_023_avg_sets_match () {
					return this._023_avg_sets_match;
				}
				
			    public String _023_games_won;

				public String get_023_games_won () {
					return this._023_games_won;
				}
				
			    public String _023_games_lost;

				public String get_023_games_lost () {
					return this._023_games_lost;
				}
				
			    public String _023_avg_games_match;

				public String get_023_avg_games_match () {
					return this._023_avg_games_match;
				}
				
			    public String _023_titles;

				public String get_023_titles () {
					return this._023_titles;
				}
				
			    public String _023_finals;

				public String get_023_finals () {
					return this._023_finals;
				}
				
			    public String _023_semifinals;

				public String get_023_semifinals () {
					return this._023_semifinals;
				}
				
			    public String _023_best_round;

				public String get_023_best_round () {
					return this._023_best_round;
				}
				
			    public String _024_matches_played;

				public String get_024_matches_played () {
					return this._024_matches_played;
				}
				
			    public String _024_matches_won;

				public String get_024_matches_won () {
					return this._024_matches_won;
				}
				
			    public String _024_win_pct;

				public String get_024_win_pct () {
					return this._024_win_pct;
				}
				
			    public String _024_sets_won;

				public String get_024_sets_won () {
					return this._024_sets_won;
				}
				
			    public String _024_sets_lost;

				public String get_024_sets_lost () {
					return this._024_sets_lost;
				}
				
			    public String _024_avg_sets_match;

				public String get_024_avg_sets_match () {
					return this._024_avg_sets_match;
				}
				
			    public String _024_games_won;

				public String get_024_games_won () {
					return this._024_games_won;
				}
				
			    public String _024_games_lost;

				public String get_024_games_lost () {
					return this._024_games_lost;
				}
				
			    public String _024_avg_games_match;

				public String get_024_avg_games_match () {
					return this._024_avg_games_match;
				}
				
			    public String _024_titles;

				public String get_024_titles () {
					return this._024_titles;
				}
				
			    public String _024_finals;

				public String get_024_finals () {
					return this._024_finals;
				}
				
			    public String _024_semifinals;

				public String get_024_semifinals () {
					return this._024_semifinals;
				}
				
			    public String _024_best_round;

				public String get_024_best_round () {
					return this._024_best_round;
				}
				
			    public String _025_matches_played;

				public String get_025_matches_played () {
					return this._025_matches_played;
				}
				
			    public String _025_matches_won;

				public String get_025_matches_won () {
					return this._025_matches_won;
				}
				
			    public String _025_win_pct;

				public String get_025_win_pct () {
					return this._025_win_pct;
				}
				
			    public String _025_sets_won;

				public String get_025_sets_won () {
					return this._025_sets_won;
				}
				
			    public String _025_sets_lost;

				public String get_025_sets_lost () {
					return this._025_sets_lost;
				}
				
			    public String _025_avg_sets_match;

				public String get_025_avg_sets_match () {
					return this._025_avg_sets_match;
				}
				
			    public String _025_games_won;

				public String get_025_games_won () {
					return this._025_games_won;
				}
				
			    public String _025_games_lost;

				public String get_025_games_lost () {
					return this._025_games_lost;
				}
				
			    public String _025_avg_games_match;

				public String get_025_avg_games_match () {
					return this._025_avg_games_match;
				}
				
			    public String _025_titles;

				public String get_025_titles () {
					return this._025_titles;
				}
				
			    public String _025_finals;

				public String get_025_finals () {
					return this._025_finals;
				}
				
			    public String _025_semifinals;

				public String get_025_semifinals () {
					return this._025_semifinals;
				}
				
			    public String _025_best_round;

				public String get_025_best_round () {
					return this._025_best_round;
				}
				
			    public String _026_matches_played;

				public String get_026_matches_played () {
					return this._026_matches_played;
				}
				
			    public String _026_matches_won;

				public String get_026_matches_won () {
					return this._026_matches_won;
				}
				
			    public String _026_win_pct;

				public String get_026_win_pct () {
					return this._026_win_pct;
				}
				
			    public String _026_sets_won;

				public String get_026_sets_won () {
					return this._026_sets_won;
				}
				
			    public String _026_sets_lost;

				public String get_026_sets_lost () {
					return this._026_sets_lost;
				}
				
			    public String _026_avg_sets_match;

				public String get_026_avg_sets_match () {
					return this._026_avg_sets_match;
				}
				
			    public String _026_games_won;

				public String get_026_games_won () {
					return this._026_games_won;
				}
				
			    public String _026_games_lost;

				public String get_026_games_lost () {
					return this._026_games_lost;
				}
				
			    public String _026_avg_games_match;

				public String get_026_avg_games_match () {
					return this._026_avg_games_match;
				}
				
			    public String _026_titles;

				public String get_026_titles () {
					return this._026_titles;
				}
				
			    public String _026_finals;

				public String get_026_finals () {
					return this._026_finals;
				}
				
			    public String _026_semifinals;

				public String get_026_semifinals () {
					return this._026_semifinals;
				}
				
			    public String _026_best_round;

				public String get_026_best_round () {
					return this._026_best_round;
				}
				
			    public String followers;

				public String getFollowers () {
					return this.followers;
				}
				
			    public String interactions;

				public String getInteractions () {
					return this.interactions;
				}
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_dim_player.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_player.length == 0) {
   					commonByteArray_DW_PADEL_dim_player = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_player = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_dim_player, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_player, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_dim_player.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_player.length == 0) {
   					commonByteArray_DW_PADEL_dim_player = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_player = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_dim_player, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_player, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_dim_player) {

        	try {

        		int length = 0;
		
					this.name = readString(dis);
					
					this.ranking = readString(dis);
					
					this.gender = readString(dis);
					
					this.nationality = readString(dis);
					
					this.total_points = readString(dis);
					
					this.height = readString(dis);
					
					this.birthdate = readString(dis);
					
					this.age = readString(dis);
					
					this.hand = readString(dis);
					
					this.side = readString(dis);
					
					this.partner = readString(dis);
					
					this._023_matches_played = readString(dis);
					
					this._023_matches_won = readString(dis);
					
					this._023_win_pct = readString(dis);
					
					this._023_sets_won = readString(dis);
					
					this._023_sets_lost = readString(dis);
					
					this._023_avg_sets_match = readString(dis);
					
					this._023_games_won = readString(dis);
					
					this._023_games_lost = readString(dis);
					
					this._023_avg_games_match = readString(dis);
					
					this._023_titles = readString(dis);
					
					this._023_finals = readString(dis);
					
					this._023_semifinals = readString(dis);
					
					this._023_best_round = readString(dis);
					
					this._024_matches_played = readString(dis);
					
					this._024_matches_won = readString(dis);
					
					this._024_win_pct = readString(dis);
					
					this._024_sets_won = readString(dis);
					
					this._024_sets_lost = readString(dis);
					
					this._024_avg_sets_match = readString(dis);
					
					this._024_games_won = readString(dis);
					
					this._024_games_lost = readString(dis);
					
					this._024_avg_games_match = readString(dis);
					
					this._024_titles = readString(dis);
					
					this._024_finals = readString(dis);
					
					this._024_semifinals = readString(dis);
					
					this._024_best_round = readString(dis);
					
					this._025_matches_played = readString(dis);
					
					this._025_matches_won = readString(dis);
					
					this._025_win_pct = readString(dis);
					
					this._025_sets_won = readString(dis);
					
					this._025_sets_lost = readString(dis);
					
					this._025_avg_sets_match = readString(dis);
					
					this._025_games_won = readString(dis);
					
					this._025_games_lost = readString(dis);
					
					this._025_avg_games_match = readString(dis);
					
					this._025_titles = readString(dis);
					
					this._025_finals = readString(dis);
					
					this._025_semifinals = readString(dis);
					
					this._025_best_round = readString(dis);
					
					this._026_matches_played = readString(dis);
					
					this._026_matches_won = readString(dis);
					
					this._026_win_pct = readString(dis);
					
					this._026_sets_won = readString(dis);
					
					this._026_sets_lost = readString(dis);
					
					this._026_avg_sets_match = readString(dis);
					
					this._026_games_won = readString(dis);
					
					this._026_games_lost = readString(dis);
					
					this._026_avg_games_match = readString(dis);
					
					this._026_titles = readString(dis);
					
					this._026_finals = readString(dis);
					
					this._026_semifinals = readString(dis);
					
					this._026_best_round = readString(dis);
					
					this.followers = readString(dis);
					
					this.interactions = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_dim_player) {

        	try {

        		int length = 0;
		
					this.name = readString(dis);
					
					this.ranking = readString(dis);
					
					this.gender = readString(dis);
					
					this.nationality = readString(dis);
					
					this.total_points = readString(dis);
					
					this.height = readString(dis);
					
					this.birthdate = readString(dis);
					
					this.age = readString(dis);
					
					this.hand = readString(dis);
					
					this.side = readString(dis);
					
					this.partner = readString(dis);
					
					this._023_matches_played = readString(dis);
					
					this._023_matches_won = readString(dis);
					
					this._023_win_pct = readString(dis);
					
					this._023_sets_won = readString(dis);
					
					this._023_sets_lost = readString(dis);
					
					this._023_avg_sets_match = readString(dis);
					
					this._023_games_won = readString(dis);
					
					this._023_games_lost = readString(dis);
					
					this._023_avg_games_match = readString(dis);
					
					this._023_titles = readString(dis);
					
					this._023_finals = readString(dis);
					
					this._023_semifinals = readString(dis);
					
					this._023_best_round = readString(dis);
					
					this._024_matches_played = readString(dis);
					
					this._024_matches_won = readString(dis);
					
					this._024_win_pct = readString(dis);
					
					this._024_sets_won = readString(dis);
					
					this._024_sets_lost = readString(dis);
					
					this._024_avg_sets_match = readString(dis);
					
					this._024_games_won = readString(dis);
					
					this._024_games_lost = readString(dis);
					
					this._024_avg_games_match = readString(dis);
					
					this._024_titles = readString(dis);
					
					this._024_finals = readString(dis);
					
					this._024_semifinals = readString(dis);
					
					this._024_best_round = readString(dis);
					
					this._025_matches_played = readString(dis);
					
					this._025_matches_won = readString(dis);
					
					this._025_win_pct = readString(dis);
					
					this._025_sets_won = readString(dis);
					
					this._025_sets_lost = readString(dis);
					
					this._025_avg_sets_match = readString(dis);
					
					this._025_games_won = readString(dis);
					
					this._025_games_lost = readString(dis);
					
					this._025_avg_games_match = readString(dis);
					
					this._025_titles = readString(dis);
					
					this._025_finals = readString(dis);
					
					this._025_semifinals = readString(dis);
					
					this._025_best_round = readString(dis);
					
					this._026_matches_played = readString(dis);
					
					this._026_matches_won = readString(dis);
					
					this._026_win_pct = readString(dis);
					
					this._026_sets_won = readString(dis);
					
					this._026_sets_lost = readString(dis);
					
					this._026_avg_sets_match = readString(dis);
					
					this._026_games_won = readString(dis);
					
					this._026_games_lost = readString(dis);
					
					this._026_avg_games_match = readString(dis);
					
					this._026_titles = readString(dis);
					
					this._026_finals = readString(dis);
					
					this._026_semifinals = readString(dis);
					
					this._026_best_round = readString(dis);
					
					this.followers = readString(dis);
					
					this.interactions = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.name,dos);
					
					// String
				
						writeString(this.ranking,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.nationality,dos);
					
					// String
				
						writeString(this.total_points,dos);
					
					// String
				
						writeString(this.height,dos);
					
					// String
				
						writeString(this.birthdate,dos);
					
					// String
				
						writeString(this.age,dos);
					
					// String
				
						writeString(this.hand,dos);
					
					// String
				
						writeString(this.side,dos);
					
					// String
				
						writeString(this.partner,dos);
					
					// String
				
						writeString(this._023_matches_played,dos);
					
					// String
				
						writeString(this._023_matches_won,dos);
					
					// String
				
						writeString(this._023_win_pct,dos);
					
					// String
				
						writeString(this._023_sets_won,dos);
					
					// String
				
						writeString(this._023_sets_lost,dos);
					
					// String
				
						writeString(this._023_avg_sets_match,dos);
					
					// String
				
						writeString(this._023_games_won,dos);
					
					// String
				
						writeString(this._023_games_lost,dos);
					
					// String
				
						writeString(this._023_avg_games_match,dos);
					
					// String
				
						writeString(this._023_titles,dos);
					
					// String
				
						writeString(this._023_finals,dos);
					
					// String
				
						writeString(this._023_semifinals,dos);
					
					// String
				
						writeString(this._023_best_round,dos);
					
					// String
				
						writeString(this._024_matches_played,dos);
					
					// String
				
						writeString(this._024_matches_won,dos);
					
					// String
				
						writeString(this._024_win_pct,dos);
					
					// String
				
						writeString(this._024_sets_won,dos);
					
					// String
				
						writeString(this._024_sets_lost,dos);
					
					// String
				
						writeString(this._024_avg_sets_match,dos);
					
					// String
				
						writeString(this._024_games_won,dos);
					
					// String
				
						writeString(this._024_games_lost,dos);
					
					// String
				
						writeString(this._024_avg_games_match,dos);
					
					// String
				
						writeString(this._024_titles,dos);
					
					// String
				
						writeString(this._024_finals,dos);
					
					// String
				
						writeString(this._024_semifinals,dos);
					
					// String
				
						writeString(this._024_best_round,dos);
					
					// String
				
						writeString(this._025_matches_played,dos);
					
					// String
				
						writeString(this._025_matches_won,dos);
					
					// String
				
						writeString(this._025_win_pct,dos);
					
					// String
				
						writeString(this._025_sets_won,dos);
					
					// String
				
						writeString(this._025_sets_lost,dos);
					
					// String
				
						writeString(this._025_avg_sets_match,dos);
					
					// String
				
						writeString(this._025_games_won,dos);
					
					// String
				
						writeString(this._025_games_lost,dos);
					
					// String
				
						writeString(this._025_avg_games_match,dos);
					
					// String
				
						writeString(this._025_titles,dos);
					
					// String
				
						writeString(this._025_finals,dos);
					
					// String
				
						writeString(this._025_semifinals,dos);
					
					// String
				
						writeString(this._025_best_round,dos);
					
					// String
				
						writeString(this._026_matches_played,dos);
					
					// String
				
						writeString(this._026_matches_won,dos);
					
					// String
				
						writeString(this._026_win_pct,dos);
					
					// String
				
						writeString(this._026_sets_won,dos);
					
					// String
				
						writeString(this._026_sets_lost,dos);
					
					// String
				
						writeString(this._026_avg_sets_match,dos);
					
					// String
				
						writeString(this._026_games_won,dos);
					
					// String
				
						writeString(this._026_games_lost,dos);
					
					// String
				
						writeString(this._026_avg_games_match,dos);
					
					// String
				
						writeString(this._026_titles,dos);
					
					// String
				
						writeString(this._026_finals,dos);
					
					// String
				
						writeString(this._026_semifinals,dos);
					
					// String
				
						writeString(this._026_best_round,dos);
					
					// String
				
						writeString(this.followers,dos);
					
					// String
				
						writeString(this.interactions,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.name,dos);
					
					// String
				
						writeString(this.ranking,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.nationality,dos);
					
					// String
				
						writeString(this.total_points,dos);
					
					// String
				
						writeString(this.height,dos);
					
					// String
				
						writeString(this.birthdate,dos);
					
					// String
				
						writeString(this.age,dos);
					
					// String
				
						writeString(this.hand,dos);
					
					// String
				
						writeString(this.side,dos);
					
					// String
				
						writeString(this.partner,dos);
					
					// String
				
						writeString(this._023_matches_played,dos);
					
					// String
				
						writeString(this._023_matches_won,dos);
					
					// String
				
						writeString(this._023_win_pct,dos);
					
					// String
				
						writeString(this._023_sets_won,dos);
					
					// String
				
						writeString(this._023_sets_lost,dos);
					
					// String
				
						writeString(this._023_avg_sets_match,dos);
					
					// String
				
						writeString(this._023_games_won,dos);
					
					// String
				
						writeString(this._023_games_lost,dos);
					
					// String
				
						writeString(this._023_avg_games_match,dos);
					
					// String
				
						writeString(this._023_titles,dos);
					
					// String
				
						writeString(this._023_finals,dos);
					
					// String
				
						writeString(this._023_semifinals,dos);
					
					// String
				
						writeString(this._023_best_round,dos);
					
					// String
				
						writeString(this._024_matches_played,dos);
					
					// String
				
						writeString(this._024_matches_won,dos);
					
					// String
				
						writeString(this._024_win_pct,dos);
					
					// String
				
						writeString(this._024_sets_won,dos);
					
					// String
				
						writeString(this._024_sets_lost,dos);
					
					// String
				
						writeString(this._024_avg_sets_match,dos);
					
					// String
				
						writeString(this._024_games_won,dos);
					
					// String
				
						writeString(this._024_games_lost,dos);
					
					// String
				
						writeString(this._024_avg_games_match,dos);
					
					// String
				
						writeString(this._024_titles,dos);
					
					// String
				
						writeString(this._024_finals,dos);
					
					// String
				
						writeString(this._024_semifinals,dos);
					
					// String
				
						writeString(this._024_best_round,dos);
					
					// String
				
						writeString(this._025_matches_played,dos);
					
					// String
				
						writeString(this._025_matches_won,dos);
					
					// String
				
						writeString(this._025_win_pct,dos);
					
					// String
				
						writeString(this._025_sets_won,dos);
					
					// String
				
						writeString(this._025_sets_lost,dos);
					
					// String
				
						writeString(this._025_avg_sets_match,dos);
					
					// String
				
						writeString(this._025_games_won,dos);
					
					// String
				
						writeString(this._025_games_lost,dos);
					
					// String
				
						writeString(this._025_avg_games_match,dos);
					
					// String
				
						writeString(this._025_titles,dos);
					
					// String
				
						writeString(this._025_finals,dos);
					
					// String
				
						writeString(this._025_semifinals,dos);
					
					// String
				
						writeString(this._025_best_round,dos);
					
					// String
				
						writeString(this._026_matches_played,dos);
					
					// String
				
						writeString(this._026_matches_won,dos);
					
					// String
				
						writeString(this._026_win_pct,dos);
					
					// String
				
						writeString(this._026_sets_won,dos);
					
					// String
				
						writeString(this._026_sets_lost,dos);
					
					// String
				
						writeString(this._026_avg_sets_match,dos);
					
					// String
				
						writeString(this._026_games_won,dos);
					
					// String
				
						writeString(this._026_games_lost,dos);
					
					// String
				
						writeString(this._026_avg_games_match,dos);
					
					// String
				
						writeString(this._026_titles,dos);
					
					// String
				
						writeString(this._026_finals,dos);
					
					// String
				
						writeString(this._026_semifinals,dos);
					
					// String
				
						writeString(this._026_best_round,dos);
					
					// String
				
						writeString(this.followers,dos);
					
					// String
				
						writeString(this.interactions,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("name="+name);
		sb.append(",ranking="+ranking);
		sb.append(",gender="+gender);
		sb.append(",nationality="+nationality);
		sb.append(",total_points="+total_points);
		sb.append(",height="+height);
		sb.append(",birthdate="+birthdate);
		sb.append(",age="+age);
		sb.append(",hand="+hand);
		sb.append(",side="+side);
		sb.append(",partner="+partner);
		sb.append(",_023_matches_played="+_023_matches_played);
		sb.append(",_023_matches_won="+_023_matches_won);
		sb.append(",_023_win_pct="+_023_win_pct);
		sb.append(",_023_sets_won="+_023_sets_won);
		sb.append(",_023_sets_lost="+_023_sets_lost);
		sb.append(",_023_avg_sets_match="+_023_avg_sets_match);
		sb.append(",_023_games_won="+_023_games_won);
		sb.append(",_023_games_lost="+_023_games_lost);
		sb.append(",_023_avg_games_match="+_023_avg_games_match);
		sb.append(",_023_titles="+_023_titles);
		sb.append(",_023_finals="+_023_finals);
		sb.append(",_023_semifinals="+_023_semifinals);
		sb.append(",_023_best_round="+_023_best_round);
		sb.append(",_024_matches_played="+_024_matches_played);
		sb.append(",_024_matches_won="+_024_matches_won);
		sb.append(",_024_win_pct="+_024_win_pct);
		sb.append(",_024_sets_won="+_024_sets_won);
		sb.append(",_024_sets_lost="+_024_sets_lost);
		sb.append(",_024_avg_sets_match="+_024_avg_sets_match);
		sb.append(",_024_games_won="+_024_games_won);
		sb.append(",_024_games_lost="+_024_games_lost);
		sb.append(",_024_avg_games_match="+_024_avg_games_match);
		sb.append(",_024_titles="+_024_titles);
		sb.append(",_024_finals="+_024_finals);
		sb.append(",_024_semifinals="+_024_semifinals);
		sb.append(",_024_best_round="+_024_best_round);
		sb.append(",_025_matches_played="+_025_matches_played);
		sb.append(",_025_matches_won="+_025_matches_won);
		sb.append(",_025_win_pct="+_025_win_pct);
		sb.append(",_025_sets_won="+_025_sets_won);
		sb.append(",_025_sets_lost="+_025_sets_lost);
		sb.append(",_025_avg_sets_match="+_025_avg_sets_match);
		sb.append(",_025_games_won="+_025_games_won);
		sb.append(",_025_games_lost="+_025_games_lost);
		sb.append(",_025_avg_games_match="+_025_avg_games_match);
		sb.append(",_025_titles="+_025_titles);
		sb.append(",_025_finals="+_025_finals);
		sb.append(",_025_semifinals="+_025_semifinals);
		sb.append(",_025_best_round="+_025_best_round);
		sb.append(",_026_matches_played="+_026_matches_played);
		sb.append(",_026_matches_won="+_026_matches_won);
		sb.append(",_026_win_pct="+_026_win_pct);
		sb.append(",_026_sets_won="+_026_sets_won);
		sb.append(",_026_sets_lost="+_026_sets_lost);
		sb.append(",_026_avg_sets_match="+_026_avg_sets_match);
		sb.append(",_026_games_won="+_026_games_won);
		sb.append(",_026_games_lost="+_026_games_lost);
		sb.append(",_026_avg_games_match="+_026_avg_games_match);
		sb.append(",_026_titles="+_026_titles);
		sb.append(",_026_finals="+_026_finals);
		sb.append(",_026_semifinals="+_026_semifinals);
		sb.append(",_026_best_round="+_026_best_round);
		sb.append(",followers="+followers);
		sb.append(",interactions="+interactions);
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

public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_dim_player = new byte[0];
    static byte[] commonByteArray_DW_PADEL_dim_player = new byte[0];

	
			    public String name;

				public String getName () {
					return this.name;
				}
				
			    public String ranking;

				public String getRanking () {
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
				
			    public String total_points;

				public String getTotal_points () {
					return this.total_points;
				}
				
			    public String height;

				public String getHeight () {
					return this.height;
				}
				
			    public String birthdate;

				public String getBirthdate () {
					return this.birthdate;
				}
				
			    public String age;

				public String getAge () {
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
				
			    public String _023_matches_played;

				public String get_023_matches_played () {
					return this._023_matches_played;
				}
				
			    public String _023_matches_won;

				public String get_023_matches_won () {
					return this._023_matches_won;
				}
				
			    public String _023_win_pct;

				public String get_023_win_pct () {
					return this._023_win_pct;
				}
				
			    public String _023_sets_won;

				public String get_023_sets_won () {
					return this._023_sets_won;
				}
				
			    public String _023_sets_lost;

				public String get_023_sets_lost () {
					return this._023_sets_lost;
				}
				
			    public String _023_avg_sets_match;

				public String get_023_avg_sets_match () {
					return this._023_avg_sets_match;
				}
				
			    public String _023_games_won;

				public String get_023_games_won () {
					return this._023_games_won;
				}
				
			    public String _023_games_lost;

				public String get_023_games_lost () {
					return this._023_games_lost;
				}
				
			    public String _023_avg_games_match;

				public String get_023_avg_games_match () {
					return this._023_avg_games_match;
				}
				
			    public String _023_titles;

				public String get_023_titles () {
					return this._023_titles;
				}
				
			    public String _023_finals;

				public String get_023_finals () {
					return this._023_finals;
				}
				
			    public String _023_semifinals;

				public String get_023_semifinals () {
					return this._023_semifinals;
				}
				
			    public String _023_best_round;

				public String get_023_best_round () {
					return this._023_best_round;
				}
				
			    public String _024_matches_played;

				public String get_024_matches_played () {
					return this._024_matches_played;
				}
				
			    public String _024_matches_won;

				public String get_024_matches_won () {
					return this._024_matches_won;
				}
				
			    public String _024_win_pct;

				public String get_024_win_pct () {
					return this._024_win_pct;
				}
				
			    public String _024_sets_won;

				public String get_024_sets_won () {
					return this._024_sets_won;
				}
				
			    public String _024_sets_lost;

				public String get_024_sets_lost () {
					return this._024_sets_lost;
				}
				
			    public String _024_avg_sets_match;

				public String get_024_avg_sets_match () {
					return this._024_avg_sets_match;
				}
				
			    public String _024_games_won;

				public String get_024_games_won () {
					return this._024_games_won;
				}
				
			    public String _024_games_lost;

				public String get_024_games_lost () {
					return this._024_games_lost;
				}
				
			    public String _024_avg_games_match;

				public String get_024_avg_games_match () {
					return this._024_avg_games_match;
				}
				
			    public String _024_titles;

				public String get_024_titles () {
					return this._024_titles;
				}
				
			    public String _024_finals;

				public String get_024_finals () {
					return this._024_finals;
				}
				
			    public String _024_semifinals;

				public String get_024_semifinals () {
					return this._024_semifinals;
				}
				
			    public String _024_best_round;

				public String get_024_best_round () {
					return this._024_best_round;
				}
				
			    public String _025_matches_played;

				public String get_025_matches_played () {
					return this._025_matches_played;
				}
				
			    public String _025_matches_won;

				public String get_025_matches_won () {
					return this._025_matches_won;
				}
				
			    public String _025_win_pct;

				public String get_025_win_pct () {
					return this._025_win_pct;
				}
				
			    public String _025_sets_won;

				public String get_025_sets_won () {
					return this._025_sets_won;
				}
				
			    public String _025_sets_lost;

				public String get_025_sets_lost () {
					return this._025_sets_lost;
				}
				
			    public String _025_avg_sets_match;

				public String get_025_avg_sets_match () {
					return this._025_avg_sets_match;
				}
				
			    public String _025_games_won;

				public String get_025_games_won () {
					return this._025_games_won;
				}
				
			    public String _025_games_lost;

				public String get_025_games_lost () {
					return this._025_games_lost;
				}
				
			    public String _025_avg_games_match;

				public String get_025_avg_games_match () {
					return this._025_avg_games_match;
				}
				
			    public String _025_titles;

				public String get_025_titles () {
					return this._025_titles;
				}
				
			    public String _025_finals;

				public String get_025_finals () {
					return this._025_finals;
				}
				
			    public String _025_semifinals;

				public String get_025_semifinals () {
					return this._025_semifinals;
				}
				
			    public String _025_best_round;

				public String get_025_best_round () {
					return this._025_best_round;
				}
				
			    public String _026_matches_played;

				public String get_026_matches_played () {
					return this._026_matches_played;
				}
				
			    public String _026_matches_won;

				public String get_026_matches_won () {
					return this._026_matches_won;
				}
				
			    public String _026_win_pct;

				public String get_026_win_pct () {
					return this._026_win_pct;
				}
				
			    public String _026_sets_won;

				public String get_026_sets_won () {
					return this._026_sets_won;
				}
				
			    public String _026_sets_lost;

				public String get_026_sets_lost () {
					return this._026_sets_lost;
				}
				
			    public String _026_avg_sets_match;

				public String get_026_avg_sets_match () {
					return this._026_avg_sets_match;
				}
				
			    public String _026_games_won;

				public String get_026_games_won () {
					return this._026_games_won;
				}
				
			    public String _026_games_lost;

				public String get_026_games_lost () {
					return this._026_games_lost;
				}
				
			    public String _026_avg_games_match;

				public String get_026_avg_games_match () {
					return this._026_avg_games_match;
				}
				
			    public String _026_titles;

				public String get_026_titles () {
					return this._026_titles;
				}
				
			    public String _026_finals;

				public String get_026_finals () {
					return this._026_finals;
				}
				
			    public String _026_semifinals;

				public String get_026_semifinals () {
					return this._026_semifinals;
				}
				
			    public String _026_best_round;

				public String get_026_best_round () {
					return this._026_best_round;
				}
				
			    public String followers;

				public String getFollowers () {
					return this.followers;
				}
				
			    public String interactions;

				public String getInteractions () {
					return this.interactions;
				}
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_dim_player.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_player.length == 0) {
   					commonByteArray_DW_PADEL_dim_player = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_player = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_dim_player, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_player, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_dim_player.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_player.length == 0) {
   					commonByteArray_DW_PADEL_dim_player = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_player = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_dim_player, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_player, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_dim_player) {

        	try {

        		int length = 0;
		
					this.name = readString(dis);
					
					this.ranking = readString(dis);
					
					this.gender = readString(dis);
					
					this.nationality = readString(dis);
					
					this.total_points = readString(dis);
					
					this.height = readString(dis);
					
					this.birthdate = readString(dis);
					
					this.age = readString(dis);
					
					this.hand = readString(dis);
					
					this.side = readString(dis);
					
					this.partner = readString(dis);
					
					this._023_matches_played = readString(dis);
					
					this._023_matches_won = readString(dis);
					
					this._023_win_pct = readString(dis);
					
					this._023_sets_won = readString(dis);
					
					this._023_sets_lost = readString(dis);
					
					this._023_avg_sets_match = readString(dis);
					
					this._023_games_won = readString(dis);
					
					this._023_games_lost = readString(dis);
					
					this._023_avg_games_match = readString(dis);
					
					this._023_titles = readString(dis);
					
					this._023_finals = readString(dis);
					
					this._023_semifinals = readString(dis);
					
					this._023_best_round = readString(dis);
					
					this._024_matches_played = readString(dis);
					
					this._024_matches_won = readString(dis);
					
					this._024_win_pct = readString(dis);
					
					this._024_sets_won = readString(dis);
					
					this._024_sets_lost = readString(dis);
					
					this._024_avg_sets_match = readString(dis);
					
					this._024_games_won = readString(dis);
					
					this._024_games_lost = readString(dis);
					
					this._024_avg_games_match = readString(dis);
					
					this._024_titles = readString(dis);
					
					this._024_finals = readString(dis);
					
					this._024_semifinals = readString(dis);
					
					this._024_best_round = readString(dis);
					
					this._025_matches_played = readString(dis);
					
					this._025_matches_won = readString(dis);
					
					this._025_win_pct = readString(dis);
					
					this._025_sets_won = readString(dis);
					
					this._025_sets_lost = readString(dis);
					
					this._025_avg_sets_match = readString(dis);
					
					this._025_games_won = readString(dis);
					
					this._025_games_lost = readString(dis);
					
					this._025_avg_games_match = readString(dis);
					
					this._025_titles = readString(dis);
					
					this._025_finals = readString(dis);
					
					this._025_semifinals = readString(dis);
					
					this._025_best_round = readString(dis);
					
					this._026_matches_played = readString(dis);
					
					this._026_matches_won = readString(dis);
					
					this._026_win_pct = readString(dis);
					
					this._026_sets_won = readString(dis);
					
					this._026_sets_lost = readString(dis);
					
					this._026_avg_sets_match = readString(dis);
					
					this._026_games_won = readString(dis);
					
					this._026_games_lost = readString(dis);
					
					this._026_avg_games_match = readString(dis);
					
					this._026_titles = readString(dis);
					
					this._026_finals = readString(dis);
					
					this._026_semifinals = readString(dis);
					
					this._026_best_round = readString(dis);
					
					this.followers = readString(dis);
					
					this.interactions = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_dim_player) {

        	try {

        		int length = 0;
		
					this.name = readString(dis);
					
					this.ranking = readString(dis);
					
					this.gender = readString(dis);
					
					this.nationality = readString(dis);
					
					this.total_points = readString(dis);
					
					this.height = readString(dis);
					
					this.birthdate = readString(dis);
					
					this.age = readString(dis);
					
					this.hand = readString(dis);
					
					this.side = readString(dis);
					
					this.partner = readString(dis);
					
					this._023_matches_played = readString(dis);
					
					this._023_matches_won = readString(dis);
					
					this._023_win_pct = readString(dis);
					
					this._023_sets_won = readString(dis);
					
					this._023_sets_lost = readString(dis);
					
					this._023_avg_sets_match = readString(dis);
					
					this._023_games_won = readString(dis);
					
					this._023_games_lost = readString(dis);
					
					this._023_avg_games_match = readString(dis);
					
					this._023_titles = readString(dis);
					
					this._023_finals = readString(dis);
					
					this._023_semifinals = readString(dis);
					
					this._023_best_round = readString(dis);
					
					this._024_matches_played = readString(dis);
					
					this._024_matches_won = readString(dis);
					
					this._024_win_pct = readString(dis);
					
					this._024_sets_won = readString(dis);
					
					this._024_sets_lost = readString(dis);
					
					this._024_avg_sets_match = readString(dis);
					
					this._024_games_won = readString(dis);
					
					this._024_games_lost = readString(dis);
					
					this._024_avg_games_match = readString(dis);
					
					this._024_titles = readString(dis);
					
					this._024_finals = readString(dis);
					
					this._024_semifinals = readString(dis);
					
					this._024_best_round = readString(dis);
					
					this._025_matches_played = readString(dis);
					
					this._025_matches_won = readString(dis);
					
					this._025_win_pct = readString(dis);
					
					this._025_sets_won = readString(dis);
					
					this._025_sets_lost = readString(dis);
					
					this._025_avg_sets_match = readString(dis);
					
					this._025_games_won = readString(dis);
					
					this._025_games_lost = readString(dis);
					
					this._025_avg_games_match = readString(dis);
					
					this._025_titles = readString(dis);
					
					this._025_finals = readString(dis);
					
					this._025_semifinals = readString(dis);
					
					this._025_best_round = readString(dis);
					
					this._026_matches_played = readString(dis);
					
					this._026_matches_won = readString(dis);
					
					this._026_win_pct = readString(dis);
					
					this._026_sets_won = readString(dis);
					
					this._026_sets_lost = readString(dis);
					
					this._026_avg_sets_match = readString(dis);
					
					this._026_games_won = readString(dis);
					
					this._026_games_lost = readString(dis);
					
					this._026_avg_games_match = readString(dis);
					
					this._026_titles = readString(dis);
					
					this._026_finals = readString(dis);
					
					this._026_semifinals = readString(dis);
					
					this._026_best_round = readString(dis);
					
					this.followers = readString(dis);
					
					this.interactions = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.name,dos);
					
					// String
				
						writeString(this.ranking,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.nationality,dos);
					
					// String
				
						writeString(this.total_points,dos);
					
					// String
				
						writeString(this.height,dos);
					
					// String
				
						writeString(this.birthdate,dos);
					
					// String
				
						writeString(this.age,dos);
					
					// String
				
						writeString(this.hand,dos);
					
					// String
				
						writeString(this.side,dos);
					
					// String
				
						writeString(this.partner,dos);
					
					// String
				
						writeString(this._023_matches_played,dos);
					
					// String
				
						writeString(this._023_matches_won,dos);
					
					// String
				
						writeString(this._023_win_pct,dos);
					
					// String
				
						writeString(this._023_sets_won,dos);
					
					// String
				
						writeString(this._023_sets_lost,dos);
					
					// String
				
						writeString(this._023_avg_sets_match,dos);
					
					// String
				
						writeString(this._023_games_won,dos);
					
					// String
				
						writeString(this._023_games_lost,dos);
					
					// String
				
						writeString(this._023_avg_games_match,dos);
					
					// String
				
						writeString(this._023_titles,dos);
					
					// String
				
						writeString(this._023_finals,dos);
					
					// String
				
						writeString(this._023_semifinals,dos);
					
					// String
				
						writeString(this._023_best_round,dos);
					
					// String
				
						writeString(this._024_matches_played,dos);
					
					// String
				
						writeString(this._024_matches_won,dos);
					
					// String
				
						writeString(this._024_win_pct,dos);
					
					// String
				
						writeString(this._024_sets_won,dos);
					
					// String
				
						writeString(this._024_sets_lost,dos);
					
					// String
				
						writeString(this._024_avg_sets_match,dos);
					
					// String
				
						writeString(this._024_games_won,dos);
					
					// String
				
						writeString(this._024_games_lost,dos);
					
					// String
				
						writeString(this._024_avg_games_match,dos);
					
					// String
				
						writeString(this._024_titles,dos);
					
					// String
				
						writeString(this._024_finals,dos);
					
					// String
				
						writeString(this._024_semifinals,dos);
					
					// String
				
						writeString(this._024_best_round,dos);
					
					// String
				
						writeString(this._025_matches_played,dos);
					
					// String
				
						writeString(this._025_matches_won,dos);
					
					// String
				
						writeString(this._025_win_pct,dos);
					
					// String
				
						writeString(this._025_sets_won,dos);
					
					// String
				
						writeString(this._025_sets_lost,dos);
					
					// String
				
						writeString(this._025_avg_sets_match,dos);
					
					// String
				
						writeString(this._025_games_won,dos);
					
					// String
				
						writeString(this._025_games_lost,dos);
					
					// String
				
						writeString(this._025_avg_games_match,dos);
					
					// String
				
						writeString(this._025_titles,dos);
					
					// String
				
						writeString(this._025_finals,dos);
					
					// String
				
						writeString(this._025_semifinals,dos);
					
					// String
				
						writeString(this._025_best_round,dos);
					
					// String
				
						writeString(this._026_matches_played,dos);
					
					// String
				
						writeString(this._026_matches_won,dos);
					
					// String
				
						writeString(this._026_win_pct,dos);
					
					// String
				
						writeString(this._026_sets_won,dos);
					
					// String
				
						writeString(this._026_sets_lost,dos);
					
					// String
				
						writeString(this._026_avg_sets_match,dos);
					
					// String
				
						writeString(this._026_games_won,dos);
					
					// String
				
						writeString(this._026_games_lost,dos);
					
					// String
				
						writeString(this._026_avg_games_match,dos);
					
					// String
				
						writeString(this._026_titles,dos);
					
					// String
				
						writeString(this._026_finals,dos);
					
					// String
				
						writeString(this._026_semifinals,dos);
					
					// String
				
						writeString(this._026_best_round,dos);
					
					// String
				
						writeString(this.followers,dos);
					
					// String
				
						writeString(this.interactions,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.name,dos);
					
					// String
				
						writeString(this.ranking,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.nationality,dos);
					
					// String
				
						writeString(this.total_points,dos);
					
					// String
				
						writeString(this.height,dos);
					
					// String
				
						writeString(this.birthdate,dos);
					
					// String
				
						writeString(this.age,dos);
					
					// String
				
						writeString(this.hand,dos);
					
					// String
				
						writeString(this.side,dos);
					
					// String
				
						writeString(this.partner,dos);
					
					// String
				
						writeString(this._023_matches_played,dos);
					
					// String
				
						writeString(this._023_matches_won,dos);
					
					// String
				
						writeString(this._023_win_pct,dos);
					
					// String
				
						writeString(this._023_sets_won,dos);
					
					// String
				
						writeString(this._023_sets_lost,dos);
					
					// String
				
						writeString(this._023_avg_sets_match,dos);
					
					// String
				
						writeString(this._023_games_won,dos);
					
					// String
				
						writeString(this._023_games_lost,dos);
					
					// String
				
						writeString(this._023_avg_games_match,dos);
					
					// String
				
						writeString(this._023_titles,dos);
					
					// String
				
						writeString(this._023_finals,dos);
					
					// String
				
						writeString(this._023_semifinals,dos);
					
					// String
				
						writeString(this._023_best_round,dos);
					
					// String
				
						writeString(this._024_matches_played,dos);
					
					// String
				
						writeString(this._024_matches_won,dos);
					
					// String
				
						writeString(this._024_win_pct,dos);
					
					// String
				
						writeString(this._024_sets_won,dos);
					
					// String
				
						writeString(this._024_sets_lost,dos);
					
					// String
				
						writeString(this._024_avg_sets_match,dos);
					
					// String
				
						writeString(this._024_games_won,dos);
					
					// String
				
						writeString(this._024_games_lost,dos);
					
					// String
				
						writeString(this._024_avg_games_match,dos);
					
					// String
				
						writeString(this._024_titles,dos);
					
					// String
				
						writeString(this._024_finals,dos);
					
					// String
				
						writeString(this._024_semifinals,dos);
					
					// String
				
						writeString(this._024_best_round,dos);
					
					// String
				
						writeString(this._025_matches_played,dos);
					
					// String
				
						writeString(this._025_matches_won,dos);
					
					// String
				
						writeString(this._025_win_pct,dos);
					
					// String
				
						writeString(this._025_sets_won,dos);
					
					// String
				
						writeString(this._025_sets_lost,dos);
					
					// String
				
						writeString(this._025_avg_sets_match,dos);
					
					// String
				
						writeString(this._025_games_won,dos);
					
					// String
				
						writeString(this._025_games_lost,dos);
					
					// String
				
						writeString(this._025_avg_games_match,dos);
					
					// String
				
						writeString(this._025_titles,dos);
					
					// String
				
						writeString(this._025_finals,dos);
					
					// String
				
						writeString(this._025_semifinals,dos);
					
					// String
				
						writeString(this._025_best_round,dos);
					
					// String
				
						writeString(this._026_matches_played,dos);
					
					// String
				
						writeString(this._026_matches_won,dos);
					
					// String
				
						writeString(this._026_win_pct,dos);
					
					// String
				
						writeString(this._026_sets_won,dos);
					
					// String
				
						writeString(this._026_sets_lost,dos);
					
					// String
				
						writeString(this._026_avg_sets_match,dos);
					
					// String
				
						writeString(this._026_games_won,dos);
					
					// String
				
						writeString(this._026_games_lost,dos);
					
					// String
				
						writeString(this._026_avg_games_match,dos);
					
					// String
				
						writeString(this._026_titles,dos);
					
					// String
				
						writeString(this._026_finals,dos);
					
					// String
				
						writeString(this._026_semifinals,dos);
					
					// String
				
						writeString(this._026_best_round,dos);
					
					// String
				
						writeString(this.followers,dos);
					
					// String
				
						writeString(this.interactions,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("name="+name);
		sb.append(",ranking="+ranking);
		sb.append(",gender="+gender);
		sb.append(",nationality="+nationality);
		sb.append(",total_points="+total_points);
		sb.append(",height="+height);
		sb.append(",birthdate="+birthdate);
		sb.append(",age="+age);
		sb.append(",hand="+hand);
		sb.append(",side="+side);
		sb.append(",partner="+partner);
		sb.append(",_023_matches_played="+_023_matches_played);
		sb.append(",_023_matches_won="+_023_matches_won);
		sb.append(",_023_win_pct="+_023_win_pct);
		sb.append(",_023_sets_won="+_023_sets_won);
		sb.append(",_023_sets_lost="+_023_sets_lost);
		sb.append(",_023_avg_sets_match="+_023_avg_sets_match);
		sb.append(",_023_games_won="+_023_games_won);
		sb.append(",_023_games_lost="+_023_games_lost);
		sb.append(",_023_avg_games_match="+_023_avg_games_match);
		sb.append(",_023_titles="+_023_titles);
		sb.append(",_023_finals="+_023_finals);
		sb.append(",_023_semifinals="+_023_semifinals);
		sb.append(",_023_best_round="+_023_best_round);
		sb.append(",_024_matches_played="+_024_matches_played);
		sb.append(",_024_matches_won="+_024_matches_won);
		sb.append(",_024_win_pct="+_024_win_pct);
		sb.append(",_024_sets_won="+_024_sets_won);
		sb.append(",_024_sets_lost="+_024_sets_lost);
		sb.append(",_024_avg_sets_match="+_024_avg_sets_match);
		sb.append(",_024_games_won="+_024_games_won);
		sb.append(",_024_games_lost="+_024_games_lost);
		sb.append(",_024_avg_games_match="+_024_avg_games_match);
		sb.append(",_024_titles="+_024_titles);
		sb.append(",_024_finals="+_024_finals);
		sb.append(",_024_semifinals="+_024_semifinals);
		sb.append(",_024_best_round="+_024_best_round);
		sb.append(",_025_matches_played="+_025_matches_played);
		sb.append(",_025_matches_won="+_025_matches_won);
		sb.append(",_025_win_pct="+_025_win_pct);
		sb.append(",_025_sets_won="+_025_sets_won);
		sb.append(",_025_sets_lost="+_025_sets_lost);
		sb.append(",_025_avg_sets_match="+_025_avg_sets_match);
		sb.append(",_025_games_won="+_025_games_won);
		sb.append(",_025_games_lost="+_025_games_lost);
		sb.append(",_025_avg_games_match="+_025_avg_games_match);
		sb.append(",_025_titles="+_025_titles);
		sb.append(",_025_finals="+_025_finals);
		sb.append(",_025_semifinals="+_025_semifinals);
		sb.append(",_025_best_round="+_025_best_round);
		sb.append(",_026_matches_played="+_026_matches_played);
		sb.append(",_026_matches_won="+_026_matches_won);
		sb.append(",_026_win_pct="+_026_win_pct);
		sb.append(",_026_sets_won="+_026_sets_won);
		sb.append(",_026_sets_lost="+_026_sets_lost);
		sb.append(",_026_avg_sets_match="+_026_avg_sets_match);
		sb.append(",_026_games_won="+_026_games_won);
		sb.append(",_026_games_lost="+_026_games_lost);
		sb.append(",_026_avg_games_match="+_026_avg_games_match);
		sb.append(",_026_titles="+_026_titles);
		sb.append(",_026_finals="+_026_finals);
		sb.append(",_026_semifinals="+_026_semifinals);
		sb.append(",_026_best_round="+_026_best_round);
		sb.append(",followers="+followers);
		sb.append(",interactions="+interactions);
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

public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_dim_player = new byte[0];
    static byte[] commonByteArray_DW_PADEL_dim_player = new byte[0];

	
			    public String name;

				public String getName () {
					return this.name;
				}
				
			    public String ranking;

				public String getRanking () {
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
				
			    public String total_points;

				public String getTotal_points () {
					return this.total_points;
				}
				
			    public String height;

				public String getHeight () {
					return this.height;
				}
				
			    public String birthdate;

				public String getBirthdate () {
					return this.birthdate;
				}
				
			    public String age;

				public String getAge () {
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
				
			    public String _023_matches_played;

				public String get_023_matches_played () {
					return this._023_matches_played;
				}
				
			    public String _023_matches_won;

				public String get_023_matches_won () {
					return this._023_matches_won;
				}
				
			    public String _023_win_pct;

				public String get_023_win_pct () {
					return this._023_win_pct;
				}
				
			    public String _023_sets_won;

				public String get_023_sets_won () {
					return this._023_sets_won;
				}
				
			    public String _023_sets_lost;

				public String get_023_sets_lost () {
					return this._023_sets_lost;
				}
				
			    public String _023_avg_sets_match;

				public String get_023_avg_sets_match () {
					return this._023_avg_sets_match;
				}
				
			    public String _023_games_won;

				public String get_023_games_won () {
					return this._023_games_won;
				}
				
			    public String _023_games_lost;

				public String get_023_games_lost () {
					return this._023_games_lost;
				}
				
			    public String _023_avg_games_match;

				public String get_023_avg_games_match () {
					return this._023_avg_games_match;
				}
				
			    public String _023_titles;

				public String get_023_titles () {
					return this._023_titles;
				}
				
			    public String _023_finals;

				public String get_023_finals () {
					return this._023_finals;
				}
				
			    public String _023_semifinals;

				public String get_023_semifinals () {
					return this._023_semifinals;
				}
				
			    public String _023_best_round;

				public String get_023_best_round () {
					return this._023_best_round;
				}
				
			    public String _024_matches_played;

				public String get_024_matches_played () {
					return this._024_matches_played;
				}
				
			    public String _024_matches_won;

				public String get_024_matches_won () {
					return this._024_matches_won;
				}
				
			    public String _024_win_pct;

				public String get_024_win_pct () {
					return this._024_win_pct;
				}
				
			    public String _024_sets_won;

				public String get_024_sets_won () {
					return this._024_sets_won;
				}
				
			    public String _024_sets_lost;

				public String get_024_sets_lost () {
					return this._024_sets_lost;
				}
				
			    public String _024_avg_sets_match;

				public String get_024_avg_sets_match () {
					return this._024_avg_sets_match;
				}
				
			    public String _024_games_won;

				public String get_024_games_won () {
					return this._024_games_won;
				}
				
			    public String _024_games_lost;

				public String get_024_games_lost () {
					return this._024_games_lost;
				}
				
			    public String _024_avg_games_match;

				public String get_024_avg_games_match () {
					return this._024_avg_games_match;
				}
				
			    public String _024_titles;

				public String get_024_titles () {
					return this._024_titles;
				}
				
			    public String _024_finals;

				public String get_024_finals () {
					return this._024_finals;
				}
				
			    public String _024_semifinals;

				public String get_024_semifinals () {
					return this._024_semifinals;
				}
				
			    public String _024_best_round;

				public String get_024_best_round () {
					return this._024_best_round;
				}
				
			    public String _025_matches_played;

				public String get_025_matches_played () {
					return this._025_matches_played;
				}
				
			    public String _025_matches_won;

				public String get_025_matches_won () {
					return this._025_matches_won;
				}
				
			    public String _025_win_pct;

				public String get_025_win_pct () {
					return this._025_win_pct;
				}
				
			    public String _025_sets_won;

				public String get_025_sets_won () {
					return this._025_sets_won;
				}
				
			    public String _025_sets_lost;

				public String get_025_sets_lost () {
					return this._025_sets_lost;
				}
				
			    public String _025_avg_sets_match;

				public String get_025_avg_sets_match () {
					return this._025_avg_sets_match;
				}
				
			    public String _025_games_won;

				public String get_025_games_won () {
					return this._025_games_won;
				}
				
			    public String _025_games_lost;

				public String get_025_games_lost () {
					return this._025_games_lost;
				}
				
			    public String _025_avg_games_match;

				public String get_025_avg_games_match () {
					return this._025_avg_games_match;
				}
				
			    public String _025_titles;

				public String get_025_titles () {
					return this._025_titles;
				}
				
			    public String _025_finals;

				public String get_025_finals () {
					return this._025_finals;
				}
				
			    public String _025_semifinals;

				public String get_025_semifinals () {
					return this._025_semifinals;
				}
				
			    public String _025_best_round;

				public String get_025_best_round () {
					return this._025_best_round;
				}
				
			    public String _026_matches_played;

				public String get_026_matches_played () {
					return this._026_matches_played;
				}
				
			    public String _026_matches_won;

				public String get_026_matches_won () {
					return this._026_matches_won;
				}
				
			    public String _026_win_pct;

				public String get_026_win_pct () {
					return this._026_win_pct;
				}
				
			    public String _026_sets_won;

				public String get_026_sets_won () {
					return this._026_sets_won;
				}
				
			    public String _026_sets_lost;

				public String get_026_sets_lost () {
					return this._026_sets_lost;
				}
				
			    public String _026_avg_sets_match;

				public String get_026_avg_sets_match () {
					return this._026_avg_sets_match;
				}
				
			    public String _026_games_won;

				public String get_026_games_won () {
					return this._026_games_won;
				}
				
			    public String _026_games_lost;

				public String get_026_games_lost () {
					return this._026_games_lost;
				}
				
			    public String _026_avg_games_match;

				public String get_026_avg_games_match () {
					return this._026_avg_games_match;
				}
				
			    public String _026_titles;

				public String get_026_titles () {
					return this._026_titles;
				}
				
			    public String _026_finals;

				public String get_026_finals () {
					return this._026_finals;
				}
				
			    public String _026_semifinals;

				public String get_026_semifinals () {
					return this._026_semifinals;
				}
				
			    public String _026_best_round;

				public String get_026_best_round () {
					return this._026_best_round;
				}
				
			    public String followers;

				public String getFollowers () {
					return this.followers;
				}
				
			    public String interactions;

				public String getInteractions () {
					return this.interactions;
				}
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_dim_player.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_player.length == 0) {
   					commonByteArray_DW_PADEL_dim_player = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_player = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_dim_player, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_player, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_dim_player.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_player.length == 0) {
   					commonByteArray_DW_PADEL_dim_player = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_player = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_dim_player, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_player, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_dim_player) {

        	try {

        		int length = 0;
		
					this.name = readString(dis);
					
					this.ranking = readString(dis);
					
					this.gender = readString(dis);
					
					this.nationality = readString(dis);
					
					this.total_points = readString(dis);
					
					this.height = readString(dis);
					
					this.birthdate = readString(dis);
					
					this.age = readString(dis);
					
					this.hand = readString(dis);
					
					this.side = readString(dis);
					
					this.partner = readString(dis);
					
					this._023_matches_played = readString(dis);
					
					this._023_matches_won = readString(dis);
					
					this._023_win_pct = readString(dis);
					
					this._023_sets_won = readString(dis);
					
					this._023_sets_lost = readString(dis);
					
					this._023_avg_sets_match = readString(dis);
					
					this._023_games_won = readString(dis);
					
					this._023_games_lost = readString(dis);
					
					this._023_avg_games_match = readString(dis);
					
					this._023_titles = readString(dis);
					
					this._023_finals = readString(dis);
					
					this._023_semifinals = readString(dis);
					
					this._023_best_round = readString(dis);
					
					this._024_matches_played = readString(dis);
					
					this._024_matches_won = readString(dis);
					
					this._024_win_pct = readString(dis);
					
					this._024_sets_won = readString(dis);
					
					this._024_sets_lost = readString(dis);
					
					this._024_avg_sets_match = readString(dis);
					
					this._024_games_won = readString(dis);
					
					this._024_games_lost = readString(dis);
					
					this._024_avg_games_match = readString(dis);
					
					this._024_titles = readString(dis);
					
					this._024_finals = readString(dis);
					
					this._024_semifinals = readString(dis);
					
					this._024_best_round = readString(dis);
					
					this._025_matches_played = readString(dis);
					
					this._025_matches_won = readString(dis);
					
					this._025_win_pct = readString(dis);
					
					this._025_sets_won = readString(dis);
					
					this._025_sets_lost = readString(dis);
					
					this._025_avg_sets_match = readString(dis);
					
					this._025_games_won = readString(dis);
					
					this._025_games_lost = readString(dis);
					
					this._025_avg_games_match = readString(dis);
					
					this._025_titles = readString(dis);
					
					this._025_finals = readString(dis);
					
					this._025_semifinals = readString(dis);
					
					this._025_best_round = readString(dis);
					
					this._026_matches_played = readString(dis);
					
					this._026_matches_won = readString(dis);
					
					this._026_win_pct = readString(dis);
					
					this._026_sets_won = readString(dis);
					
					this._026_sets_lost = readString(dis);
					
					this._026_avg_sets_match = readString(dis);
					
					this._026_games_won = readString(dis);
					
					this._026_games_lost = readString(dis);
					
					this._026_avg_games_match = readString(dis);
					
					this._026_titles = readString(dis);
					
					this._026_finals = readString(dis);
					
					this._026_semifinals = readString(dis);
					
					this._026_best_round = readString(dis);
					
					this.followers = readString(dis);
					
					this.interactions = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_dim_player) {

        	try {

        		int length = 0;
		
					this.name = readString(dis);
					
					this.ranking = readString(dis);
					
					this.gender = readString(dis);
					
					this.nationality = readString(dis);
					
					this.total_points = readString(dis);
					
					this.height = readString(dis);
					
					this.birthdate = readString(dis);
					
					this.age = readString(dis);
					
					this.hand = readString(dis);
					
					this.side = readString(dis);
					
					this.partner = readString(dis);
					
					this._023_matches_played = readString(dis);
					
					this._023_matches_won = readString(dis);
					
					this._023_win_pct = readString(dis);
					
					this._023_sets_won = readString(dis);
					
					this._023_sets_lost = readString(dis);
					
					this._023_avg_sets_match = readString(dis);
					
					this._023_games_won = readString(dis);
					
					this._023_games_lost = readString(dis);
					
					this._023_avg_games_match = readString(dis);
					
					this._023_titles = readString(dis);
					
					this._023_finals = readString(dis);
					
					this._023_semifinals = readString(dis);
					
					this._023_best_round = readString(dis);
					
					this._024_matches_played = readString(dis);
					
					this._024_matches_won = readString(dis);
					
					this._024_win_pct = readString(dis);
					
					this._024_sets_won = readString(dis);
					
					this._024_sets_lost = readString(dis);
					
					this._024_avg_sets_match = readString(dis);
					
					this._024_games_won = readString(dis);
					
					this._024_games_lost = readString(dis);
					
					this._024_avg_games_match = readString(dis);
					
					this._024_titles = readString(dis);
					
					this._024_finals = readString(dis);
					
					this._024_semifinals = readString(dis);
					
					this._024_best_round = readString(dis);
					
					this._025_matches_played = readString(dis);
					
					this._025_matches_won = readString(dis);
					
					this._025_win_pct = readString(dis);
					
					this._025_sets_won = readString(dis);
					
					this._025_sets_lost = readString(dis);
					
					this._025_avg_sets_match = readString(dis);
					
					this._025_games_won = readString(dis);
					
					this._025_games_lost = readString(dis);
					
					this._025_avg_games_match = readString(dis);
					
					this._025_titles = readString(dis);
					
					this._025_finals = readString(dis);
					
					this._025_semifinals = readString(dis);
					
					this._025_best_round = readString(dis);
					
					this._026_matches_played = readString(dis);
					
					this._026_matches_won = readString(dis);
					
					this._026_win_pct = readString(dis);
					
					this._026_sets_won = readString(dis);
					
					this._026_sets_lost = readString(dis);
					
					this._026_avg_sets_match = readString(dis);
					
					this._026_games_won = readString(dis);
					
					this._026_games_lost = readString(dis);
					
					this._026_avg_games_match = readString(dis);
					
					this._026_titles = readString(dis);
					
					this._026_finals = readString(dis);
					
					this._026_semifinals = readString(dis);
					
					this._026_best_round = readString(dis);
					
					this.followers = readString(dis);
					
					this.interactions = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.name,dos);
					
					// String
				
						writeString(this.ranking,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.nationality,dos);
					
					// String
				
						writeString(this.total_points,dos);
					
					// String
				
						writeString(this.height,dos);
					
					// String
				
						writeString(this.birthdate,dos);
					
					// String
				
						writeString(this.age,dos);
					
					// String
				
						writeString(this.hand,dos);
					
					// String
				
						writeString(this.side,dos);
					
					// String
				
						writeString(this.partner,dos);
					
					// String
				
						writeString(this._023_matches_played,dos);
					
					// String
				
						writeString(this._023_matches_won,dos);
					
					// String
				
						writeString(this._023_win_pct,dos);
					
					// String
				
						writeString(this._023_sets_won,dos);
					
					// String
				
						writeString(this._023_sets_lost,dos);
					
					// String
				
						writeString(this._023_avg_sets_match,dos);
					
					// String
				
						writeString(this._023_games_won,dos);
					
					// String
				
						writeString(this._023_games_lost,dos);
					
					// String
				
						writeString(this._023_avg_games_match,dos);
					
					// String
				
						writeString(this._023_titles,dos);
					
					// String
				
						writeString(this._023_finals,dos);
					
					// String
				
						writeString(this._023_semifinals,dos);
					
					// String
				
						writeString(this._023_best_round,dos);
					
					// String
				
						writeString(this._024_matches_played,dos);
					
					// String
				
						writeString(this._024_matches_won,dos);
					
					// String
				
						writeString(this._024_win_pct,dos);
					
					// String
				
						writeString(this._024_sets_won,dos);
					
					// String
				
						writeString(this._024_sets_lost,dos);
					
					// String
				
						writeString(this._024_avg_sets_match,dos);
					
					// String
				
						writeString(this._024_games_won,dos);
					
					// String
				
						writeString(this._024_games_lost,dos);
					
					// String
				
						writeString(this._024_avg_games_match,dos);
					
					// String
				
						writeString(this._024_titles,dos);
					
					// String
				
						writeString(this._024_finals,dos);
					
					// String
				
						writeString(this._024_semifinals,dos);
					
					// String
				
						writeString(this._024_best_round,dos);
					
					// String
				
						writeString(this._025_matches_played,dos);
					
					// String
				
						writeString(this._025_matches_won,dos);
					
					// String
				
						writeString(this._025_win_pct,dos);
					
					// String
				
						writeString(this._025_sets_won,dos);
					
					// String
				
						writeString(this._025_sets_lost,dos);
					
					// String
				
						writeString(this._025_avg_sets_match,dos);
					
					// String
				
						writeString(this._025_games_won,dos);
					
					// String
				
						writeString(this._025_games_lost,dos);
					
					// String
				
						writeString(this._025_avg_games_match,dos);
					
					// String
				
						writeString(this._025_titles,dos);
					
					// String
				
						writeString(this._025_finals,dos);
					
					// String
				
						writeString(this._025_semifinals,dos);
					
					// String
				
						writeString(this._025_best_round,dos);
					
					// String
				
						writeString(this._026_matches_played,dos);
					
					// String
				
						writeString(this._026_matches_won,dos);
					
					// String
				
						writeString(this._026_win_pct,dos);
					
					// String
				
						writeString(this._026_sets_won,dos);
					
					// String
				
						writeString(this._026_sets_lost,dos);
					
					// String
				
						writeString(this._026_avg_sets_match,dos);
					
					// String
				
						writeString(this._026_games_won,dos);
					
					// String
				
						writeString(this._026_games_lost,dos);
					
					// String
				
						writeString(this._026_avg_games_match,dos);
					
					// String
				
						writeString(this._026_titles,dos);
					
					// String
				
						writeString(this._026_finals,dos);
					
					// String
				
						writeString(this._026_semifinals,dos);
					
					// String
				
						writeString(this._026_best_round,dos);
					
					// String
				
						writeString(this.followers,dos);
					
					// String
				
						writeString(this.interactions,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.name,dos);
					
					// String
				
						writeString(this.ranking,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.nationality,dos);
					
					// String
				
						writeString(this.total_points,dos);
					
					// String
				
						writeString(this.height,dos);
					
					// String
				
						writeString(this.birthdate,dos);
					
					// String
				
						writeString(this.age,dos);
					
					// String
				
						writeString(this.hand,dos);
					
					// String
				
						writeString(this.side,dos);
					
					// String
				
						writeString(this.partner,dos);
					
					// String
				
						writeString(this._023_matches_played,dos);
					
					// String
				
						writeString(this._023_matches_won,dos);
					
					// String
				
						writeString(this._023_win_pct,dos);
					
					// String
				
						writeString(this._023_sets_won,dos);
					
					// String
				
						writeString(this._023_sets_lost,dos);
					
					// String
				
						writeString(this._023_avg_sets_match,dos);
					
					// String
				
						writeString(this._023_games_won,dos);
					
					// String
				
						writeString(this._023_games_lost,dos);
					
					// String
				
						writeString(this._023_avg_games_match,dos);
					
					// String
				
						writeString(this._023_titles,dos);
					
					// String
				
						writeString(this._023_finals,dos);
					
					// String
				
						writeString(this._023_semifinals,dos);
					
					// String
				
						writeString(this._023_best_round,dos);
					
					// String
				
						writeString(this._024_matches_played,dos);
					
					// String
				
						writeString(this._024_matches_won,dos);
					
					// String
				
						writeString(this._024_win_pct,dos);
					
					// String
				
						writeString(this._024_sets_won,dos);
					
					// String
				
						writeString(this._024_sets_lost,dos);
					
					// String
				
						writeString(this._024_avg_sets_match,dos);
					
					// String
				
						writeString(this._024_games_won,dos);
					
					// String
				
						writeString(this._024_games_lost,dos);
					
					// String
				
						writeString(this._024_avg_games_match,dos);
					
					// String
				
						writeString(this._024_titles,dos);
					
					// String
				
						writeString(this._024_finals,dos);
					
					// String
				
						writeString(this._024_semifinals,dos);
					
					// String
				
						writeString(this._024_best_round,dos);
					
					// String
				
						writeString(this._025_matches_played,dos);
					
					// String
				
						writeString(this._025_matches_won,dos);
					
					// String
				
						writeString(this._025_win_pct,dos);
					
					// String
				
						writeString(this._025_sets_won,dos);
					
					// String
				
						writeString(this._025_sets_lost,dos);
					
					// String
				
						writeString(this._025_avg_sets_match,dos);
					
					// String
				
						writeString(this._025_games_won,dos);
					
					// String
				
						writeString(this._025_games_lost,dos);
					
					// String
				
						writeString(this._025_avg_games_match,dos);
					
					// String
				
						writeString(this._025_titles,dos);
					
					// String
				
						writeString(this._025_finals,dos);
					
					// String
				
						writeString(this._025_semifinals,dos);
					
					// String
				
						writeString(this._025_best_round,dos);
					
					// String
				
						writeString(this._026_matches_played,dos);
					
					// String
				
						writeString(this._026_matches_won,dos);
					
					// String
				
						writeString(this._026_win_pct,dos);
					
					// String
				
						writeString(this._026_sets_won,dos);
					
					// String
				
						writeString(this._026_sets_lost,dos);
					
					// String
				
						writeString(this._026_avg_sets_match,dos);
					
					// String
				
						writeString(this._026_games_won,dos);
					
					// String
				
						writeString(this._026_games_lost,dos);
					
					// String
				
						writeString(this._026_avg_games_match,dos);
					
					// String
				
						writeString(this._026_titles,dos);
					
					// String
				
						writeString(this._026_finals,dos);
					
					// String
				
						writeString(this._026_semifinals,dos);
					
					// String
				
						writeString(this._026_best_round,dos);
					
					// String
				
						writeString(this.followers,dos);
					
					// String
				
						writeString(this.interactions,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("name="+name);
		sb.append(",ranking="+ranking);
		sb.append(",gender="+gender);
		sb.append(",nationality="+nationality);
		sb.append(",total_points="+total_points);
		sb.append(",height="+height);
		sb.append(",birthdate="+birthdate);
		sb.append(",age="+age);
		sb.append(",hand="+hand);
		sb.append(",side="+side);
		sb.append(",partner="+partner);
		sb.append(",_023_matches_played="+_023_matches_played);
		sb.append(",_023_matches_won="+_023_matches_won);
		sb.append(",_023_win_pct="+_023_win_pct);
		sb.append(",_023_sets_won="+_023_sets_won);
		sb.append(",_023_sets_lost="+_023_sets_lost);
		sb.append(",_023_avg_sets_match="+_023_avg_sets_match);
		sb.append(",_023_games_won="+_023_games_won);
		sb.append(",_023_games_lost="+_023_games_lost);
		sb.append(",_023_avg_games_match="+_023_avg_games_match);
		sb.append(",_023_titles="+_023_titles);
		sb.append(",_023_finals="+_023_finals);
		sb.append(",_023_semifinals="+_023_semifinals);
		sb.append(",_023_best_round="+_023_best_round);
		sb.append(",_024_matches_played="+_024_matches_played);
		sb.append(",_024_matches_won="+_024_matches_won);
		sb.append(",_024_win_pct="+_024_win_pct);
		sb.append(",_024_sets_won="+_024_sets_won);
		sb.append(",_024_sets_lost="+_024_sets_lost);
		sb.append(",_024_avg_sets_match="+_024_avg_sets_match);
		sb.append(",_024_games_won="+_024_games_won);
		sb.append(",_024_games_lost="+_024_games_lost);
		sb.append(",_024_avg_games_match="+_024_avg_games_match);
		sb.append(",_024_titles="+_024_titles);
		sb.append(",_024_finals="+_024_finals);
		sb.append(",_024_semifinals="+_024_semifinals);
		sb.append(",_024_best_round="+_024_best_round);
		sb.append(",_025_matches_played="+_025_matches_played);
		sb.append(",_025_matches_won="+_025_matches_won);
		sb.append(",_025_win_pct="+_025_win_pct);
		sb.append(",_025_sets_won="+_025_sets_won);
		sb.append(",_025_sets_lost="+_025_sets_lost);
		sb.append(",_025_avg_sets_match="+_025_avg_sets_match);
		sb.append(",_025_games_won="+_025_games_won);
		sb.append(",_025_games_lost="+_025_games_lost);
		sb.append(",_025_avg_games_match="+_025_avg_games_match);
		sb.append(",_025_titles="+_025_titles);
		sb.append(",_025_finals="+_025_finals);
		sb.append(",_025_semifinals="+_025_semifinals);
		sb.append(",_025_best_round="+_025_best_round);
		sb.append(",_026_matches_played="+_026_matches_played);
		sb.append(",_026_matches_won="+_026_matches_won);
		sb.append(",_026_win_pct="+_026_win_pct);
		sb.append(",_026_sets_won="+_026_sets_won);
		sb.append(",_026_sets_lost="+_026_sets_lost);
		sb.append(",_026_avg_sets_match="+_026_avg_sets_match);
		sb.append(",_026_games_won="+_026_games_won);
		sb.append(",_026_games_lost="+_026_games_lost);
		sb.append(",_026_avg_games_match="+_026_avg_games_match);
		sb.append(",_026_titles="+_026_titles);
		sb.append(",_026_finals="+_026_finals);
		sb.append(",_026_semifinals="+_026_semifinals);
		sb.append(",_026_best_round="+_026_best_round);
		sb.append(",followers="+followers);
		sb.append(",interactions="+interactions);
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
    final static byte[] commonByteArrayLock_DW_PADEL_dim_player = new byte[0];
    static byte[] commonByteArray_DW_PADEL_dim_player = new byte[0];

	
			    public String name;

				public String getName () {
					return this.name;
				}
				
			    public String ranking;

				public String getRanking () {
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
				
			    public String total_points;

				public String getTotal_points () {
					return this.total_points;
				}
				
			    public String height;

				public String getHeight () {
					return this.height;
				}
				
			    public String birthdate;

				public String getBirthdate () {
					return this.birthdate;
				}
				
			    public String age;

				public String getAge () {
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
				
			    public String _023_matches_played;

				public String get_023_matches_played () {
					return this._023_matches_played;
				}
				
			    public String _023_matches_won;

				public String get_023_matches_won () {
					return this._023_matches_won;
				}
				
			    public String _023_win_pct;

				public String get_023_win_pct () {
					return this._023_win_pct;
				}
				
			    public String _023_sets_won;

				public String get_023_sets_won () {
					return this._023_sets_won;
				}
				
			    public String _023_sets_lost;

				public String get_023_sets_lost () {
					return this._023_sets_lost;
				}
				
			    public String _023_avg_sets_match;

				public String get_023_avg_sets_match () {
					return this._023_avg_sets_match;
				}
				
			    public String _023_games_won;

				public String get_023_games_won () {
					return this._023_games_won;
				}
				
			    public String _023_games_lost;

				public String get_023_games_lost () {
					return this._023_games_lost;
				}
				
			    public String _023_avg_games_match;

				public String get_023_avg_games_match () {
					return this._023_avg_games_match;
				}
				
			    public String _023_titles;

				public String get_023_titles () {
					return this._023_titles;
				}
				
			    public String _023_finals;

				public String get_023_finals () {
					return this._023_finals;
				}
				
			    public String _023_semifinals;

				public String get_023_semifinals () {
					return this._023_semifinals;
				}
				
			    public String _023_best_round;

				public String get_023_best_round () {
					return this._023_best_round;
				}
				
			    public String _024_matches_played;

				public String get_024_matches_played () {
					return this._024_matches_played;
				}
				
			    public String _024_matches_won;

				public String get_024_matches_won () {
					return this._024_matches_won;
				}
				
			    public String _024_win_pct;

				public String get_024_win_pct () {
					return this._024_win_pct;
				}
				
			    public String _024_sets_won;

				public String get_024_sets_won () {
					return this._024_sets_won;
				}
				
			    public String _024_sets_lost;

				public String get_024_sets_lost () {
					return this._024_sets_lost;
				}
				
			    public String _024_avg_sets_match;

				public String get_024_avg_sets_match () {
					return this._024_avg_sets_match;
				}
				
			    public String _024_games_won;

				public String get_024_games_won () {
					return this._024_games_won;
				}
				
			    public String _024_games_lost;

				public String get_024_games_lost () {
					return this._024_games_lost;
				}
				
			    public String _024_avg_games_match;

				public String get_024_avg_games_match () {
					return this._024_avg_games_match;
				}
				
			    public String _024_titles;

				public String get_024_titles () {
					return this._024_titles;
				}
				
			    public String _024_finals;

				public String get_024_finals () {
					return this._024_finals;
				}
				
			    public String _024_semifinals;

				public String get_024_semifinals () {
					return this._024_semifinals;
				}
				
			    public String _024_best_round;

				public String get_024_best_round () {
					return this._024_best_round;
				}
				
			    public String _025_matches_played;

				public String get_025_matches_played () {
					return this._025_matches_played;
				}
				
			    public String _025_matches_won;

				public String get_025_matches_won () {
					return this._025_matches_won;
				}
				
			    public String _025_win_pct;

				public String get_025_win_pct () {
					return this._025_win_pct;
				}
				
			    public String _025_sets_won;

				public String get_025_sets_won () {
					return this._025_sets_won;
				}
				
			    public String _025_sets_lost;

				public String get_025_sets_lost () {
					return this._025_sets_lost;
				}
				
			    public String _025_avg_sets_match;

				public String get_025_avg_sets_match () {
					return this._025_avg_sets_match;
				}
				
			    public String _025_games_won;

				public String get_025_games_won () {
					return this._025_games_won;
				}
				
			    public String _025_games_lost;

				public String get_025_games_lost () {
					return this._025_games_lost;
				}
				
			    public String _025_avg_games_match;

				public String get_025_avg_games_match () {
					return this._025_avg_games_match;
				}
				
			    public String _025_titles;

				public String get_025_titles () {
					return this._025_titles;
				}
				
			    public String _025_finals;

				public String get_025_finals () {
					return this._025_finals;
				}
				
			    public String _025_semifinals;

				public String get_025_semifinals () {
					return this._025_semifinals;
				}
				
			    public String _025_best_round;

				public String get_025_best_round () {
					return this._025_best_round;
				}
				
			    public String _026_matches_played;

				public String get_026_matches_played () {
					return this._026_matches_played;
				}
				
			    public String _026_matches_won;

				public String get_026_matches_won () {
					return this._026_matches_won;
				}
				
			    public String _026_win_pct;

				public String get_026_win_pct () {
					return this._026_win_pct;
				}
				
			    public String _026_sets_won;

				public String get_026_sets_won () {
					return this._026_sets_won;
				}
				
			    public String _026_sets_lost;

				public String get_026_sets_lost () {
					return this._026_sets_lost;
				}
				
			    public String _026_avg_sets_match;

				public String get_026_avg_sets_match () {
					return this._026_avg_sets_match;
				}
				
			    public String _026_games_won;

				public String get_026_games_won () {
					return this._026_games_won;
				}
				
			    public String _026_games_lost;

				public String get_026_games_lost () {
					return this._026_games_lost;
				}
				
			    public String _026_avg_games_match;

				public String get_026_avg_games_match () {
					return this._026_avg_games_match;
				}
				
			    public String _026_titles;

				public String get_026_titles () {
					return this._026_titles;
				}
				
			    public String _026_finals;

				public String get_026_finals () {
					return this._026_finals;
				}
				
			    public String _026_semifinals;

				public String get_026_semifinals () {
					return this._026_semifinals;
				}
				
			    public String _026_best_round;

				public String get_026_best_round () {
					return this._026_best_round;
				}
				
			    public String followers;

				public String getFollowers () {
					return this.followers;
				}
				
			    public String interactions;

				public String getInteractions () {
					return this.interactions;
				}
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_dim_player.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_player.length == 0) {
   					commonByteArray_DW_PADEL_dim_player = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_player = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_dim_player, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_player, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_dim_player.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_player.length == 0) {
   					commonByteArray_DW_PADEL_dim_player = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_player = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_dim_player, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_player, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_dim_player) {

        	try {

        		int length = 0;
		
					this.name = readString(dis);
					
					this.ranking = readString(dis);
					
					this.gender = readString(dis);
					
					this.nationality = readString(dis);
					
					this.total_points = readString(dis);
					
					this.height = readString(dis);
					
					this.birthdate = readString(dis);
					
					this.age = readString(dis);
					
					this.hand = readString(dis);
					
					this.side = readString(dis);
					
					this.partner = readString(dis);
					
					this._023_matches_played = readString(dis);
					
					this._023_matches_won = readString(dis);
					
					this._023_win_pct = readString(dis);
					
					this._023_sets_won = readString(dis);
					
					this._023_sets_lost = readString(dis);
					
					this._023_avg_sets_match = readString(dis);
					
					this._023_games_won = readString(dis);
					
					this._023_games_lost = readString(dis);
					
					this._023_avg_games_match = readString(dis);
					
					this._023_titles = readString(dis);
					
					this._023_finals = readString(dis);
					
					this._023_semifinals = readString(dis);
					
					this._023_best_round = readString(dis);
					
					this._024_matches_played = readString(dis);
					
					this._024_matches_won = readString(dis);
					
					this._024_win_pct = readString(dis);
					
					this._024_sets_won = readString(dis);
					
					this._024_sets_lost = readString(dis);
					
					this._024_avg_sets_match = readString(dis);
					
					this._024_games_won = readString(dis);
					
					this._024_games_lost = readString(dis);
					
					this._024_avg_games_match = readString(dis);
					
					this._024_titles = readString(dis);
					
					this._024_finals = readString(dis);
					
					this._024_semifinals = readString(dis);
					
					this._024_best_round = readString(dis);
					
					this._025_matches_played = readString(dis);
					
					this._025_matches_won = readString(dis);
					
					this._025_win_pct = readString(dis);
					
					this._025_sets_won = readString(dis);
					
					this._025_sets_lost = readString(dis);
					
					this._025_avg_sets_match = readString(dis);
					
					this._025_games_won = readString(dis);
					
					this._025_games_lost = readString(dis);
					
					this._025_avg_games_match = readString(dis);
					
					this._025_titles = readString(dis);
					
					this._025_finals = readString(dis);
					
					this._025_semifinals = readString(dis);
					
					this._025_best_round = readString(dis);
					
					this._026_matches_played = readString(dis);
					
					this._026_matches_won = readString(dis);
					
					this._026_win_pct = readString(dis);
					
					this._026_sets_won = readString(dis);
					
					this._026_sets_lost = readString(dis);
					
					this._026_avg_sets_match = readString(dis);
					
					this._026_games_won = readString(dis);
					
					this._026_games_lost = readString(dis);
					
					this._026_avg_games_match = readString(dis);
					
					this._026_titles = readString(dis);
					
					this._026_finals = readString(dis);
					
					this._026_semifinals = readString(dis);
					
					this._026_best_round = readString(dis);
					
					this.followers = readString(dis);
					
					this.interactions = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_dim_player) {

        	try {

        		int length = 0;
		
					this.name = readString(dis);
					
					this.ranking = readString(dis);
					
					this.gender = readString(dis);
					
					this.nationality = readString(dis);
					
					this.total_points = readString(dis);
					
					this.height = readString(dis);
					
					this.birthdate = readString(dis);
					
					this.age = readString(dis);
					
					this.hand = readString(dis);
					
					this.side = readString(dis);
					
					this.partner = readString(dis);
					
					this._023_matches_played = readString(dis);
					
					this._023_matches_won = readString(dis);
					
					this._023_win_pct = readString(dis);
					
					this._023_sets_won = readString(dis);
					
					this._023_sets_lost = readString(dis);
					
					this._023_avg_sets_match = readString(dis);
					
					this._023_games_won = readString(dis);
					
					this._023_games_lost = readString(dis);
					
					this._023_avg_games_match = readString(dis);
					
					this._023_titles = readString(dis);
					
					this._023_finals = readString(dis);
					
					this._023_semifinals = readString(dis);
					
					this._023_best_round = readString(dis);
					
					this._024_matches_played = readString(dis);
					
					this._024_matches_won = readString(dis);
					
					this._024_win_pct = readString(dis);
					
					this._024_sets_won = readString(dis);
					
					this._024_sets_lost = readString(dis);
					
					this._024_avg_sets_match = readString(dis);
					
					this._024_games_won = readString(dis);
					
					this._024_games_lost = readString(dis);
					
					this._024_avg_games_match = readString(dis);
					
					this._024_titles = readString(dis);
					
					this._024_finals = readString(dis);
					
					this._024_semifinals = readString(dis);
					
					this._024_best_round = readString(dis);
					
					this._025_matches_played = readString(dis);
					
					this._025_matches_won = readString(dis);
					
					this._025_win_pct = readString(dis);
					
					this._025_sets_won = readString(dis);
					
					this._025_sets_lost = readString(dis);
					
					this._025_avg_sets_match = readString(dis);
					
					this._025_games_won = readString(dis);
					
					this._025_games_lost = readString(dis);
					
					this._025_avg_games_match = readString(dis);
					
					this._025_titles = readString(dis);
					
					this._025_finals = readString(dis);
					
					this._025_semifinals = readString(dis);
					
					this._025_best_round = readString(dis);
					
					this._026_matches_played = readString(dis);
					
					this._026_matches_won = readString(dis);
					
					this._026_win_pct = readString(dis);
					
					this._026_sets_won = readString(dis);
					
					this._026_sets_lost = readString(dis);
					
					this._026_avg_sets_match = readString(dis);
					
					this._026_games_won = readString(dis);
					
					this._026_games_lost = readString(dis);
					
					this._026_avg_games_match = readString(dis);
					
					this._026_titles = readString(dis);
					
					this._026_finals = readString(dis);
					
					this._026_semifinals = readString(dis);
					
					this._026_best_round = readString(dis);
					
					this.followers = readString(dis);
					
					this.interactions = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.name,dos);
					
					// String
				
						writeString(this.ranking,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.nationality,dos);
					
					// String
				
						writeString(this.total_points,dos);
					
					// String
				
						writeString(this.height,dos);
					
					// String
				
						writeString(this.birthdate,dos);
					
					// String
				
						writeString(this.age,dos);
					
					// String
				
						writeString(this.hand,dos);
					
					// String
				
						writeString(this.side,dos);
					
					// String
				
						writeString(this.partner,dos);
					
					// String
				
						writeString(this._023_matches_played,dos);
					
					// String
				
						writeString(this._023_matches_won,dos);
					
					// String
				
						writeString(this._023_win_pct,dos);
					
					// String
				
						writeString(this._023_sets_won,dos);
					
					// String
				
						writeString(this._023_sets_lost,dos);
					
					// String
				
						writeString(this._023_avg_sets_match,dos);
					
					// String
				
						writeString(this._023_games_won,dos);
					
					// String
				
						writeString(this._023_games_lost,dos);
					
					// String
				
						writeString(this._023_avg_games_match,dos);
					
					// String
				
						writeString(this._023_titles,dos);
					
					// String
				
						writeString(this._023_finals,dos);
					
					// String
				
						writeString(this._023_semifinals,dos);
					
					// String
				
						writeString(this._023_best_round,dos);
					
					// String
				
						writeString(this._024_matches_played,dos);
					
					// String
				
						writeString(this._024_matches_won,dos);
					
					// String
				
						writeString(this._024_win_pct,dos);
					
					// String
				
						writeString(this._024_sets_won,dos);
					
					// String
				
						writeString(this._024_sets_lost,dos);
					
					// String
				
						writeString(this._024_avg_sets_match,dos);
					
					// String
				
						writeString(this._024_games_won,dos);
					
					// String
				
						writeString(this._024_games_lost,dos);
					
					// String
				
						writeString(this._024_avg_games_match,dos);
					
					// String
				
						writeString(this._024_titles,dos);
					
					// String
				
						writeString(this._024_finals,dos);
					
					// String
				
						writeString(this._024_semifinals,dos);
					
					// String
				
						writeString(this._024_best_round,dos);
					
					// String
				
						writeString(this._025_matches_played,dos);
					
					// String
				
						writeString(this._025_matches_won,dos);
					
					// String
				
						writeString(this._025_win_pct,dos);
					
					// String
				
						writeString(this._025_sets_won,dos);
					
					// String
				
						writeString(this._025_sets_lost,dos);
					
					// String
				
						writeString(this._025_avg_sets_match,dos);
					
					// String
				
						writeString(this._025_games_won,dos);
					
					// String
				
						writeString(this._025_games_lost,dos);
					
					// String
				
						writeString(this._025_avg_games_match,dos);
					
					// String
				
						writeString(this._025_titles,dos);
					
					// String
				
						writeString(this._025_finals,dos);
					
					// String
				
						writeString(this._025_semifinals,dos);
					
					// String
				
						writeString(this._025_best_round,dos);
					
					// String
				
						writeString(this._026_matches_played,dos);
					
					// String
				
						writeString(this._026_matches_won,dos);
					
					// String
				
						writeString(this._026_win_pct,dos);
					
					// String
				
						writeString(this._026_sets_won,dos);
					
					// String
				
						writeString(this._026_sets_lost,dos);
					
					// String
				
						writeString(this._026_avg_sets_match,dos);
					
					// String
				
						writeString(this._026_games_won,dos);
					
					// String
				
						writeString(this._026_games_lost,dos);
					
					// String
				
						writeString(this._026_avg_games_match,dos);
					
					// String
				
						writeString(this._026_titles,dos);
					
					// String
				
						writeString(this._026_finals,dos);
					
					// String
				
						writeString(this._026_semifinals,dos);
					
					// String
				
						writeString(this._026_best_round,dos);
					
					// String
				
						writeString(this.followers,dos);
					
					// String
				
						writeString(this.interactions,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.name,dos);
					
					// String
				
						writeString(this.ranking,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.nationality,dos);
					
					// String
				
						writeString(this.total_points,dos);
					
					// String
				
						writeString(this.height,dos);
					
					// String
				
						writeString(this.birthdate,dos);
					
					// String
				
						writeString(this.age,dos);
					
					// String
				
						writeString(this.hand,dos);
					
					// String
				
						writeString(this.side,dos);
					
					// String
				
						writeString(this.partner,dos);
					
					// String
				
						writeString(this._023_matches_played,dos);
					
					// String
				
						writeString(this._023_matches_won,dos);
					
					// String
				
						writeString(this._023_win_pct,dos);
					
					// String
				
						writeString(this._023_sets_won,dos);
					
					// String
				
						writeString(this._023_sets_lost,dos);
					
					// String
				
						writeString(this._023_avg_sets_match,dos);
					
					// String
				
						writeString(this._023_games_won,dos);
					
					// String
				
						writeString(this._023_games_lost,dos);
					
					// String
				
						writeString(this._023_avg_games_match,dos);
					
					// String
				
						writeString(this._023_titles,dos);
					
					// String
				
						writeString(this._023_finals,dos);
					
					// String
				
						writeString(this._023_semifinals,dos);
					
					// String
				
						writeString(this._023_best_round,dos);
					
					// String
				
						writeString(this._024_matches_played,dos);
					
					// String
				
						writeString(this._024_matches_won,dos);
					
					// String
				
						writeString(this._024_win_pct,dos);
					
					// String
				
						writeString(this._024_sets_won,dos);
					
					// String
				
						writeString(this._024_sets_lost,dos);
					
					// String
				
						writeString(this._024_avg_sets_match,dos);
					
					// String
				
						writeString(this._024_games_won,dos);
					
					// String
				
						writeString(this._024_games_lost,dos);
					
					// String
				
						writeString(this._024_avg_games_match,dos);
					
					// String
				
						writeString(this._024_titles,dos);
					
					// String
				
						writeString(this._024_finals,dos);
					
					// String
				
						writeString(this._024_semifinals,dos);
					
					// String
				
						writeString(this._024_best_round,dos);
					
					// String
				
						writeString(this._025_matches_played,dos);
					
					// String
				
						writeString(this._025_matches_won,dos);
					
					// String
				
						writeString(this._025_win_pct,dos);
					
					// String
				
						writeString(this._025_sets_won,dos);
					
					// String
				
						writeString(this._025_sets_lost,dos);
					
					// String
				
						writeString(this._025_avg_sets_match,dos);
					
					// String
				
						writeString(this._025_games_won,dos);
					
					// String
				
						writeString(this._025_games_lost,dos);
					
					// String
				
						writeString(this._025_avg_games_match,dos);
					
					// String
				
						writeString(this._025_titles,dos);
					
					// String
				
						writeString(this._025_finals,dos);
					
					// String
				
						writeString(this._025_semifinals,dos);
					
					// String
				
						writeString(this._025_best_round,dos);
					
					// String
				
						writeString(this._026_matches_played,dos);
					
					// String
				
						writeString(this._026_matches_won,dos);
					
					// String
				
						writeString(this._026_win_pct,dos);
					
					// String
				
						writeString(this._026_sets_won,dos);
					
					// String
				
						writeString(this._026_sets_lost,dos);
					
					// String
				
						writeString(this._026_avg_sets_match,dos);
					
					// String
				
						writeString(this._026_games_won,dos);
					
					// String
				
						writeString(this._026_games_lost,dos);
					
					// String
				
						writeString(this._026_avg_games_match,dos);
					
					// String
				
						writeString(this._026_titles,dos);
					
					// String
				
						writeString(this._026_finals,dos);
					
					// String
				
						writeString(this._026_semifinals,dos);
					
					// String
				
						writeString(this._026_best_round,dos);
					
					// String
				
						writeString(this.followers,dos);
					
					// String
				
						writeString(this.interactions,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("name="+name);
		sb.append(",ranking="+ranking);
		sb.append(",gender="+gender);
		sb.append(",nationality="+nationality);
		sb.append(",total_points="+total_points);
		sb.append(",height="+height);
		sb.append(",birthdate="+birthdate);
		sb.append(",age="+age);
		sb.append(",hand="+hand);
		sb.append(",side="+side);
		sb.append(",partner="+partner);
		sb.append(",_023_matches_played="+_023_matches_played);
		sb.append(",_023_matches_won="+_023_matches_won);
		sb.append(",_023_win_pct="+_023_win_pct);
		sb.append(",_023_sets_won="+_023_sets_won);
		sb.append(",_023_sets_lost="+_023_sets_lost);
		sb.append(",_023_avg_sets_match="+_023_avg_sets_match);
		sb.append(",_023_games_won="+_023_games_won);
		sb.append(",_023_games_lost="+_023_games_lost);
		sb.append(",_023_avg_games_match="+_023_avg_games_match);
		sb.append(",_023_titles="+_023_titles);
		sb.append(",_023_finals="+_023_finals);
		sb.append(",_023_semifinals="+_023_semifinals);
		sb.append(",_023_best_round="+_023_best_round);
		sb.append(",_024_matches_played="+_024_matches_played);
		sb.append(",_024_matches_won="+_024_matches_won);
		sb.append(",_024_win_pct="+_024_win_pct);
		sb.append(",_024_sets_won="+_024_sets_won);
		sb.append(",_024_sets_lost="+_024_sets_lost);
		sb.append(",_024_avg_sets_match="+_024_avg_sets_match);
		sb.append(",_024_games_won="+_024_games_won);
		sb.append(",_024_games_lost="+_024_games_lost);
		sb.append(",_024_avg_games_match="+_024_avg_games_match);
		sb.append(",_024_titles="+_024_titles);
		sb.append(",_024_finals="+_024_finals);
		sb.append(",_024_semifinals="+_024_semifinals);
		sb.append(",_024_best_round="+_024_best_round);
		sb.append(",_025_matches_played="+_025_matches_played);
		sb.append(",_025_matches_won="+_025_matches_won);
		sb.append(",_025_win_pct="+_025_win_pct);
		sb.append(",_025_sets_won="+_025_sets_won);
		sb.append(",_025_sets_lost="+_025_sets_lost);
		sb.append(",_025_avg_sets_match="+_025_avg_sets_match);
		sb.append(",_025_games_won="+_025_games_won);
		sb.append(",_025_games_lost="+_025_games_lost);
		sb.append(",_025_avg_games_match="+_025_avg_games_match);
		sb.append(",_025_titles="+_025_titles);
		sb.append(",_025_finals="+_025_finals);
		sb.append(",_025_semifinals="+_025_semifinals);
		sb.append(",_025_best_round="+_025_best_round);
		sb.append(",_026_matches_played="+_026_matches_played);
		sb.append(",_026_matches_won="+_026_matches_won);
		sb.append(",_026_win_pct="+_026_win_pct);
		sb.append(",_026_sets_won="+_026_sets_won);
		sb.append(",_026_sets_lost="+_026_sets_lost);
		sb.append(",_026_avg_sets_match="+_026_avg_sets_match);
		sb.append(",_026_games_won="+_026_games_won);
		sb.append(",_026_games_lost="+_026_games_lost);
		sb.append(",_026_avg_games_match="+_026_avg_games_match);
		sb.append(",_026_titles="+_026_titles);
		sb.append(",_026_finals="+_026_finals);
		sb.append(",_026_semifinals="+_026_semifinals);
		sb.append(",_026_best_round="+_026_best_round);
		sb.append(",followers="+followers);
		sb.append(",interactions="+interactions);
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
row2Struct row2 = new row2Struct();
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


        int updateKeyCount_tDBOutput_1 = 1;
        if(updateKeyCount_tDBOutput_1 < 1) {
            throw new RuntimeException("For update, Schema must have a key");
        } else if (updateKeyCount_tDBOutput_1 == 11 && true) {
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

 
	final String decryptedPassword_tDBOutput_1 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:TZuvA1P7K1odkJjIWQNAOG1QxR68xQ9eAd9XsFxsXmlb5eQ=");

    String dbPwd_tDBOutput_1 = decryptedPassword_tDBOutput_1;	
    conn_tDBOutput_1 = java.sql.DriverManager.getConnection(url_tDBOutput_1,dbUser_tDBOutput_1,dbPwd_tDBOutput_1);
	
		resourceMap.put("conn_tDBOutput_1", conn_tDBOutput_1);
	
        conn_tDBOutput_1.setAutoCommit(false);
        int commitEvery_tDBOutput_1 = 10000;
        int commitCounter_tDBOutput_1 = 0;


if(dbschema_tDBOutput_1 == null || dbschema_tDBOutput_1.trim().length() == 0) {
    tableName_tDBOutput_1 = "dim_player";
} else {
    tableName_tDBOutput_1 = dbschema_tDBOutput_1 + "].[" + "dim_player";
}
	int count_tDBOutput_1=0;

        java.sql.PreparedStatement pstmt_tDBOutput_1 = conn_tDBOutput_1.prepareStatement("SELECT COUNT(1) FROM [" + tableName_tDBOutput_1 + "] WHERE [player_id] = ?");
        resourceMap.put("pstmt_tDBOutput_1", pstmt_tDBOutput_1);
        String insert_tDBOutput_1 = "INSERT INTO [" + tableName_tDBOutput_1 + "] ([player_id],[full_name],[ranking],[gender],[nationality],[birthdate],[height_cm],[playing_hand],[court_side],[partner_name],[total_points]) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        java.sql.PreparedStatement pstmtInsert_tDBOutput_1 = conn_tDBOutput_1.prepareStatement(insert_tDBOutput_1);
        resourceMap.put("pstmtInsert_tDBOutput_1", pstmtInsert_tDBOutput_1);
        String update_tDBOutput_1 = "UPDATE [" + tableName_tDBOutput_1 + "] SET [full_name] = ?,[ranking] = ?,[gender] = ?,[nationality] = ?,[birthdate] = ?,[height_cm] = ?,[playing_hand] = ?,[court_side] = ?,[partner_name] = ?,[total_points] = ? WHERE [player_id] = ?";
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
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row3");
					}
				
		int tos_count_tMap_1 = 0;
		




// ###############################
// # Lookup's keys initialization
	
		org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct> tHash_Lookup_row4 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct>) 
				((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct>) 
					globalMap.get( "tHash_Lookup_row4" ))
					;					
					
	

row4Struct row4HashKey = new row4Struct();
row4Struct row4Default = new row4Struct();
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
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row1");
					}
				
		int tos_count_tUniqRow_1 = 0;
		

	
		class KeyStruct_tUniqRow_1 {
	
			private static final int DEFAULT_HASHCODE = 1;
		    private static final int PRIME = 31;
		    private int hashCode = DEFAULT_HASHCODE;
		    public boolean hashCodeDirty = true;
	
	        
					String name;        
	        
		    @Override
			public int hashCode() {
				if (this.hashCodeDirty) {
					final int prime = PRIME;
					int result = DEFAULT_HASHCODE;
			
								result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
								
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
				
									if (this.name == null) {
										if (other.name != null) 
											return false;
								
									} else if (!this.name.equals(other.name))
								 
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
				
				 
	final String decryptedPassword_tDBInput_1 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:zVC92Lmuxc3gzjAP6XLcLJlbAmkfE8JOJbkfVWR4mCSnxfI=");
				
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

		    String dbquery_tDBInput_1 = "SELECT players.name,\n		players.ranking,\n		players.gender,\n		players.nationality,\n		players.total_points,\n		players.heig"
+"ht,\n		players.birthdate,\n		players.age,\n		players.hand,\n		players.side,\n		players.partner,\n		players.\"2023_matches_play"
+"ed\",\n		players.\"2023_matches_won\",\n		players.\"2023_win_pct\",\n		players.\"2023_sets_won\",\n		players.\"2023_sets_los"
+"t\",\n		players.\"2023_avg_sets_match\",\n		players.\"2023_games_won\",\n		players.\"2023_games_lost\",\n		players.\"2023_av"
+"g_games_match\",\n		players.\"2023_titles\",\n		players.\"2023_finals\",\n		players.\"2023_semifinals\",\n		players.\"2023_b"
+"est_round\",\n		players.\"2024_matches_played\",\n		players.\"2024_matches_won\",\n		players.\"2024_win_pct\",\n		players.\""
+"2024_sets_won\",\n		players.\"2024_sets_lost\",\n		players.\"2024_avg_sets_match\",\n		players.\"2024_games_won\",\n		player"
+"s.\"2024_games_lost\",\n		players.\"2024_avg_games_match\",\n		players.\"2024_titles\",\n		players.\"2024_finals\",\n		playe"
+"rs.\"2024_semifinals\",\n		players.\"2024_best_round\",\n		players.\"2025_matches_played\",\n		players.\"2025_matches_won\""
+",\n		players.\"2025_win_pct\",\n		players.\"2025_sets_won\",\n		players.\"2025_sets_lost\",\n		players.\"2025_avg_sets_match"
+"\",\n		players.\"2025_games_won\",\n		players.\"2025_games_lost\",\n		players.\"2025_avg_games_match\",\n		players.\"2025_ti"
+"tles\",\n		players.\"2025_finals\",\n		players.\"2025_semifinals\",\n		players.\"2025_best_round\",\n		players.\"2026_matche"
+"s_played\",\n		players.\"2026_matches_won\",\n		players.\"2026_win_pct\",\n		players.\"2026_sets_won\",\n		players.\"2026_se"
+"ts_lost\",\n		players.\"2026_avg_sets_match\",\n		players.\"2026_games_won\",\n		players.\"2026_games_lost\",\n		players.\"2"
+"026_avg_games_match\",\n		players.\"2026_titles\",\n		players.\"2026_finals\",\n		players.\"2026_semifinals\",\n		players.\""
+"2026_best_round\",\n		players.followers,\n		players.interactions\nFROM	players";
		    

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
								row1.name = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(1);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(1).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.name = tmpContent_tDBInput_1;
                }
            } else {
                row1.name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 2) {
								row1.ranking = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(2);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.ranking = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.ranking = tmpContent_tDBInput_1;
                }
            } else {
                row1.ranking = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 3) {
								row1.gender = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(3);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(3).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.gender = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.gender = tmpContent_tDBInput_1;
                }
            } else {
                row1.gender = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 4) {
								row1.nationality = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(4);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(4).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.nationality = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.nationality = tmpContent_tDBInput_1;
                }
            } else {
                row1.nationality = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 5) {
								row1.total_points = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(5);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(5).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.total_points = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.total_points = tmpContent_tDBInput_1;
                }
            } else {
                row1.total_points = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 6) {
								row1.height = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(6);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(6).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.height = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.height = tmpContent_tDBInput_1;
                }
            } else {
                row1.height = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 7) {
								row1.birthdate = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(7);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(7).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.birthdate = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.birthdate = tmpContent_tDBInput_1;
                }
            } else {
                row1.birthdate = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 8) {
								row1.age = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(8);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(8).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.age = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.age = tmpContent_tDBInput_1;
                }
            } else {
                row1.age = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 9) {
								row1.hand = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(9);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(9).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.hand = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.hand = tmpContent_tDBInput_1;
                }
            } else {
                row1.hand = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 10) {
								row1.side = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(10);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(10).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.side = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.side = tmpContent_tDBInput_1;
                }
            } else {
                row1.side = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 11) {
								row1.partner = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(11);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(11).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.partner = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.partner = tmpContent_tDBInput_1;
                }
            } else {
                row1.partner = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 12) {
								row1._023_matches_played = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(12);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(12).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._023_matches_played = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._023_matches_played = tmpContent_tDBInput_1;
                }
            } else {
                row1._023_matches_played = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 13) {
								row1._023_matches_won = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(13);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(13).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._023_matches_won = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._023_matches_won = tmpContent_tDBInput_1;
                }
            } else {
                row1._023_matches_won = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 14) {
								row1._023_win_pct = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(14);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(14).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._023_win_pct = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._023_win_pct = tmpContent_tDBInput_1;
                }
            } else {
                row1._023_win_pct = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 15) {
								row1._023_sets_won = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(15);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(15).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._023_sets_won = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._023_sets_won = tmpContent_tDBInput_1;
                }
            } else {
                row1._023_sets_won = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 16) {
								row1._023_sets_lost = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(16);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(16).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._023_sets_lost = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._023_sets_lost = tmpContent_tDBInput_1;
                }
            } else {
                row1._023_sets_lost = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 17) {
								row1._023_avg_sets_match = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(17);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(17).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._023_avg_sets_match = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._023_avg_sets_match = tmpContent_tDBInput_1;
                }
            } else {
                row1._023_avg_sets_match = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 18) {
								row1._023_games_won = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(18);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(18).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._023_games_won = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._023_games_won = tmpContent_tDBInput_1;
                }
            } else {
                row1._023_games_won = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 19) {
								row1._023_games_lost = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(19);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(19).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._023_games_lost = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._023_games_lost = tmpContent_tDBInput_1;
                }
            } else {
                row1._023_games_lost = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 20) {
								row1._023_avg_games_match = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(20);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(20).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._023_avg_games_match = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._023_avg_games_match = tmpContent_tDBInput_1;
                }
            } else {
                row1._023_avg_games_match = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 21) {
								row1._023_titles = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(21);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(21).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._023_titles = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._023_titles = tmpContent_tDBInput_1;
                }
            } else {
                row1._023_titles = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 22) {
								row1._023_finals = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(22);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(22).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._023_finals = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._023_finals = tmpContent_tDBInput_1;
                }
            } else {
                row1._023_finals = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 23) {
								row1._023_semifinals = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(23);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(23).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._023_semifinals = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._023_semifinals = tmpContent_tDBInput_1;
                }
            } else {
                row1._023_semifinals = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 24) {
								row1._023_best_round = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(24);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(24).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._023_best_round = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._023_best_round = tmpContent_tDBInput_1;
                }
            } else {
                row1._023_best_round = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 25) {
								row1._024_matches_played = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(25);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(25).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._024_matches_played = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._024_matches_played = tmpContent_tDBInput_1;
                }
            } else {
                row1._024_matches_played = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 26) {
								row1._024_matches_won = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(26);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(26).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._024_matches_won = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._024_matches_won = tmpContent_tDBInput_1;
                }
            } else {
                row1._024_matches_won = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 27) {
								row1._024_win_pct = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(27);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(27).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._024_win_pct = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._024_win_pct = tmpContent_tDBInput_1;
                }
            } else {
                row1._024_win_pct = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 28) {
								row1._024_sets_won = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(28);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(28).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._024_sets_won = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._024_sets_won = tmpContent_tDBInput_1;
                }
            } else {
                row1._024_sets_won = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 29) {
								row1._024_sets_lost = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(29);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(29).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._024_sets_lost = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._024_sets_lost = tmpContent_tDBInput_1;
                }
            } else {
                row1._024_sets_lost = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 30) {
								row1._024_avg_sets_match = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(30);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(30).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._024_avg_sets_match = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._024_avg_sets_match = tmpContent_tDBInput_1;
                }
            } else {
                row1._024_avg_sets_match = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 31) {
								row1._024_games_won = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(31);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(31).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._024_games_won = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._024_games_won = tmpContent_tDBInput_1;
                }
            } else {
                row1._024_games_won = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 32) {
								row1._024_games_lost = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(32);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(32).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._024_games_lost = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._024_games_lost = tmpContent_tDBInput_1;
                }
            } else {
                row1._024_games_lost = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 33) {
								row1._024_avg_games_match = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(33);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(33).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._024_avg_games_match = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._024_avg_games_match = tmpContent_tDBInput_1;
                }
            } else {
                row1._024_avg_games_match = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 34) {
								row1._024_titles = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(34);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(34).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._024_titles = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._024_titles = tmpContent_tDBInput_1;
                }
            } else {
                row1._024_titles = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 35) {
								row1._024_finals = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(35);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(35).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._024_finals = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._024_finals = tmpContent_tDBInput_1;
                }
            } else {
                row1._024_finals = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 36) {
								row1._024_semifinals = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(36);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(36).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._024_semifinals = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._024_semifinals = tmpContent_tDBInput_1;
                }
            } else {
                row1._024_semifinals = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 37) {
								row1._024_best_round = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(37);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(37).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._024_best_round = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._024_best_round = tmpContent_tDBInput_1;
                }
            } else {
                row1._024_best_round = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 38) {
								row1._025_matches_played = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(38);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(38).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._025_matches_played = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._025_matches_played = tmpContent_tDBInput_1;
                }
            } else {
                row1._025_matches_played = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 39) {
								row1._025_matches_won = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(39);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(39).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._025_matches_won = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._025_matches_won = tmpContent_tDBInput_1;
                }
            } else {
                row1._025_matches_won = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 40) {
								row1._025_win_pct = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(40);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(40).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._025_win_pct = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._025_win_pct = tmpContent_tDBInput_1;
                }
            } else {
                row1._025_win_pct = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 41) {
								row1._025_sets_won = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(41);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(41).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._025_sets_won = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._025_sets_won = tmpContent_tDBInput_1;
                }
            } else {
                row1._025_sets_won = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 42) {
								row1._025_sets_lost = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(42);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(42).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._025_sets_lost = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._025_sets_lost = tmpContent_tDBInput_1;
                }
            } else {
                row1._025_sets_lost = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 43) {
								row1._025_avg_sets_match = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(43);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(43).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._025_avg_sets_match = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._025_avg_sets_match = tmpContent_tDBInput_1;
                }
            } else {
                row1._025_avg_sets_match = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 44) {
								row1._025_games_won = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(44);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(44).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._025_games_won = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._025_games_won = tmpContent_tDBInput_1;
                }
            } else {
                row1._025_games_won = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 45) {
								row1._025_games_lost = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(45);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(45).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._025_games_lost = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._025_games_lost = tmpContent_tDBInput_1;
                }
            } else {
                row1._025_games_lost = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 46) {
								row1._025_avg_games_match = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(46);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(46).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._025_avg_games_match = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._025_avg_games_match = tmpContent_tDBInput_1;
                }
            } else {
                row1._025_avg_games_match = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 47) {
								row1._025_titles = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(47);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(47).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._025_titles = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._025_titles = tmpContent_tDBInput_1;
                }
            } else {
                row1._025_titles = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 48) {
								row1._025_finals = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(48);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(48).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._025_finals = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._025_finals = tmpContent_tDBInput_1;
                }
            } else {
                row1._025_finals = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 49) {
								row1._025_semifinals = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(49);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(49).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._025_semifinals = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._025_semifinals = tmpContent_tDBInput_1;
                }
            } else {
                row1._025_semifinals = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 50) {
								row1._025_best_round = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(50);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(50).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._025_best_round = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._025_best_round = tmpContent_tDBInput_1;
                }
            } else {
                row1._025_best_round = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 51) {
								row1._026_matches_played = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(51);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(51).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._026_matches_played = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._026_matches_played = tmpContent_tDBInput_1;
                }
            } else {
                row1._026_matches_played = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 52) {
								row1._026_matches_won = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(52);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(52).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._026_matches_won = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._026_matches_won = tmpContent_tDBInput_1;
                }
            } else {
                row1._026_matches_won = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 53) {
								row1._026_win_pct = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(53);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(53).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._026_win_pct = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._026_win_pct = tmpContent_tDBInput_1;
                }
            } else {
                row1._026_win_pct = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 54) {
								row1._026_sets_won = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(54);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(54).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._026_sets_won = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._026_sets_won = tmpContent_tDBInput_1;
                }
            } else {
                row1._026_sets_won = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 55) {
								row1._026_sets_lost = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(55);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(55).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._026_sets_lost = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._026_sets_lost = tmpContent_tDBInput_1;
                }
            } else {
                row1._026_sets_lost = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 56) {
								row1._026_avg_sets_match = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(56);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(56).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._026_avg_sets_match = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._026_avg_sets_match = tmpContent_tDBInput_1;
                }
            } else {
                row1._026_avg_sets_match = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 57) {
								row1._026_games_won = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(57);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(57).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._026_games_won = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._026_games_won = tmpContent_tDBInput_1;
                }
            } else {
                row1._026_games_won = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 58) {
								row1._026_games_lost = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(58);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(58).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._026_games_lost = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._026_games_lost = tmpContent_tDBInput_1;
                }
            } else {
                row1._026_games_lost = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 59) {
								row1._026_avg_games_match = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(59);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(59).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._026_avg_games_match = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._026_avg_games_match = tmpContent_tDBInput_1;
                }
            } else {
                row1._026_avg_games_match = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 60) {
								row1._026_titles = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(60);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(60).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._026_titles = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._026_titles = tmpContent_tDBInput_1;
                }
            } else {
                row1._026_titles = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 61) {
								row1._026_finals = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(61);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(61).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._026_finals = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._026_finals = tmpContent_tDBInput_1;
                }
            } else {
                row1._026_finals = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 62) {
								row1._026_semifinals = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(62);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(62).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._026_semifinals = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._026_semifinals = tmpContent_tDBInput_1;
                }
            } else {
                row1._026_semifinals = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 63) {
								row1._026_best_round = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(63);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(63).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1._026_best_round = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1._026_best_round = tmpContent_tDBInput_1;
                }
            } else {
                row1._026_best_round = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 64) {
								row1.followers = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(64);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(64).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.followers = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.followers = tmpContent_tDBInput_1;
                }
            } else {
                row1.followers = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 65) {
								row1.interactions = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(65);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(65).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.interactions = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.interactions = tmpContent_tDBInput_1;
                }
            } else {
                row1.interactions = null;
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
						
							,"row1"
						
						);
					}
					
row2 = null;			
finder_tUniqRow_1.name = row1.name;	
finder_tUniqRow_1.hashCodeDirty = true;
if (!keystUniqRow_1.contains(finder_tUniqRow_1)) {
		KeyStruct_tUniqRow_1 new_tUniqRow_1 = new KeyStruct_tUniqRow_1();

		
new_tUniqRow_1.name = row1.name;
		
		keystUniqRow_1.add(new_tUniqRow_1);if(row2 == null){ 
	
	row2 = new row2Struct();
}row2.name = row1.name;			row2.ranking = row1.ranking;			row2.gender = row1.gender;			row2.nationality = row1.nationality;			row2.total_points = row1.total_points;			row2.height = row1.height;			row2.birthdate = row1.birthdate;			row2.age = row1.age;			row2.hand = row1.hand;			row2.side = row1.side;			row2.partner = row1.partner;			row2._023_matches_played = row1._023_matches_played;			row2._023_matches_won = row1._023_matches_won;			row2._023_win_pct = row1._023_win_pct;			row2._023_sets_won = row1._023_sets_won;			row2._023_sets_lost = row1._023_sets_lost;			row2._023_avg_sets_match = row1._023_avg_sets_match;			row2._023_games_won = row1._023_games_won;			row2._023_games_lost = row1._023_games_lost;			row2._023_avg_games_match = row1._023_avg_games_match;			row2._023_titles = row1._023_titles;			row2._023_finals = row1._023_finals;			row2._023_semifinals = row1._023_semifinals;			row2._023_best_round = row1._023_best_round;			row2._024_matches_played = row1._024_matches_played;			row2._024_matches_won = row1._024_matches_won;			row2._024_win_pct = row1._024_win_pct;			row2._024_sets_won = row1._024_sets_won;			row2._024_sets_lost = row1._024_sets_lost;			row2._024_avg_sets_match = row1._024_avg_sets_match;			row2._024_games_won = row1._024_games_won;			row2._024_games_lost = row1._024_games_lost;			row2._024_avg_games_match = row1._024_avg_games_match;			row2._024_titles = row1._024_titles;			row2._024_finals = row1._024_finals;			row2._024_semifinals = row1._024_semifinals;			row2._024_best_round = row1._024_best_round;			row2._025_matches_played = row1._025_matches_played;			row2._025_matches_won = row1._025_matches_won;			row2._025_win_pct = row1._025_win_pct;			row2._025_sets_won = row1._025_sets_won;			row2._025_sets_lost = row1._025_sets_lost;			row2._025_avg_sets_match = row1._025_avg_sets_match;			row2._025_games_won = row1._025_games_won;			row2._025_games_lost = row1._025_games_lost;			row2._025_avg_games_match = row1._025_avg_games_match;			row2._025_titles = row1._025_titles;			row2._025_finals = row1._025_finals;			row2._025_semifinals = row1._025_semifinals;			row2._025_best_round = row1._025_best_round;			row2._026_matches_played = row1._026_matches_played;			row2._026_matches_won = row1._026_matches_won;			row2._026_win_pct = row1._026_win_pct;			row2._026_sets_won = row1._026_sets_won;			row2._026_sets_lost = row1._026_sets_lost;			row2._026_avg_sets_match = row1._026_avg_sets_match;			row2._026_games_won = row1._026_games_won;			row2._026_games_lost = row1._026_games_lost;			row2._026_avg_games_match = row1._026_avg_games_match;			row2._026_titles = row1._026_titles;			row2._026_finals = row1._026_finals;			row2._026_semifinals = row1._026_semifinals;			row2._026_best_round = row1._026_best_round;			row2.followers = row1.followers;			row2.interactions = row1.interactions;					
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
					

          row3 = null;
    Operator_tFilterRow_1 ope_tFilterRow_1 = new Operator_tFilterRow_1("&&");
	        ope_tFilterRow_1.matches((row2.name != null)
	                       , "name!=null failed");
    
    if (ope_tFilterRow_1.getMatchFlag()) {
              if(row3 == null){ 
                row3 = new row3Struct();
              }
               row3.name = row2.name;
               row3.ranking = row2.ranking;
               row3.gender = row2.gender;
               row3.nationality = row2.nationality;
               row3.total_points = row2.total_points;
               row3.height = row2.height;
               row3.birthdate = row2.birthdate;
               row3.age = row2.age;
               row3.hand = row2.hand;
               row3.side = row2.side;
               row3.partner = row2.partner;
               row3._023_matches_played = row2._023_matches_played;
               row3._023_matches_won = row2._023_matches_won;
               row3._023_win_pct = row2._023_win_pct;
               row3._023_sets_won = row2._023_sets_won;
               row3._023_sets_lost = row2._023_sets_lost;
               row3._023_avg_sets_match = row2._023_avg_sets_match;
               row3._023_games_won = row2._023_games_won;
               row3._023_games_lost = row2._023_games_lost;
               row3._023_avg_games_match = row2._023_avg_games_match;
               row3._023_titles = row2._023_titles;
               row3._023_finals = row2._023_finals;
               row3._023_semifinals = row2._023_semifinals;
               row3._023_best_round = row2._023_best_round;
               row3._024_matches_played = row2._024_matches_played;
               row3._024_matches_won = row2._024_matches_won;
               row3._024_win_pct = row2._024_win_pct;
               row3._024_sets_won = row2._024_sets_won;
               row3._024_sets_lost = row2._024_sets_lost;
               row3._024_avg_sets_match = row2._024_avg_sets_match;
               row3._024_games_won = row2._024_games_won;
               row3._024_games_lost = row2._024_games_lost;
               row3._024_avg_games_match = row2._024_avg_games_match;
               row3._024_titles = row2._024_titles;
               row3._024_finals = row2._024_finals;
               row3._024_semifinals = row2._024_semifinals;
               row3._024_best_round = row2._024_best_round;
               row3._025_matches_played = row2._025_matches_played;
               row3._025_matches_won = row2._025_matches_won;
               row3._025_win_pct = row2._025_win_pct;
               row3._025_sets_won = row2._025_sets_won;
               row3._025_sets_lost = row2._025_sets_lost;
               row3._025_avg_sets_match = row2._025_avg_sets_match;
               row3._025_games_won = row2._025_games_won;
               row3._025_games_lost = row2._025_games_lost;
               row3._025_avg_games_match = row2._025_avg_games_match;
               row3._025_titles = row2._025_titles;
               row3._025_finals = row2._025_finals;
               row3._025_semifinals = row2._025_semifinals;
               row3._025_best_round = row2._025_best_round;
               row3._026_matches_played = row2._026_matches_played;
               row3._026_matches_won = row2._026_matches_won;
               row3._026_win_pct = row2._026_win_pct;
               row3._026_sets_won = row2._026_sets_won;
               row3._026_sets_lost = row2._026_sets_lost;
               row3._026_avg_sets_match = row2._026_avg_sets_match;
               row3._026_games_won = row2._026_games_won;
               row3._026_games_lost = row2._026_games_lost;
               row3._026_avg_games_match = row2._026_avg_games_match;
               row3._026_titles = row2._026_titles;
               row3._026_finals = row2._026_finals;
               row3._026_semifinals = row2._026_semifinals;
               row3._026_best_round = row2._026_best_round;
               row3.followers = row2.followers;
               row3.interactions = row2.interactions;    
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
				// Starting Lookup Table "row4" 
				///////////////////////////////////////////////


				
				
                            
 					    boolean forceLooprow4 = false;
       		  	    	
       		  	    	
 							row4Struct row4ObjectFromLookup = null;
                          
		           		  	if(!rejectedInnerJoin_tMap_1) { // G_TM_M_020

								
								hasCasePrimitiveKeyWithNull_tMap_1 = false;
								
                        		    		    row4HashKey.full_name = row3.name ;
                        		    		

								
		                        	row4HashKey.hashCodeDirty = true;
                        		
	  					
	  							
			  					
			  					
	  					
		  							tHash_Lookup_row4.lookup( row4HashKey );

	  							

	  							

 								
		  				
	  								
						
									
  									  		
 								



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
							
							
							
			  							
								
	                    		  	
		                    
	            	
	            	
	            // ###############################
        { // start of Var scope
        
	        // ###############################
        	// # Vars tables
        
Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
        // ###############################
        // # Output tables

DIM = null;


// # Output table : 'DIM'
DIM_tmp.player_id = Numeric.sequence("s1", 1, 1);
DIM_tmp.full_name = row3.name ;
DIM_tmp.ranking = (row3.ranking == null || row3.ranking.trim().isEmpty()) ? null : Integer.parseInt(row3.ranking);
DIM_tmp.gender = row3.gender ;
DIM_tmp.nationality = row3.nationality ;
DIM_tmp.birthdate = (row3.birthdate == null || row3.birthdate.trim().isEmpty()) ? null : TalendDate.parseDate("dd/MM/yyyy", row3.birthdate);
DIM_tmp.height_cm = (row3.height == null || row3.height.trim().isEmpty()) ? null : Double.valueOf(row3.height).shortValue();
DIM_tmp.playing_hand = row3.hand ;
DIM_tmp.court_side = row3.side ;
DIM_tmp.partner_name = row3.partner ;
DIM_tmp.total_points = (row3.total_points == null || row3.total_points.trim().isEmpty()) ? 0 : Integer.parseInt(row3.total_points.trim());
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


                    pstmt_tDBOutput_1.setInt(1, DIM.player_id);

            int checkCount_tDBOutput_1 = -1;
            try (java.sql.ResultSet rs_tDBOutput_1 = pstmt_tDBOutput_1.executeQuery()) {
                while(rs_tDBOutput_1.next()) {
                    checkCount_tDBOutput_1 = rs_tDBOutput_1.getInt(1);
                }
            }
            if(checkCount_tDBOutput_1 > 0) {
                        if(DIM.full_name == null) {
pstmtUpdate_tDBOutput_1.setNull(1, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(1, DIM.full_name);
}

                        if(DIM.ranking == null) {
pstmtUpdate_tDBOutput_1.setNull(2, java.sql.Types.INTEGER);
} else {pstmtUpdate_tDBOutput_1.setInt(2, DIM.ranking);
}

                        if(DIM.gender == null) {
pstmtUpdate_tDBOutput_1.setNull(3, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(3, DIM.gender);
}

                        if(DIM.nationality == null) {
pstmtUpdate_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(4, DIM.nationality);
}

                        if(DIM.birthdate != null) {
pstmtUpdate_tDBOutput_1.setTimestamp(5, new java.sql.Timestamp(DIM.birthdate.getTime()));
} else {
pstmtUpdate_tDBOutput_1.setNull(5, java.sql.Types.TIMESTAMP);
}

                        if(DIM.height_cm == null) {
pstmtUpdate_tDBOutput_1.setNull(6, java.sql.Types.INTEGER);
} else {pstmtUpdate_tDBOutput_1.setShort(6, DIM.height_cm);
}

                        if(DIM.playing_hand == null) {
pstmtUpdate_tDBOutput_1.setNull(7, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(7, DIM.playing_hand);
}

                        if(DIM.court_side == null) {
pstmtUpdate_tDBOutput_1.setNull(8, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(8, DIM.court_side);
}

                        if(DIM.partner_name == null) {
pstmtUpdate_tDBOutput_1.setNull(9, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(9, DIM.partner_name);
}

                        if(DIM.total_points == null) {
pstmtUpdate_tDBOutput_1.setNull(10, java.sql.Types.INTEGER);
} else {pstmtUpdate_tDBOutput_1.setInt(10, DIM.total_points);
}


	                    

                        pstmtUpdate_tDBOutput_1.setInt(11 + count_tDBOutput_1, DIM.player_id);

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
                        pstmtInsert_tDBOutput_1.setInt(1, DIM.player_id);

                        if(DIM.full_name == null) {
pstmtInsert_tDBOutput_1.setNull(2, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(2, DIM.full_name);
}

                        if(DIM.ranking == null) {
pstmtInsert_tDBOutput_1.setNull(3, java.sql.Types.INTEGER);
} else {pstmtInsert_tDBOutput_1.setInt(3, DIM.ranking);
}

                        if(DIM.gender == null) {
pstmtInsert_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(4, DIM.gender);
}

                        if(DIM.nationality == null) {
pstmtInsert_tDBOutput_1.setNull(5, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(5, DIM.nationality);
}

                        if(DIM.birthdate != null) {
pstmtInsert_tDBOutput_1.setTimestamp(6, new java.sql.Timestamp(DIM.birthdate.getTime()));
} else {
pstmtInsert_tDBOutput_1.setNull(6, java.sql.Types.TIMESTAMP);
}

                        if(DIM.height_cm == null) {
pstmtInsert_tDBOutput_1.setNull(7, java.sql.Types.INTEGER);
} else {pstmtInsert_tDBOutput_1.setShort(7, DIM.height_cm);
}

                        if(DIM.playing_hand == null) {
pstmtInsert_tDBOutput_1.setNull(8, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(8, DIM.playing_hand);
}

                        if(DIM.court_side == null) {
pstmtInsert_tDBOutput_1.setNull(9, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(9, DIM.court_side);
}

                        if(DIM.partner_name == null) {
pstmtInsert_tDBOutput_1.setNull(10, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(10, DIM.partner_name);
}

                        if(DIM.total_points == null) {
pstmtInsert_tDBOutput_1.setNull(11, java.sql.Types.INTEGER);
} else {pstmtInsert_tDBOutput_1.setInt(11, DIM.total_points);
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
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row1");
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
					if(tHash_Lookup_row4 != null) {
						tHash_Lookup_row4.endGet();
					}
					globalMap.remove( "tHash_Lookup_row4" );

					
					
				
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
					     			globalMap.remove("tHash_Lookup_row4"); 
				     			
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
	


public static class row4Struct implements routines.system.IPersistableComparableLookupRow<row4Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_dim_player = new byte[0];
    static byte[] commonByteArray_DW_PADEL_dim_player = new byte[0];
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
			if(length > commonByteArray_DW_PADEL_dim_player.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_player.length == 0) {
   					commonByteArray_DW_PADEL_dim_player = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_player = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_dim_player, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_player, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_dim_player.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_player.length == 0) {
   					commonByteArray_DW_PADEL_dim_player = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_player = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_dim_player, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_player, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_dim_player) {

        	try {

        		int length = 0;
		
					this.full_name = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_dim_player) {

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
			   		// source node:tDBInput_2 - inputs:(after_tDBInput_1) outputs:(row4,row4) | target node:tAdvancedHash_row4 - inputs:(row4) outputs:()
			   		// linked node: tMap_1 - inputs:(row3,row4) outputs:(DIM)
			   
			   		org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row4 = 
			   			org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;
			   			
			   
	   			org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct> tHash_Lookup_row4 =org.talend.designer.components.lookup.memory.AdvancedMemoryLookup.
	   						<row4Struct>getLookup(matchingModeEnum_row4);
	   						   
		   	   	   globalMap.put("tHash_Lookup_row4", tHash_Lookup_row4);
		   	   	   
				
           

 



/**
 * [tAdvancedHash_row4 begin ] stop
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
				
				 
	final String decryptedPassword_tDBInput_2 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:96Dro26QkKtFMYiskiVLsQ+4yG8gGVrCwxTmHn0fKWunoXc=");
				
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

		    String dbquery_tDBInput_2 = "SELECT dim_player.player_id,\n		dim_player.full_name,\n		dim_player.ranking,\n		dim_player.gender,\n		dim_player.nationalit"
+"y,\n		dim_player.birthdate,\n		dim_player.height_cm,\n		dim_player.playing_hand,\n		dim_player.court_side,\n		dim_player.part"
+"ner_name,\n		dim_player.total_points\nFROM	dim_player";
		    

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
								row4.player_id = 0;
							} else {
		                          
            row4.player_id = rs_tDBInput_2.getInt(1);
            if(rs_tDBInput_2.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 2) {
								row4.full_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(2);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.full_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row4.full_name = tmpContent_tDBInput_2;
                }
            } else {
                row4.full_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 3) {
								row4.ranking = null;
							} else {
		                          
            row4.ranking = rs_tDBInput_2.getInt(3);
            if(rs_tDBInput_2.wasNull()){
                    row4.ranking = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 4) {
								row4.gender = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(4);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(4).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.gender = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row4.gender = tmpContent_tDBInput_2;
                }
            } else {
                row4.gender = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 5) {
								row4.nationality = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(5);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(5).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.nationality = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row4.nationality = tmpContent_tDBInput_2;
                }
            } else {
                row4.nationality = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 6) {
								row4.birthdate = null;
							} else {
										
			row4.birthdate = mssqlGTU_tDBInput_2.getDate(rsmd_tDBInput_2, rs_tDBInput_2, 6);
			
		                    }
							if(colQtyInRs_tDBInput_2 < 7) {
								row4.height_cm = null;
							} else {
		                          
            row4.height_cm = rs_tDBInput_2.getShort(7);
            if(rs_tDBInput_2.wasNull()){
                    row4.height_cm = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 8) {
								row4.playing_hand = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(8);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(8).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.playing_hand = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row4.playing_hand = tmpContent_tDBInput_2;
                }
            } else {
                row4.playing_hand = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 9) {
								row4.court_side = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(9);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(9).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.court_side = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row4.court_side = tmpContent_tDBInput_2;
                }
            } else {
                row4.court_side = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 10) {
								row4.partner_name = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(10);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(10).toUpperCase(java.util.Locale.ENGLISH))) {
            		row4.partner_name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row4.partner_name = tmpContent_tDBInput_2;
                }
            } else {
                row4.partner_name = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 11) {
								row4.total_points = null;
							} else {
		                          
            row4.total_points = rs_tDBInput_2.getInt(11);
            if(rs_tDBInput_2.wasNull()){
                    row4.total_points = null;
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
	 * [tDBInput_2 finally ] start
	 */

	

	
	
	currentComponent="tDBInput_2";

	

 



/**
 * [tDBInput_2 finally ] stop
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
        final dim_player dim_playerClass = new dim_player();

        int exitCode = dim_playerClass.runJobInTOS(args);

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
            java.io.InputStream inContext = dim_player.class.getClassLoader().getResourceAsStream("dw_padel/dim_player_0_1/contexts/" + contextStr + ".properties");
            if (inContext == null) {
                inContext = dim_player.class.getClassLoader().getResourceAsStream("config/contexts/" + contextStr + ".properties");
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
            System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : dim_player");
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
 *     286717 characters generated by Talend Open Studio for Data Integration 
 *     on the 29 avril 2026 à 03:23:58 WAT
 ************************************************************************************************/