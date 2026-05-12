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


package dw_padel.dim_equipement_0_1;

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
 * Job: dim_equipement Purpose: <br>
 * Description:  <br>
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status 
 */
public class dim_equipement implements TalendJob {

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
	private final String jobName = "dim_equipement";
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
				dim_equipement.this.exception = e;
			}
		}
		if (!(e instanceof TalendException)) {
		try {
			for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
				if (m.getName().compareTo(currentComponent + "_error") == 0) {
					m.invoke(dim_equipement.this, new Object[] { e , currentComponent, globalMap});
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
			
			public void tAdvancedHash_row3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_1_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
	






public static class DIMMStruct implements routines.system.IPersistableRow<DIMMStruct> {
    final static byte[] commonByteArrayLock_DW_PADEL_dim_equipement = new byte[0];
    static byte[] commonByteArray_DW_PADEL_dim_equipement = new byte[0];
	protected static final int DEFAULT_HASHCODE = 1;
    protected static final int PRIME = 31;
    protected int hashCode = DEFAULT_HASHCODE;
    public boolean hashCodeDirty = true;

    public String loopKey;



	
			    public int equipement_id;

				public int getEquipement_id () {
					return this.equipement_id;
				}
				
			    public String title;

				public String getTitle () {
					return this.title;
				}
				
			    public String handle;

				public String getHandle () {
					return this.handle;
				}
				
			    public String vendor;

				public String getVendor () {
					return this.vendor;
				}
				
			    public String product_type;

				public String getProduct_type () {
					return this.product_type;
				}
				
			    public String tags;

				public String getTags () {
					return this.tags;
				}
				
			    public java.util.Date created_at;

				public java.util.Date getCreated_at () {
					return this.created_at;
				}
				
			    public Double price;

				public Double getPrice () {
					return this.price;
				}
				
			    public Integer sku;

				public Integer getSku () {
					return this.sku;
				}
				
			    public String image;

				public String getImage () {
					return this.image;
				}
				
			    public String weight;

				public String getWeight () {
					return this.weight;
				}
				
			    public String shape;

				public String getShape () {
					return this.shape;
				}
				
			    public String foam;

				public String getFoam () {
					return this.foam;
				}
				
			    public String collection;

				public String getCollection () {
					return this.collection;
				}
				
			    public String game_level;

				public String getGame_level () {
					return this.game_level;
				}
				
			    public String frame;

				public String getFrame () {
					return this.frame;
				}
				
			    public String surface;

				public String getSurface () {
					return this.surface;
				}
				
			    public String professional_player;

				public String getProfessional_player () {
					return this.professional_player;
				}
				
			    public String color;

				public String getColor () {
					return this.color;
				}
				
			    public String racket_type;

				public String getRacket_type () {
					return this.racket_type;
				}
				
			    public String balance;

				public String getBalance () {
					return this.balance;
				}
				
			    public String gender;

				public String getGender () {
					return this.gender;
				}
				
			    public String racket_cover;

				public String getRacket_cover () {
					return this.racket_cover;
				}
				
			    public String owner;

				public String getOwner () {
					return this.owner;
				}
				
			    public Double ratings;

				public Double getRatings () {
					return this.ratings;
				}
				


	@Override
	public int hashCode() {
		if (this.hashCodeDirty) {
			final int prime = PRIME;
			int result = DEFAULT_HASHCODE;
	
							result = prime * result + (int) this.equipement_id;
						
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
		final DIMMStruct other = (DIMMStruct) obj;
		
						if (this.equipement_id != other.equipement_id)
							return false;
					

		return true;
    }

	public void copyDataTo(DIMMStruct other) {

		other.equipement_id = this.equipement_id;
	            other.title = this.title;
	            other.handle = this.handle;
	            other.vendor = this.vendor;
	            other.product_type = this.product_type;
	            other.tags = this.tags;
	            other.created_at = this.created_at;
	            other.price = this.price;
	            other.sku = this.sku;
	            other.image = this.image;
	            other.weight = this.weight;
	            other.shape = this.shape;
	            other.foam = this.foam;
	            other.collection = this.collection;
	            other.game_level = this.game_level;
	            other.frame = this.frame;
	            other.surface = this.surface;
	            other.professional_player = this.professional_player;
	            other.color = this.color;
	            other.racket_type = this.racket_type;
	            other.balance = this.balance;
	            other.gender = this.gender;
	            other.racket_cover = this.racket_cover;
	            other.owner = this.owner;
	            other.ratings = this.ratings;
	            
	}

	public void copyKeysDataTo(DIMMStruct other) {

		other.equipement_id = this.equipement_id;
	            	
	}




	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_dim_equipement.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_equipement.length == 0) {
   					commonByteArray_DW_PADEL_dim_equipement = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_equipement = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_dim_equipement, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_equipement, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_dim_equipement.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_equipement.length == 0) {
   					commonByteArray_DW_PADEL_dim_equipement = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_equipement = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_dim_equipement, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_equipement, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_dim_equipement) {

        	try {

        		int length = 0;
		
			        this.equipement_id = dis.readInt();
					
					this.title = readString(dis);
					
					this.handle = readString(dis);
					
					this.vendor = readString(dis);
					
					this.product_type = readString(dis);
					
					this.tags = readString(dis);
					
					this.created_at = readDate(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.price = null;
           				} else {
           			    	this.price = dis.readDouble();
           				}
					
						this.sku = readInteger(dis);
					
					this.image = readString(dis);
					
					this.weight = readString(dis);
					
					this.shape = readString(dis);
					
					this.foam = readString(dis);
					
					this.collection = readString(dis);
					
					this.game_level = readString(dis);
					
					this.frame = readString(dis);
					
					this.surface = readString(dis);
					
					this.professional_player = readString(dis);
					
					this.color = readString(dis);
					
					this.racket_type = readString(dis);
					
					this.balance = readString(dis);
					
					this.gender = readString(dis);
					
					this.racket_cover = readString(dis);
					
					this.owner = readString(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.ratings = null;
           				} else {
           			    	this.ratings = dis.readDouble();
           				}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_dim_equipement) {

        	try {

        		int length = 0;
		
			        this.equipement_id = dis.readInt();
					
					this.title = readString(dis);
					
					this.handle = readString(dis);
					
					this.vendor = readString(dis);
					
					this.product_type = readString(dis);
					
					this.tags = readString(dis);
					
					this.created_at = readDate(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.price = null;
           				} else {
           			    	this.price = dis.readDouble();
           				}
					
						this.sku = readInteger(dis);
					
					this.image = readString(dis);
					
					this.weight = readString(dis);
					
					this.shape = readString(dis);
					
					this.foam = readString(dis);
					
					this.collection = readString(dis);
					
					this.game_level = readString(dis);
					
					this.frame = readString(dis);
					
					this.surface = readString(dis);
					
					this.professional_player = readString(dis);
					
					this.color = readString(dis);
					
					this.racket_type = readString(dis);
					
					this.balance = readString(dis);
					
					this.gender = readString(dis);
					
					this.racket_cover = readString(dis);
					
					this.owner = readString(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.ratings = null;
           				} else {
           			    	this.ratings = dis.readDouble();
           				}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// int
				
		            	dos.writeInt(this.equipement_id);
					
					// String
				
						writeString(this.title,dos);
					
					// String
				
						writeString(this.handle,dos);
					
					// String
				
						writeString(this.vendor,dos);
					
					// String
				
						writeString(this.product_type,dos);
					
					// String
				
						writeString(this.tags,dos);
					
					// java.util.Date
				
						writeDate(this.created_at,dos);
					
					// Double
				
						if(this.price == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.price);
		            	}
					
					// Integer
				
						writeInteger(this.sku,dos);
					
					// String
				
						writeString(this.image,dos);
					
					// String
				
						writeString(this.weight,dos);
					
					// String
				
						writeString(this.shape,dos);
					
					// String
				
						writeString(this.foam,dos);
					
					// String
				
						writeString(this.collection,dos);
					
					// String
				
						writeString(this.game_level,dos);
					
					// String
				
						writeString(this.frame,dos);
					
					// String
				
						writeString(this.surface,dos);
					
					// String
				
						writeString(this.professional_player,dos);
					
					// String
				
						writeString(this.color,dos);
					
					// String
				
						writeString(this.racket_type,dos);
					
					// String
				
						writeString(this.balance,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.racket_cover,dos);
					
					// String
				
						writeString(this.owner,dos);
					
					// Double
				
						if(this.ratings == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.ratings);
		            	}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// int
				
		            	dos.writeInt(this.equipement_id);
					
					// String
				
						writeString(this.title,dos);
					
					// String
				
						writeString(this.handle,dos);
					
					// String
				
						writeString(this.vendor,dos);
					
					// String
				
						writeString(this.product_type,dos);
					
					// String
				
						writeString(this.tags,dos);
					
					// java.util.Date
				
						writeDate(this.created_at,dos);
					
					// Double
				
						if(this.price == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.price);
		            	}
					
					// Integer
				
						writeInteger(this.sku,dos);
					
					// String
				
						writeString(this.image,dos);
					
					// String
				
						writeString(this.weight,dos);
					
					// String
				
						writeString(this.shape,dos);
					
					// String
				
						writeString(this.foam,dos);
					
					// String
				
						writeString(this.collection,dos);
					
					// String
				
						writeString(this.game_level,dos);
					
					// String
				
						writeString(this.frame,dos);
					
					// String
				
						writeString(this.surface,dos);
					
					// String
				
						writeString(this.professional_player,dos);
					
					// String
				
						writeString(this.color,dos);
					
					// String
				
						writeString(this.racket_type,dos);
					
					// String
				
						writeString(this.balance,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.racket_cover,dos);
					
					// String
				
						writeString(this.owner,dos);
					
					// Double
				
						if(this.ratings == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.ratings);
		            	}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("equipement_id="+String.valueOf(equipement_id));
		sb.append(",title="+title);
		sb.append(",handle="+handle);
		sb.append(",vendor="+vendor);
		sb.append(",product_type="+product_type);
		sb.append(",tags="+tags);
		sb.append(",created_at="+String.valueOf(created_at));
		sb.append(",price="+String.valueOf(price));
		sb.append(",sku="+String.valueOf(sku));
		sb.append(",image="+image);
		sb.append(",weight="+weight);
		sb.append(",shape="+shape);
		sb.append(",foam="+foam);
		sb.append(",collection="+collection);
		sb.append(",game_level="+game_level);
		sb.append(",frame="+frame);
		sb.append(",surface="+surface);
		sb.append(",professional_player="+professional_player);
		sb.append(",color="+color);
		sb.append(",racket_type="+racket_type);
		sb.append(",balance="+balance);
		sb.append(",gender="+gender);
		sb.append(",racket_cover="+racket_cover);
		sb.append(",owner="+owner);
		sb.append(",ratings="+String.valueOf(ratings));
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(DIMMStruct other) {

		int returnValue = -1;
		
						returnValue = checkNullsAndCompare(this.equipement_id, other.equipement_id);
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

public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
    final static byte[] commonByteArrayLock_DW_PADEL_dim_equipement = new byte[0];
    static byte[] commonByteArray_DW_PADEL_dim_equipement = new byte[0];

	
			    public String title;

				public String getTitle () {
					return this.title;
				}
				
			    public String handle;

				public String getHandle () {
					return this.handle;
				}
				
			    public String vendor;

				public String getVendor () {
					return this.vendor;
				}
				
			    public String product_type;

				public String getProduct_type () {
					return this.product_type;
				}
				
			    public String tags;

				public String getTags () {
					return this.tags;
				}
				
			    public String created_at;

				public String getCreated_at () {
					return this.created_at;
				}
				
			    public Double price;

				public Double getPrice () {
					return this.price;
				}
				
			    public Integer sku;

				public Integer getSku () {
					return this.sku;
				}
				
			    public String image;

				public String getImage () {
					return this.image;
				}
				
			    public String weight;

				public String getWeight () {
					return this.weight;
				}
				
			    public String shape;

				public String getShape () {
					return this.shape;
				}
				
			    public String foam;

				public String getFoam () {
					return this.foam;
				}
				
			    public String collection;

				public String getCollection () {
					return this.collection;
				}
				
			    public String game_level;

				public String getGame_level () {
					return this.game_level;
				}
				
			    public String frame;

				public String getFrame () {
					return this.frame;
				}
				
			    public String surface;

				public String getSurface () {
					return this.surface;
				}
				
			    public String professional_player;

				public String getProfessional_player () {
					return this.professional_player;
				}
				
			    public String color;

				public String getColor () {
					return this.color;
				}
				
			    public String racket_type;

				public String getRacket_type () {
					return this.racket_type;
				}
				
			    public String balance;

				public String getBalance () {
					return this.balance;
				}
				
			    public String gender;

				public String getGender () {
					return this.gender;
				}
				
			    public String racket_cover;

				public String getRacket_cover () {
					return this.racket_cover;
				}
				
			    public String owner;

				public String getOwner () {
					return this.owner;
				}
				
			    public Double ratings;

				public Double getRatings () {
					return this.ratings;
				}
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_dim_equipement.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_equipement.length == 0) {
   					commonByteArray_DW_PADEL_dim_equipement = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_equipement = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_dim_equipement, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_equipement, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_dim_equipement.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_equipement.length == 0) {
   					commonByteArray_DW_PADEL_dim_equipement = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_equipement = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_dim_equipement, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_equipement, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_dim_equipement) {

        	try {

        		int length = 0;
		
					this.title = readString(dis);
					
					this.handle = readString(dis);
					
					this.vendor = readString(dis);
					
					this.product_type = readString(dis);
					
					this.tags = readString(dis);
					
					this.created_at = readString(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.price = null;
           				} else {
           			    	this.price = dis.readDouble();
           				}
					
						this.sku = readInteger(dis);
					
					this.image = readString(dis);
					
					this.weight = readString(dis);
					
					this.shape = readString(dis);
					
					this.foam = readString(dis);
					
					this.collection = readString(dis);
					
					this.game_level = readString(dis);
					
					this.frame = readString(dis);
					
					this.surface = readString(dis);
					
					this.professional_player = readString(dis);
					
					this.color = readString(dis);
					
					this.racket_type = readString(dis);
					
					this.balance = readString(dis);
					
					this.gender = readString(dis);
					
					this.racket_cover = readString(dis);
					
					this.owner = readString(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.ratings = null;
           				} else {
           			    	this.ratings = dis.readDouble();
           				}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_dim_equipement) {

        	try {

        		int length = 0;
		
					this.title = readString(dis);
					
					this.handle = readString(dis);
					
					this.vendor = readString(dis);
					
					this.product_type = readString(dis);
					
					this.tags = readString(dis);
					
					this.created_at = readString(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.price = null;
           				} else {
           			    	this.price = dis.readDouble();
           				}
					
						this.sku = readInteger(dis);
					
					this.image = readString(dis);
					
					this.weight = readString(dis);
					
					this.shape = readString(dis);
					
					this.foam = readString(dis);
					
					this.collection = readString(dis);
					
					this.game_level = readString(dis);
					
					this.frame = readString(dis);
					
					this.surface = readString(dis);
					
					this.professional_player = readString(dis);
					
					this.color = readString(dis);
					
					this.racket_type = readString(dis);
					
					this.balance = readString(dis);
					
					this.gender = readString(dis);
					
					this.racket_cover = readString(dis);
					
					this.owner = readString(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.ratings = null;
           				} else {
           			    	this.ratings = dis.readDouble();
           				}
					
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
				
						writeString(this.handle,dos);
					
					// String
				
						writeString(this.vendor,dos);
					
					// String
				
						writeString(this.product_type,dos);
					
					// String
				
						writeString(this.tags,dos);
					
					// String
				
						writeString(this.created_at,dos);
					
					// Double
				
						if(this.price == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.price);
		            	}
					
					// Integer
				
						writeInteger(this.sku,dos);
					
					// String
				
						writeString(this.image,dos);
					
					// String
				
						writeString(this.weight,dos);
					
					// String
				
						writeString(this.shape,dos);
					
					// String
				
						writeString(this.foam,dos);
					
					// String
				
						writeString(this.collection,dos);
					
					// String
				
						writeString(this.game_level,dos);
					
					// String
				
						writeString(this.frame,dos);
					
					// String
				
						writeString(this.surface,dos);
					
					// String
				
						writeString(this.professional_player,dos);
					
					// String
				
						writeString(this.color,dos);
					
					// String
				
						writeString(this.racket_type,dos);
					
					// String
				
						writeString(this.balance,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.racket_cover,dos);
					
					// String
				
						writeString(this.owner,dos);
					
					// Double
				
						if(this.ratings == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.ratings);
		            	}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.title,dos);
					
					// String
				
						writeString(this.handle,dos);
					
					// String
				
						writeString(this.vendor,dos);
					
					// String
				
						writeString(this.product_type,dos);
					
					// String
				
						writeString(this.tags,dos);
					
					// String
				
						writeString(this.created_at,dos);
					
					// Double
				
						if(this.price == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.price);
		            	}
					
					// Integer
				
						writeInteger(this.sku,dos);
					
					// String
				
						writeString(this.image,dos);
					
					// String
				
						writeString(this.weight,dos);
					
					// String
				
						writeString(this.shape,dos);
					
					// String
				
						writeString(this.foam,dos);
					
					// String
				
						writeString(this.collection,dos);
					
					// String
				
						writeString(this.game_level,dos);
					
					// String
				
						writeString(this.frame,dos);
					
					// String
				
						writeString(this.surface,dos);
					
					// String
				
						writeString(this.professional_player,dos);
					
					// String
				
						writeString(this.color,dos);
					
					// String
				
						writeString(this.racket_type,dos);
					
					// String
				
						writeString(this.balance,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.racket_cover,dos);
					
					// String
				
						writeString(this.owner,dos);
					
					// Double
				
						if(this.ratings == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.ratings);
		            	}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("title="+title);
		sb.append(",handle="+handle);
		sb.append(",vendor="+vendor);
		sb.append(",product_type="+product_type);
		sb.append(",tags="+tags);
		sb.append(",created_at="+created_at);
		sb.append(",price="+String.valueOf(price));
		sb.append(",sku="+String.valueOf(sku));
		sb.append(",image="+image);
		sb.append(",weight="+weight);
		sb.append(",shape="+shape);
		sb.append(",foam="+foam);
		sb.append(",collection="+collection);
		sb.append(",game_level="+game_level);
		sb.append(",frame="+frame);
		sb.append(",surface="+surface);
		sb.append(",professional_player="+professional_player);
		sb.append(",color="+color);
		sb.append(",racket_type="+racket_type);
		sb.append(",balance="+balance);
		sb.append(",gender="+gender);
		sb.append(",racket_cover="+racket_cover);
		sb.append(",owner="+owner);
		sb.append(",ratings="+String.valueOf(ratings));
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
    final static byte[] commonByteArrayLock_DW_PADEL_dim_equipement = new byte[0];
    static byte[] commonByteArray_DW_PADEL_dim_equipement = new byte[0];

	
			    public String title;

				public String getTitle () {
					return this.title;
				}
				
			    public String handle;

				public String getHandle () {
					return this.handle;
				}
				
			    public String vendor;

				public String getVendor () {
					return this.vendor;
				}
				
			    public String product_type;

				public String getProduct_type () {
					return this.product_type;
				}
				
			    public String tags;

				public String getTags () {
					return this.tags;
				}
				
			    public String created_at;

				public String getCreated_at () {
					return this.created_at;
				}
				
			    public Double price;

				public Double getPrice () {
					return this.price;
				}
				
			    public Integer sku;

				public Integer getSku () {
					return this.sku;
				}
				
			    public String image;

				public String getImage () {
					return this.image;
				}
				
			    public String weight;

				public String getWeight () {
					return this.weight;
				}
				
			    public String shape;

				public String getShape () {
					return this.shape;
				}
				
			    public String foam;

				public String getFoam () {
					return this.foam;
				}
				
			    public String collection;

				public String getCollection () {
					return this.collection;
				}
				
			    public String game_level;

				public String getGame_level () {
					return this.game_level;
				}
				
			    public String frame;

				public String getFrame () {
					return this.frame;
				}
				
			    public String surface;

				public String getSurface () {
					return this.surface;
				}
				
			    public String professional_player;

				public String getProfessional_player () {
					return this.professional_player;
				}
				
			    public String color;

				public String getColor () {
					return this.color;
				}
				
			    public String racket_type;

				public String getRacket_type () {
					return this.racket_type;
				}
				
			    public String balance;

				public String getBalance () {
					return this.balance;
				}
				
			    public String gender;

				public String getGender () {
					return this.gender;
				}
				
			    public String racket_cover;

				public String getRacket_cover () {
					return this.racket_cover;
				}
				
			    public String owner;

				public String getOwner () {
					return this.owner;
				}
				
			    public Double ratings;

				public Double getRatings () {
					return this.ratings;
				}
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_dim_equipement.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_equipement.length == 0) {
   					commonByteArray_DW_PADEL_dim_equipement = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_equipement = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_dim_equipement, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_equipement, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_dim_equipement.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_equipement.length == 0) {
   					commonByteArray_DW_PADEL_dim_equipement = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_equipement = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_dim_equipement, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_equipement, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_dim_equipement) {

        	try {

        		int length = 0;
		
					this.title = readString(dis);
					
					this.handle = readString(dis);
					
					this.vendor = readString(dis);
					
					this.product_type = readString(dis);
					
					this.tags = readString(dis);
					
					this.created_at = readString(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.price = null;
           				} else {
           			    	this.price = dis.readDouble();
           				}
					
						this.sku = readInteger(dis);
					
					this.image = readString(dis);
					
					this.weight = readString(dis);
					
					this.shape = readString(dis);
					
					this.foam = readString(dis);
					
					this.collection = readString(dis);
					
					this.game_level = readString(dis);
					
					this.frame = readString(dis);
					
					this.surface = readString(dis);
					
					this.professional_player = readString(dis);
					
					this.color = readString(dis);
					
					this.racket_type = readString(dis);
					
					this.balance = readString(dis);
					
					this.gender = readString(dis);
					
					this.racket_cover = readString(dis);
					
					this.owner = readString(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.ratings = null;
           				} else {
           			    	this.ratings = dis.readDouble();
           				}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_dim_equipement) {

        	try {

        		int length = 0;
		
					this.title = readString(dis);
					
					this.handle = readString(dis);
					
					this.vendor = readString(dis);
					
					this.product_type = readString(dis);
					
					this.tags = readString(dis);
					
					this.created_at = readString(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.price = null;
           				} else {
           			    	this.price = dis.readDouble();
           				}
					
						this.sku = readInteger(dis);
					
					this.image = readString(dis);
					
					this.weight = readString(dis);
					
					this.shape = readString(dis);
					
					this.foam = readString(dis);
					
					this.collection = readString(dis);
					
					this.game_level = readString(dis);
					
					this.frame = readString(dis);
					
					this.surface = readString(dis);
					
					this.professional_player = readString(dis);
					
					this.color = readString(dis);
					
					this.racket_type = readString(dis);
					
					this.balance = readString(dis);
					
					this.gender = readString(dis);
					
					this.racket_cover = readString(dis);
					
					this.owner = readString(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.ratings = null;
           				} else {
           			    	this.ratings = dis.readDouble();
           				}
					
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
				
						writeString(this.handle,dos);
					
					// String
				
						writeString(this.vendor,dos);
					
					// String
				
						writeString(this.product_type,dos);
					
					// String
				
						writeString(this.tags,dos);
					
					// String
				
						writeString(this.created_at,dos);
					
					// Double
				
						if(this.price == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.price);
		            	}
					
					// Integer
				
						writeInteger(this.sku,dos);
					
					// String
				
						writeString(this.image,dos);
					
					// String
				
						writeString(this.weight,dos);
					
					// String
				
						writeString(this.shape,dos);
					
					// String
				
						writeString(this.foam,dos);
					
					// String
				
						writeString(this.collection,dos);
					
					// String
				
						writeString(this.game_level,dos);
					
					// String
				
						writeString(this.frame,dos);
					
					// String
				
						writeString(this.surface,dos);
					
					// String
				
						writeString(this.professional_player,dos);
					
					// String
				
						writeString(this.color,dos);
					
					// String
				
						writeString(this.racket_type,dos);
					
					// String
				
						writeString(this.balance,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.racket_cover,dos);
					
					// String
				
						writeString(this.owner,dos);
					
					// Double
				
						if(this.ratings == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.ratings);
		            	}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.title,dos);
					
					// String
				
						writeString(this.handle,dos);
					
					// String
				
						writeString(this.vendor,dos);
					
					// String
				
						writeString(this.product_type,dos);
					
					// String
				
						writeString(this.tags,dos);
					
					// String
				
						writeString(this.created_at,dos);
					
					// Double
				
						if(this.price == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.price);
		            	}
					
					// Integer
				
						writeInteger(this.sku,dos);
					
					// String
				
						writeString(this.image,dos);
					
					// String
				
						writeString(this.weight,dos);
					
					// String
				
						writeString(this.shape,dos);
					
					// String
				
						writeString(this.foam,dos);
					
					// String
				
						writeString(this.collection,dos);
					
					// String
				
						writeString(this.game_level,dos);
					
					// String
				
						writeString(this.frame,dos);
					
					// String
				
						writeString(this.surface,dos);
					
					// String
				
						writeString(this.professional_player,dos);
					
					// String
				
						writeString(this.color,dos);
					
					// String
				
						writeString(this.racket_type,dos);
					
					// String
				
						writeString(this.balance,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.racket_cover,dos);
					
					// String
				
						writeString(this.owner,dos);
					
					// Double
				
						if(this.ratings == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.ratings);
		            	}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("title="+title);
		sb.append(",handle="+handle);
		sb.append(",vendor="+vendor);
		sb.append(",product_type="+product_type);
		sb.append(",tags="+tags);
		sb.append(",created_at="+created_at);
		sb.append(",price="+String.valueOf(price));
		sb.append(",sku="+String.valueOf(sku));
		sb.append(",image="+image);
		sb.append(",weight="+weight);
		sb.append(",shape="+shape);
		sb.append(",foam="+foam);
		sb.append(",collection="+collection);
		sb.append(",game_level="+game_level);
		sb.append(",frame="+frame);
		sb.append(",surface="+surface);
		sb.append(",professional_player="+professional_player);
		sb.append(",color="+color);
		sb.append(",racket_type="+racket_type);
		sb.append(",balance="+balance);
		sb.append(",gender="+gender);
		sb.append(",racket_cover="+racket_cover);
		sb.append(",owner="+owner);
		sb.append(",ratings="+String.valueOf(ratings));
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
    final static byte[] commonByteArrayLock_DW_PADEL_dim_equipement = new byte[0];
    static byte[] commonByteArray_DW_PADEL_dim_equipement = new byte[0];

	
			    public String title;

				public String getTitle () {
					return this.title;
				}
				
			    public String handle;

				public String getHandle () {
					return this.handle;
				}
				
			    public String vendor;

				public String getVendor () {
					return this.vendor;
				}
				
			    public String product_type;

				public String getProduct_type () {
					return this.product_type;
				}
				
			    public String tags;

				public String getTags () {
					return this.tags;
				}
				
			    public String created_at;

				public String getCreated_at () {
					return this.created_at;
				}
				
			    public Double price;

				public Double getPrice () {
					return this.price;
				}
				
			    public Integer sku;

				public Integer getSku () {
					return this.sku;
				}
				
			    public String image;

				public String getImage () {
					return this.image;
				}
				
			    public String weight;

				public String getWeight () {
					return this.weight;
				}
				
			    public String shape;

				public String getShape () {
					return this.shape;
				}
				
			    public String foam;

				public String getFoam () {
					return this.foam;
				}
				
			    public String collection;

				public String getCollection () {
					return this.collection;
				}
				
			    public String game_level;

				public String getGame_level () {
					return this.game_level;
				}
				
			    public String frame;

				public String getFrame () {
					return this.frame;
				}
				
			    public String surface;

				public String getSurface () {
					return this.surface;
				}
				
			    public String professional_player;

				public String getProfessional_player () {
					return this.professional_player;
				}
				
			    public String color;

				public String getColor () {
					return this.color;
				}
				
			    public String racket_type;

				public String getRacket_type () {
					return this.racket_type;
				}
				
			    public String balance;

				public String getBalance () {
					return this.balance;
				}
				
			    public String gender;

				public String getGender () {
					return this.gender;
				}
				
			    public String racket_cover;

				public String getRacket_cover () {
					return this.racket_cover;
				}
				
			    public String owner;

				public String getOwner () {
					return this.owner;
				}
				
			    public Double ratings;

				public Double getRatings () {
					return this.ratings;
				}
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_dim_equipement.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_equipement.length == 0) {
   					commonByteArray_DW_PADEL_dim_equipement = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_equipement = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_dim_equipement, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_equipement, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_dim_equipement.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_equipement.length == 0) {
   					commonByteArray_DW_PADEL_dim_equipement = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_equipement = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_dim_equipement, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_equipement, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_DW_PADEL_dim_equipement) {

        	try {

        		int length = 0;
		
					this.title = readString(dis);
					
					this.handle = readString(dis);
					
					this.vendor = readString(dis);
					
					this.product_type = readString(dis);
					
					this.tags = readString(dis);
					
					this.created_at = readString(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.price = null;
           				} else {
           			    	this.price = dis.readDouble();
           				}
					
						this.sku = readInteger(dis);
					
					this.image = readString(dis);
					
					this.weight = readString(dis);
					
					this.shape = readString(dis);
					
					this.foam = readString(dis);
					
					this.collection = readString(dis);
					
					this.game_level = readString(dis);
					
					this.frame = readString(dis);
					
					this.surface = readString(dis);
					
					this.professional_player = readString(dis);
					
					this.color = readString(dis);
					
					this.racket_type = readString(dis);
					
					this.balance = readString(dis);
					
					this.gender = readString(dis);
					
					this.racket_cover = readString(dis);
					
					this.owner = readString(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.ratings = null;
           				} else {
           			    	this.ratings = dis.readDouble();
           				}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_dim_equipement) {

        	try {

        		int length = 0;
		
					this.title = readString(dis);
					
					this.handle = readString(dis);
					
					this.vendor = readString(dis);
					
					this.product_type = readString(dis);
					
					this.tags = readString(dis);
					
					this.created_at = readString(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.price = null;
           				} else {
           			    	this.price = dis.readDouble();
           				}
					
						this.sku = readInteger(dis);
					
					this.image = readString(dis);
					
					this.weight = readString(dis);
					
					this.shape = readString(dis);
					
					this.foam = readString(dis);
					
					this.collection = readString(dis);
					
					this.game_level = readString(dis);
					
					this.frame = readString(dis);
					
					this.surface = readString(dis);
					
					this.professional_player = readString(dis);
					
					this.color = readString(dis);
					
					this.racket_type = readString(dis);
					
					this.balance = readString(dis);
					
					this.gender = readString(dis);
					
					this.racket_cover = readString(dis);
					
					this.owner = readString(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.ratings = null;
           				} else {
           			    	this.ratings = dis.readDouble();
           				}
					
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
				
						writeString(this.handle,dos);
					
					// String
				
						writeString(this.vendor,dos);
					
					// String
				
						writeString(this.product_type,dos);
					
					// String
				
						writeString(this.tags,dos);
					
					// String
				
						writeString(this.created_at,dos);
					
					// Double
				
						if(this.price == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.price);
		            	}
					
					// Integer
				
						writeInteger(this.sku,dos);
					
					// String
				
						writeString(this.image,dos);
					
					// String
				
						writeString(this.weight,dos);
					
					// String
				
						writeString(this.shape,dos);
					
					// String
				
						writeString(this.foam,dos);
					
					// String
				
						writeString(this.collection,dos);
					
					// String
				
						writeString(this.game_level,dos);
					
					// String
				
						writeString(this.frame,dos);
					
					// String
				
						writeString(this.surface,dos);
					
					// String
				
						writeString(this.professional_player,dos);
					
					// String
				
						writeString(this.color,dos);
					
					// String
				
						writeString(this.racket_type,dos);
					
					// String
				
						writeString(this.balance,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.racket_cover,dos);
					
					// String
				
						writeString(this.owner,dos);
					
					// Double
				
						if(this.ratings == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.ratings);
		            	}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.title,dos);
					
					// String
				
						writeString(this.handle,dos);
					
					// String
				
						writeString(this.vendor,dos);
					
					// String
				
						writeString(this.product_type,dos);
					
					// String
				
						writeString(this.tags,dos);
					
					// String
				
						writeString(this.created_at,dos);
					
					// Double
				
						if(this.price == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.price);
		            	}
					
					// Integer
				
						writeInteger(this.sku,dos);
					
					// String
				
						writeString(this.image,dos);
					
					// String
				
						writeString(this.weight,dos);
					
					// String
				
						writeString(this.shape,dos);
					
					// String
				
						writeString(this.foam,dos);
					
					// String
				
						writeString(this.collection,dos);
					
					// String
				
						writeString(this.game_level,dos);
					
					// String
				
						writeString(this.frame,dos);
					
					// String
				
						writeString(this.surface,dos);
					
					// String
				
						writeString(this.professional_player,dos);
					
					// String
				
						writeString(this.color,dos);
					
					// String
				
						writeString(this.racket_type,dos);
					
					// String
				
						writeString(this.balance,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.racket_cover,dos);
					
					// String
				
						writeString(this.owner,dos);
					
					// Double
				
						if(this.ratings == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.ratings);
		            	}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("title="+title);
		sb.append(",handle="+handle);
		sb.append(",vendor="+vendor);
		sb.append(",product_type="+product_type);
		sb.append(",tags="+tags);
		sb.append(",created_at="+created_at);
		sb.append(",price="+String.valueOf(price));
		sb.append(",sku="+String.valueOf(sku));
		sb.append(",image="+image);
		sb.append(",weight="+weight);
		sb.append(",shape="+shape);
		sb.append(",foam="+foam);
		sb.append(",collection="+collection);
		sb.append(",game_level="+game_level);
		sb.append(",frame="+frame);
		sb.append(",surface="+surface);
		sb.append(",professional_player="+professional_player);
		sb.append(",color="+color);
		sb.append(",racket_type="+racket_type);
		sb.append(",balance="+balance);
		sb.append(",gender="+gender);
		sb.append(",racket_cover="+racket_cover);
		sb.append(",owner="+owner);
		sb.append(",ratings="+String.valueOf(ratings));
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
DIMMStruct DIMM = new DIMMStruct();






	
	/**
	 * [tDBOutput_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tDBOutput_1", false);
		start_Hash.put("tDBOutput_1", System.currentTimeMillis());
		
	
	currentComponent="tDBOutput_1";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"DIMM");
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
        } else if (updateKeyCount_tDBOutput_1 == 25 && true) {
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

 
	final String decryptedPassword_tDBOutput_1 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:7+dade38NnnnnkL4hJYezffcEKBcZASDR7AIbaHWgHy+BdE=");

    String dbPwd_tDBOutput_1 = decryptedPassword_tDBOutput_1;	
    conn_tDBOutput_1 = java.sql.DriverManager.getConnection(url_tDBOutput_1,dbUser_tDBOutput_1,dbPwd_tDBOutput_1);
	
		resourceMap.put("conn_tDBOutput_1", conn_tDBOutput_1);
	
        conn_tDBOutput_1.setAutoCommit(false);
        int commitEvery_tDBOutput_1 = 10000;
        int commitCounter_tDBOutput_1 = 0;


if(dbschema_tDBOutput_1 == null || dbschema_tDBOutput_1.trim().length() == 0) {
    tableName_tDBOutput_1 = "dim_equipement";
} else {
    tableName_tDBOutput_1 = dbschema_tDBOutput_1 + "].[" + "dim_equipement";
}
	int count_tDBOutput_1=0;

        java.sql.PreparedStatement pstmt_tDBOutput_1 = conn_tDBOutput_1.prepareStatement("SELECT COUNT(1) FROM [" + tableName_tDBOutput_1 + "] WHERE [equipement_id] = ?");
        resourceMap.put("pstmt_tDBOutput_1", pstmt_tDBOutput_1);
        String insert_tDBOutput_1 = "INSERT INTO [" + tableName_tDBOutput_1 + "] ([equipement_id],[title],[handle],[vendor],[product_type],[tags],[created_at],[price],[sku],[image],[weight],[shape],[foam],[collection],[game_level],[frame],[surface],[professional_player],[color],[racket_type],[balance],[gender],[racket_cover],[owner],[ratings]) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        java.sql.PreparedStatement pstmtInsert_tDBOutput_1 = conn_tDBOutput_1.prepareStatement(insert_tDBOutput_1);
        resourceMap.put("pstmtInsert_tDBOutput_1", pstmtInsert_tDBOutput_1);
        String update_tDBOutput_1 = "UPDATE [" + tableName_tDBOutput_1 + "] SET [title] = ?,[handle] = ?,[vendor] = ?,[product_type] = ?,[tags] = ?,[created_at] = ?,[price] = ?,[sku] = ?,[image] = ?,[weight] = ?,[shape] = ?,[foam] = ?,[collection] = ?,[game_level] = ?,[frame] = ?,[surface] = ?,[professional_player] = ?,[color] = ?,[racket_type] = ?,[balance] = ?,[gender] = ?,[racket_cover] = ?,[owner] = ?,[ratings] = ? WHERE [equipement_id] = ?";
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
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row2");
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
DIMMStruct DIMM_tmp = new DIMMStruct();
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
				
				 
	final String decryptedPassword_tDBInput_1 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:kslly2XTmCA56UIec22ZF0ilIzOxBiReOMkzaJ3COWxCk7E=");
				
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

		    String dbquery_tDBInput_1 = "SELECT equipement.title,\n		equipement.handle,\n		equipement.vendor,\n		equipement.product_type,\n		equipement.tags,\n		equi"
+"pement.created_at,\n		equipement.price,\n		equipement.sku,\n		equipement.image,\n		equipement.weight,\n		equipement.shape,\n		"
+"equipement.foam,\n		equipement.collection,\n		equipement.game_level,\n		equipement.frame,\n		equipement.surface,\n		equipemen"
+"t.professional_player,\n		equipement.color,\n		equipement.racket_type,\n		equipement.balance,\n		equipement.gender,\n		equipe"
+"ment.racket_cover,\n		equipement.owner,\n		equipement.ratings\nFROM	equipement";
		    

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
								row1.handle = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(2);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.handle = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.handle = tmpContent_tDBInput_1;
                }
            } else {
                row1.handle = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 3) {
								row1.vendor = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(3);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(3).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.vendor = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.vendor = tmpContent_tDBInput_1;
                }
            } else {
                row1.vendor = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 4) {
								row1.product_type = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(4);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(4).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.product_type = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.product_type = tmpContent_tDBInput_1;
                }
            } else {
                row1.product_type = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 5) {
								row1.tags = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(5);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(5).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.tags = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.tags = tmpContent_tDBInput_1;
                }
            } else {
                row1.tags = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 6) {
								row1.created_at = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(6);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(6).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.created_at = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.created_at = tmpContent_tDBInput_1;
                }
            } else {
                row1.created_at = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 7) {
								row1.price = null;
							} else {
	                         		
            row1.price = rs_tDBInput_1.getDouble(7);
            if(rs_tDBInput_1.wasNull()){
                    row1.price = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 8) {
								row1.sku = null;
							} else {
		                          
            row1.sku = rs_tDBInput_1.getInt(8);
            if(rs_tDBInput_1.wasNull()){
                    row1.sku = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 9) {
								row1.image = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(9);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(9).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.image = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.image = tmpContent_tDBInput_1;
                }
            } else {
                row1.image = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 10) {
								row1.weight = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(10);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(10).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.weight = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.weight = tmpContent_tDBInput_1;
                }
            } else {
                row1.weight = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 11) {
								row1.shape = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(11);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(11).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.shape = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.shape = tmpContent_tDBInput_1;
                }
            } else {
                row1.shape = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 12) {
								row1.foam = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(12);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(12).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.foam = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.foam = tmpContent_tDBInput_1;
                }
            } else {
                row1.foam = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 13) {
								row1.collection = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(13);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(13).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.collection = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.collection = tmpContent_tDBInput_1;
                }
            } else {
                row1.collection = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 14) {
								row1.game_level = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(14);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(14).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.game_level = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.game_level = tmpContent_tDBInput_1;
                }
            } else {
                row1.game_level = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 15) {
								row1.frame = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(15);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(15).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.frame = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.frame = tmpContent_tDBInput_1;
                }
            } else {
                row1.frame = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 16) {
								row1.surface = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(16);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(16).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.surface = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.surface = tmpContent_tDBInput_1;
                }
            } else {
                row1.surface = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 17) {
								row1.professional_player = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(17);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(17).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.professional_player = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.professional_player = tmpContent_tDBInput_1;
                }
            } else {
                row1.professional_player = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 18) {
								row1.color = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(18);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(18).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.color = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.color = tmpContent_tDBInput_1;
                }
            } else {
                row1.color = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 19) {
								row1.racket_type = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(19);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(19).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.racket_type = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.racket_type = tmpContent_tDBInput_1;
                }
            } else {
                row1.racket_type = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 20) {
								row1.balance = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(20);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(20).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.balance = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.balance = tmpContent_tDBInput_1;
                }
            } else {
                row1.balance = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 21) {
								row1.gender = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(21);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(21).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.gender = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.gender = tmpContent_tDBInput_1;
                }
            } else {
                row1.gender = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 22) {
								row1.racket_cover = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(22);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(22).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.racket_cover = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.racket_cover = tmpContent_tDBInput_1;
                }
            } else {
                row1.racket_cover = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 23) {
								row1.owner = null;
							} else {
	                         		
           		tmpContent_tDBInput_1 = rs_tDBInput_1.getString(23);
            if(tmpContent_tDBInput_1 != null) {
            	if (talendToDBList_tDBInput_1 .contains(rsmd_tDBInput_1.getColumnTypeName(23).toUpperCase(java.util.Locale.ENGLISH))) {
            		row1.owner = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
            	} else {
                	row1.owner = tmpContent_tDBInput_1;
                }
            } else {
                row1.owner = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 24) {
								row1.ratings = null;
							} else {
	                         		
            row1.ratings = rs_tDBInput_1.getDouble(24);
            if(rs_tDBInput_1.wasNull()){
                    row1.ratings = null;
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
					

          row2 = null;
    Operator_tFilterRow_1 ope_tFilterRow_1 = new Operator_tFilterRow_1("&&");
	        ope_tFilterRow_1.matches((row1.title != null)
	                       , "title!=null failed");
    
    if (ope_tFilterRow_1.getMatchFlag()) {
              if(row2 == null){ 
                row2 = new row2Struct();
              }
               row2.title = row1.title;
               row2.handle = row1.handle;
               row2.vendor = row1.vendor;
               row2.product_type = row1.product_type;
               row2.tags = row1.tags;
               row2.created_at = row1.created_at;
               row2.price = row1.price;
               row2.sku = row1.sku;
               row2.image = row1.image;
               row2.weight = row1.weight;
               row2.shape = row1.shape;
               row2.foam = row1.foam;
               row2.collection = row1.collection;
               row2.game_level = row1.game_level;
               row2.frame = row1.frame;
               row2.surface = row1.surface;
               row2.professional_player = row1.professional_player;
               row2.color = row1.color;
               row2.racket_type = row1.racket_type;
               row2.balance = row1.balance;
               row2.gender = row1.gender;
               row2.racket_cover = row1.racket_cover;
               row2.owner = row1.owner;
               row2.ratings = row1.ratings;    
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
// Start of branch "row2"
if(row2 != null) { 



	
	/**
	 * [tMap_1 main ] start
	 */

	

	
	
	currentComponent="tMap_1";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row2"
						
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
								
                        		    		    row3HashKey.title = row2.title ;
                        		    		

								
		                        	row3HashKey.hashCodeDirty = true;
                        		
	  					
	  							
			  					
			  					
	  					
		  							tHash_Lookup_row3.lookup( row3HashKey );

	  							

	  							

 								
		  				
	  								
						
									
  									  		
 								



							} // G_TM_M_020
			           		  	  
							
				           		if(tHash_Lookup_row3 != null && tHash_Lookup_row3.getCount(row3HashKey) > 1) { // G 071
			  							
			  						
									 		
									//System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row3' and it contains more one result from keys :  row3.title = '" + row3HashKey.title + "'");
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

DIMM = null;


// # Output table : 'DIMM'
DIMM_tmp.equipement_id = Numeric.sequence("s1",1,1) ;
DIMM_tmp.title = row2.title ;
DIMM_tmp.handle = row2.handle ;
DIMM_tmp.vendor = row2.vendor ;
DIMM_tmp.product_type = row2.product_type ;
DIMM_tmp.tags = row2.tags ;
DIMM_tmp.created_at = (row2.created_at == null || row2.created_at.trim().isEmpty()) ? null : TalendDate.parseDate("yyyy-MM-dd'T'HH:mm:ss", row2.created_at.replace("\"", "").substring(0, 19));
DIMM_tmp.price = row2.price ;
DIMM_tmp.sku = row2.sku ;
DIMM_tmp.image = row2.image ;
DIMM_tmp.weight = row2.weight ;
DIMM_tmp.shape = row2.shape ;
DIMM_tmp.foam = row2.foam ;
DIMM_tmp.collection = row2.collection ;
DIMM_tmp.game_level = row2.game_level ;
DIMM_tmp.frame = row2.frame ;
DIMM_tmp.surface = row2.surface ;
DIMM_tmp.professional_player = row2.professional_player ;
DIMM_tmp.color = row2.color ;
DIMM_tmp.racket_type = row2.racket_type ;
DIMM_tmp.balance = row2.balance ;
DIMM_tmp.gender = row2.gender ;
DIMM_tmp.racket_cover = row2.racket_cover ;
DIMM_tmp.owner = row2.owner ;
DIMM_tmp.ratings = row2.ratings ;
DIMM = DIMM_tmp;
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
// Start of branch "DIMM"
if(DIMM != null) { 



	
	/**
	 * [tDBOutput_1 main ] start
	 */

	

	
	
	currentComponent="tDBOutput_1";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"DIMM"
						
						);
					}
					



        whetherReject_tDBOutput_1 = false;


                    pstmt_tDBOutput_1.setInt(1, DIMM.equipement_id);

            int checkCount_tDBOutput_1 = -1;
            try (java.sql.ResultSet rs_tDBOutput_1 = pstmt_tDBOutput_1.executeQuery()) {
                while(rs_tDBOutput_1.next()) {
                    checkCount_tDBOutput_1 = rs_tDBOutput_1.getInt(1);
                }
            }
            if(checkCount_tDBOutput_1 > 0) {
                        if(DIMM.title == null) {
pstmtUpdate_tDBOutput_1.setNull(1, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(1, DIMM.title);
}

                        if(DIMM.handle == null) {
pstmtUpdate_tDBOutput_1.setNull(2, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(2, DIMM.handle);
}

                        if(DIMM.vendor == null) {
pstmtUpdate_tDBOutput_1.setNull(3, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(3, DIMM.vendor);
}

                        if(DIMM.product_type == null) {
pstmtUpdate_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(4, DIMM.product_type);
}

                        if(DIMM.tags == null) {
pstmtUpdate_tDBOutput_1.setNull(5, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(5, DIMM.tags);
}

                        if(DIMM.created_at != null) {
pstmtUpdate_tDBOutput_1.setTimestamp(6, new java.sql.Timestamp(DIMM.created_at.getTime()));
} else {
pstmtUpdate_tDBOutput_1.setNull(6, java.sql.Types.TIMESTAMP);
}

                        if(DIMM.price == null) {
pstmtUpdate_tDBOutput_1.setNull(7, java.sql.Types.DOUBLE);
} else {pstmtUpdate_tDBOutput_1.setDouble(7, DIMM.price);
}

                        if(DIMM.sku == null) {
pstmtUpdate_tDBOutput_1.setNull(8, java.sql.Types.INTEGER);
} else {pstmtUpdate_tDBOutput_1.setInt(8, DIMM.sku);
}

                        if(DIMM.image == null) {
pstmtUpdate_tDBOutput_1.setNull(9, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(9, DIMM.image);
}

                        if(DIMM.weight == null) {
pstmtUpdate_tDBOutput_1.setNull(10, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(10, DIMM.weight);
}

                        if(DIMM.shape == null) {
pstmtUpdate_tDBOutput_1.setNull(11, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(11, DIMM.shape);
}

                        if(DIMM.foam == null) {
pstmtUpdate_tDBOutput_1.setNull(12, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(12, DIMM.foam);
}

                        if(DIMM.collection == null) {
pstmtUpdate_tDBOutput_1.setNull(13, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(13, DIMM.collection);
}

                        if(DIMM.game_level == null) {
pstmtUpdate_tDBOutput_1.setNull(14, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(14, DIMM.game_level);
}

                        if(DIMM.frame == null) {
pstmtUpdate_tDBOutput_1.setNull(15, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(15, DIMM.frame);
}

                        if(DIMM.surface == null) {
pstmtUpdate_tDBOutput_1.setNull(16, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(16, DIMM.surface);
}

                        if(DIMM.professional_player == null) {
pstmtUpdate_tDBOutput_1.setNull(17, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(17, DIMM.professional_player);
}

                        if(DIMM.color == null) {
pstmtUpdate_tDBOutput_1.setNull(18, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(18, DIMM.color);
}

                        if(DIMM.racket_type == null) {
pstmtUpdate_tDBOutput_1.setNull(19, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(19, DIMM.racket_type);
}

                        if(DIMM.balance == null) {
pstmtUpdate_tDBOutput_1.setNull(20, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(20, DIMM.balance);
}

                        if(DIMM.gender == null) {
pstmtUpdate_tDBOutput_1.setNull(21, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(21, DIMM.gender);
}

                        if(DIMM.racket_cover == null) {
pstmtUpdate_tDBOutput_1.setNull(22, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(22, DIMM.racket_cover);
}

                        if(DIMM.owner == null) {
pstmtUpdate_tDBOutput_1.setNull(23, java.sql.Types.VARCHAR);
} else {pstmtUpdate_tDBOutput_1.setString(23, DIMM.owner);
}

                        if(DIMM.ratings == null) {
pstmtUpdate_tDBOutput_1.setNull(24, java.sql.Types.DOUBLE);
} else {pstmtUpdate_tDBOutput_1.setDouble(24, DIMM.ratings);
}


	                    

                        pstmtUpdate_tDBOutput_1.setInt(25 + count_tDBOutput_1, DIMM.equipement_id);

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
                        pstmtInsert_tDBOutput_1.setInt(1, DIMM.equipement_id);

                        if(DIMM.title == null) {
pstmtInsert_tDBOutput_1.setNull(2, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(2, DIMM.title);
}

                        if(DIMM.handle == null) {
pstmtInsert_tDBOutput_1.setNull(3, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(3, DIMM.handle);
}

                        if(DIMM.vendor == null) {
pstmtInsert_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(4, DIMM.vendor);
}

                        if(DIMM.product_type == null) {
pstmtInsert_tDBOutput_1.setNull(5, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(5, DIMM.product_type);
}

                        if(DIMM.tags == null) {
pstmtInsert_tDBOutput_1.setNull(6, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(6, DIMM.tags);
}

                        if(DIMM.created_at != null) {
pstmtInsert_tDBOutput_1.setTimestamp(7, new java.sql.Timestamp(DIMM.created_at.getTime()));
} else {
pstmtInsert_tDBOutput_1.setNull(7, java.sql.Types.TIMESTAMP);
}

                        if(DIMM.price == null) {
pstmtInsert_tDBOutput_1.setNull(8, java.sql.Types.DOUBLE);
} else {pstmtInsert_tDBOutput_1.setDouble(8, DIMM.price);
}

                        if(DIMM.sku == null) {
pstmtInsert_tDBOutput_1.setNull(9, java.sql.Types.INTEGER);
} else {pstmtInsert_tDBOutput_1.setInt(9, DIMM.sku);
}

                        if(DIMM.image == null) {
pstmtInsert_tDBOutput_1.setNull(10, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(10, DIMM.image);
}

                        if(DIMM.weight == null) {
pstmtInsert_tDBOutput_1.setNull(11, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(11, DIMM.weight);
}

                        if(DIMM.shape == null) {
pstmtInsert_tDBOutput_1.setNull(12, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(12, DIMM.shape);
}

                        if(DIMM.foam == null) {
pstmtInsert_tDBOutput_1.setNull(13, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(13, DIMM.foam);
}

                        if(DIMM.collection == null) {
pstmtInsert_tDBOutput_1.setNull(14, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(14, DIMM.collection);
}

                        if(DIMM.game_level == null) {
pstmtInsert_tDBOutput_1.setNull(15, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(15, DIMM.game_level);
}

                        if(DIMM.frame == null) {
pstmtInsert_tDBOutput_1.setNull(16, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(16, DIMM.frame);
}

                        if(DIMM.surface == null) {
pstmtInsert_tDBOutput_1.setNull(17, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(17, DIMM.surface);
}

                        if(DIMM.professional_player == null) {
pstmtInsert_tDBOutput_1.setNull(18, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(18, DIMM.professional_player);
}

                        if(DIMM.color == null) {
pstmtInsert_tDBOutput_1.setNull(19, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(19, DIMM.color);
}

                        if(DIMM.racket_type == null) {
pstmtInsert_tDBOutput_1.setNull(20, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(20, DIMM.racket_type);
}

                        if(DIMM.balance == null) {
pstmtInsert_tDBOutput_1.setNull(21, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(21, DIMM.balance);
}

                        if(DIMM.gender == null) {
pstmtInsert_tDBOutput_1.setNull(22, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(22, DIMM.gender);
}

                        if(DIMM.racket_cover == null) {
pstmtInsert_tDBOutput_1.setNull(23, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(23, DIMM.racket_cover);
}

                        if(DIMM.owner == null) {
pstmtInsert_tDBOutput_1.setNull(24, java.sql.Types.VARCHAR);
} else {pstmtInsert_tDBOutput_1.setString(24, DIMM.owner);
}

                        if(DIMM.ratings == null) {
pstmtInsert_tDBOutput_1.setNull(25, java.sql.Types.DOUBLE);
} else {pstmtInsert_tDBOutput_1.setDouble(25, DIMM.ratings);
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

} // End of branch "DIMM"




	
	/**
	 * [tMap_1 process_data_end ] start
	 */

	

	
	
	currentComponent="tMap_1";

	

 



/**
 * [tMap_1 process_data_end ] stop
 */

} // End of branch "row2"




	
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
					if(tHash_Lookup_row3 != null) {
						tHash_Lookup_row3.endGet();
					}
					globalMap.remove( "tHash_Lookup_row3" );

					
					
				
// ###############################      





				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row2");
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
			  		runStat.updateStat(resourceMap,iterateId,2,0,"DIMM");
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
    final static byte[] commonByteArrayLock_DW_PADEL_dim_equipement = new byte[0];
    static byte[] commonByteArray_DW_PADEL_dim_equipement = new byte[0];
	protected static final int DEFAULT_HASHCODE = 1;
    protected static final int PRIME = 31;
    protected int hashCode = DEFAULT_HASHCODE;
    public boolean hashCodeDirty = true;

    public String loopKey;



	
			    public int equipement_id;

				public int getEquipement_id () {
					return this.equipement_id;
				}
				
			    public String title;

				public String getTitle () {
					return this.title;
				}
				
			    public String handle;

				public String getHandle () {
					return this.handle;
				}
				
			    public String vendor;

				public String getVendor () {
					return this.vendor;
				}
				
			    public String product_type;

				public String getProduct_type () {
					return this.product_type;
				}
				
			    public String tags;

				public String getTags () {
					return this.tags;
				}
				
			    public java.util.Date created_at;

				public java.util.Date getCreated_at () {
					return this.created_at;
				}
				
			    public Double price;

				public Double getPrice () {
					return this.price;
				}
				
			    public Integer sku;

				public Integer getSku () {
					return this.sku;
				}
				
			    public String image;

				public String getImage () {
					return this.image;
				}
				
			    public String weight;

				public String getWeight () {
					return this.weight;
				}
				
			    public String shape;

				public String getShape () {
					return this.shape;
				}
				
			    public String foam;

				public String getFoam () {
					return this.foam;
				}
				
			    public String collection;

				public String getCollection () {
					return this.collection;
				}
				
			    public String game_level;

				public String getGame_level () {
					return this.game_level;
				}
				
			    public String frame;

				public String getFrame () {
					return this.frame;
				}
				
			    public String surface;

				public String getSurface () {
					return this.surface;
				}
				
			    public String professional_player;

				public String getProfessional_player () {
					return this.professional_player;
				}
				
			    public String color;

				public String getColor () {
					return this.color;
				}
				
			    public String racket_type;

				public String getRacket_type () {
					return this.racket_type;
				}
				
			    public String balance;

				public String getBalance () {
					return this.balance;
				}
				
			    public String gender;

				public String getGender () {
					return this.gender;
				}
				
			    public String racket_cover;

				public String getRacket_cover () {
					return this.racket_cover;
				}
				
			    public String owner;

				public String getOwner () {
					return this.owner;
				}
				
			    public Double ratings;

				public Double getRatings () {
					return this.ratings;
				}
				


	@Override
	public int hashCode() {
		if (this.hashCodeDirty) {
			final int prime = PRIME;
			int result = DEFAULT_HASHCODE;
	
						result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
					
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
		
						if (this.title == null) {
							if (other.title != null)
								return false;
						
						} else if (!this.title.equals(other.title))
						
							return false;
					

		return true;
    }

	public void copyDataTo(row3Struct other) {

		other.equipement_id = this.equipement_id;
	            other.title = this.title;
	            other.handle = this.handle;
	            other.vendor = this.vendor;
	            other.product_type = this.product_type;
	            other.tags = this.tags;
	            other.created_at = this.created_at;
	            other.price = this.price;
	            other.sku = this.sku;
	            other.image = this.image;
	            other.weight = this.weight;
	            other.shape = this.shape;
	            other.foam = this.foam;
	            other.collection = this.collection;
	            other.game_level = this.game_level;
	            other.frame = this.frame;
	            other.surface = this.surface;
	            other.professional_player = this.professional_player;
	            other.color = this.color;
	            other.racket_type = this.racket_type;
	            other.balance = this.balance;
	            other.gender = this.gender;
	            other.racket_cover = this.racket_cover;
	            other.owner = this.owner;
	            other.ratings = this.ratings;
	            
	}

	public void copyKeysDataTo(row3Struct other) {

		other.title = this.title;
	            	
	}




	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_DW_PADEL_dim_equipement.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_equipement.length == 0) {
   					commonByteArray_DW_PADEL_dim_equipement = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_equipement = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_DW_PADEL_dim_equipement, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_equipement, 0, length, utf8Charset);
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
			if(length > commonByteArray_DW_PADEL_dim_equipement.length) {
				if(length < 1024 && commonByteArray_DW_PADEL_dim_equipement.length == 0) {
   					commonByteArray_DW_PADEL_dim_equipement = new byte[1024];
				} else {
   					commonByteArray_DW_PADEL_dim_equipement = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_DW_PADEL_dim_equipement, 0, length);
			strReturn = new String(commonByteArray_DW_PADEL_dim_equipement, 0, length, utf8Charset);
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

    public void readKeysData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_DW_PADEL_dim_equipement) {

        	try {

        		int length = 0;
		
					this.title = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_DW_PADEL_dim_equipement) {

        	try {

        		int length = 0;
		
					this.title = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeKeysData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.title,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.title,dos);
					
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
		
			            this.equipement_id = dis.readInt();
					
						this.handle = readString(dis,ois);
					
						this.vendor = readString(dis,ois);
					
						this.product_type = readString(dis,ois);
					
						this.tags = readString(dis,ois);
					
						this.created_at = readDate(dis,ois);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.price = null;
           				} else {
           			    	this.price = dis.readDouble();
           				}
					
						this.sku = readInteger(dis,ois);
					
						this.image = readString(dis,ois);
					
						this.weight = readString(dis,ois);
					
						this.shape = readString(dis,ois);
					
						this.foam = readString(dis,ois);
					
						this.collection = readString(dis,ois);
					
						this.game_level = readString(dis,ois);
					
						this.frame = readString(dis,ois);
					
						this.surface = readString(dis,ois);
					
						this.professional_player = readString(dis,ois);
					
						this.color = readString(dis,ois);
					
						this.racket_type = readString(dis,ois);
					
						this.balance = readString(dis,ois);
					
						this.gender = readString(dis,ois);
					
						this.racket_cover = readString(dis,ois);
					
						this.owner = readString(dis,ois);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.ratings = null;
           				} else {
           			    	this.ratings = dis.readDouble();
           				}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }
    
    public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
        try {
			int length = 0;
		
			            this.equipement_id = objectIn.readInt();
					
						this.handle = readString(dis,objectIn);
					
						this.vendor = readString(dis,objectIn);
					
						this.product_type = readString(dis,objectIn);
					
						this.tags = readString(dis,objectIn);
					
						this.created_at = readDate(dis,objectIn);
					
			            length = objectIn.readByte();
           				if (length == -1) {
           	    			this.price = null;
           				} else {
           			    	this.price = objectIn.readDouble();
           				}
					
						this.sku = readInteger(dis,objectIn);
					
						this.image = readString(dis,objectIn);
					
						this.weight = readString(dis,objectIn);
					
						this.shape = readString(dis,objectIn);
					
						this.foam = readString(dis,objectIn);
					
						this.collection = readString(dis,objectIn);
					
						this.game_level = readString(dis,objectIn);
					
						this.frame = readString(dis,objectIn);
					
						this.surface = readString(dis,objectIn);
					
						this.professional_player = readString(dis,objectIn);
					
						this.color = readString(dis,objectIn);
					
						this.racket_type = readString(dis,objectIn);
					
						this.balance = readString(dis,objectIn);
					
						this.gender = readString(dis,objectIn);
					
						this.racket_cover = readString(dis,objectIn);
					
						this.owner = readString(dis,objectIn);
					
			            length = objectIn.readByte();
           				if (length == -1) {
           	    			this.ratings = null;
           				} else {
           			    	this.ratings = objectIn.readDouble();
           				}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }

    /**
     * Return a byte array which represents Values data.
     */
    public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
        try {

		
		            	dos.writeInt(this.equipement_id);
					
						writeString(this.handle, dos, oos);
					
						writeString(this.vendor, dos, oos);
					
						writeString(this.product_type, dos, oos);
					
						writeString(this.tags, dos, oos);
					
						writeDate(this.created_at, dos, oos);
					
						if(this.price == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.price);
		            	}
					
					writeInteger(this.sku, dos, oos);
					
						writeString(this.image, dos, oos);
					
						writeString(this.weight, dos, oos);
					
						writeString(this.shape, dos, oos);
					
						writeString(this.foam, dos, oos);
					
						writeString(this.collection, dos, oos);
					
						writeString(this.game_level, dos, oos);
					
						writeString(this.frame, dos, oos);
					
						writeString(this.surface, dos, oos);
					
						writeString(this.professional_player, dos, oos);
					
						writeString(this.color, dos, oos);
					
						writeString(this.racket_type, dos, oos);
					
						writeString(this.balance, dos, oos);
					
						writeString(this.gender, dos, oos);
					
						writeString(this.racket_cover, dos, oos);
					
						writeString(this.owner, dos, oos);
					
						if(this.ratings == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeDouble(this.ratings);
		            	}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}

    }
    
    public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut){
                try {

		
					objectOut.writeInt(this.equipement_id);
					
						writeString(this.handle, dos, objectOut);
					
						writeString(this.vendor, dos, objectOut);
					
						writeString(this.product_type, dos, objectOut);
					
						writeString(this.tags, dos, objectOut);
					
						writeDate(this.created_at, dos, objectOut);
					
						if(this.price == null) {
							objectOut.writeByte(-1);
						} else {
							objectOut.writeByte(0);
							objectOut.writeDouble(this.price);
		            	}
					
					writeInteger(this.sku, dos, objectOut);
					
						writeString(this.image, dos, objectOut);
					
						writeString(this.weight, dos, objectOut);
					
						writeString(this.shape, dos, objectOut);
					
						writeString(this.foam, dos, objectOut);
					
						writeString(this.collection, dos, objectOut);
					
						writeString(this.game_level, dos, objectOut);
					
						writeString(this.frame, dos, objectOut);
					
						writeString(this.surface, dos, objectOut);
					
						writeString(this.professional_player, dos, objectOut);
					
						writeString(this.color, dos, objectOut);
					
						writeString(this.racket_type, dos, objectOut);
					
						writeString(this.balance, dos, objectOut);
					
						writeString(this.gender, dos, objectOut);
					
						writeString(this.racket_cover, dos, objectOut);
					
						writeString(this.owner, dos, objectOut);
					
						if(this.ratings == null) {
							objectOut.writeByte(-1);
						} else {
							objectOut.writeByte(0);
							objectOut.writeDouble(this.ratings);
		            	}
					
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
		sb.append("equipement_id="+String.valueOf(equipement_id));
		sb.append(",title="+title);
		sb.append(",handle="+handle);
		sb.append(",vendor="+vendor);
		sb.append(",product_type="+product_type);
		sb.append(",tags="+tags);
		sb.append(",created_at="+String.valueOf(created_at));
		sb.append(",price="+String.valueOf(price));
		sb.append(",sku="+String.valueOf(sku));
		sb.append(",image="+image);
		sb.append(",weight="+weight);
		sb.append(",shape="+shape);
		sb.append(",foam="+foam);
		sb.append(",collection="+collection);
		sb.append(",game_level="+game_level);
		sb.append(",frame="+frame);
		sb.append(",surface="+surface);
		sb.append(",professional_player="+professional_player);
		sb.append(",color="+color);
		sb.append(",racket_type="+racket_type);
		sb.append(",balance="+balance);
		sb.append(",gender="+gender);
		sb.append(",racket_cover="+racket_cover);
		sb.append(",owner="+owner);
		sb.append(",ratings="+String.valueOf(ratings));
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(row3Struct other) {

		int returnValue = -1;
		
						returnValue = checkNullsAndCompare(this.title, other.title);
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
			   		// linked node: tMap_1 - inputs:(row2,row3) outputs:(DIMM)
			   
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
				
				 
	final String decryptedPassword_tDBInput_2 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:M8C0/NWO76PG09W0ZvQ6Onv95EQyqO6gC8h9s97VZgNomWs=");
				
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

		    String dbquery_tDBInput_2 = "SELECT dim_equipement.equipement_id,\n		dim_equipement.title,\n		dim_equipement.handle,\n		dim_equipement.vendor,\n		dim_eq"
+"uipement.product_type,\n		dim_equipement.tags,\n		dim_equipement.created_at,\n		dim_equipement.price,\n		dim_equipement.sku,"
+"\n		dim_equipement.image,\n		dim_equipement.weight,\n		dim_equipement.shape,\n		dim_equipement.foam,\n		dim_equipement.collec"
+"tion,\n		dim_equipement.game_level,\n		dim_equipement.frame,\n		dim_equipement.surface,\n		dim_equipement.professional_playe"
+"r,\n		dim_equipement.color,\n		dim_equipement.racket_type,\n		dim_equipement.balance,\n		dim_equipement.gender,\n		dim_equipe"
+"ment.racket_cover,\n		dim_equipement.owner,\n		dim_equipement.ratings\nFROM	dim_equipement";
		    

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
								row3.equipement_id = 0;
							} else {
		                          
            row3.equipement_id = rs_tDBInput_2.getInt(1);
            if(rs_tDBInput_2.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 2) {
								row3.title = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(2);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.title = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row3.title = tmpContent_tDBInput_2;
                }
            } else {
                row3.title = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 3) {
								row3.handle = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(3);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(3).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.handle = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row3.handle = tmpContent_tDBInput_2;
                }
            } else {
                row3.handle = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 4) {
								row3.vendor = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(4);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(4).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.vendor = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row3.vendor = tmpContent_tDBInput_2;
                }
            } else {
                row3.vendor = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 5) {
								row3.product_type = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(5);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(5).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.product_type = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row3.product_type = tmpContent_tDBInput_2;
                }
            } else {
                row3.product_type = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 6) {
								row3.tags = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(6);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(6).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.tags = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row3.tags = tmpContent_tDBInput_2;
                }
            } else {
                row3.tags = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 7) {
								row3.created_at = null;
							} else {
										
			row3.created_at = mssqlGTU_tDBInput_2.getDate(rsmd_tDBInput_2, rs_tDBInput_2, 7);
			
		                    }
							if(colQtyInRs_tDBInput_2 < 8) {
								row3.price = null;
							} else {
	                         		
            row3.price = rs_tDBInput_2.getDouble(8);
            if(rs_tDBInput_2.wasNull()){
                    row3.price = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 9) {
								row3.sku = null;
							} else {
		                          
            row3.sku = rs_tDBInput_2.getInt(9);
            if(rs_tDBInput_2.wasNull()){
                    row3.sku = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 10) {
								row3.image = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(10);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(10).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.image = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row3.image = tmpContent_tDBInput_2;
                }
            } else {
                row3.image = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 11) {
								row3.weight = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(11);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(11).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.weight = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row3.weight = tmpContent_tDBInput_2;
                }
            } else {
                row3.weight = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 12) {
								row3.shape = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(12);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(12).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.shape = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row3.shape = tmpContent_tDBInput_2;
                }
            } else {
                row3.shape = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 13) {
								row3.foam = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(13);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(13).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.foam = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row3.foam = tmpContent_tDBInput_2;
                }
            } else {
                row3.foam = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 14) {
								row3.collection = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(14);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(14).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.collection = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row3.collection = tmpContent_tDBInput_2;
                }
            } else {
                row3.collection = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 15) {
								row3.game_level = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(15);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(15).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.game_level = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row3.game_level = tmpContent_tDBInput_2;
                }
            } else {
                row3.game_level = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 16) {
								row3.frame = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(16);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(16).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.frame = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row3.frame = tmpContent_tDBInput_2;
                }
            } else {
                row3.frame = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 17) {
								row3.surface = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(17);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(17).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.surface = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row3.surface = tmpContent_tDBInput_2;
                }
            } else {
                row3.surface = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 18) {
								row3.professional_player = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(18);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(18).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.professional_player = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row3.professional_player = tmpContent_tDBInput_2;
                }
            } else {
                row3.professional_player = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 19) {
								row3.color = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(19);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(19).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.color = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row3.color = tmpContent_tDBInput_2;
                }
            } else {
                row3.color = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 20) {
								row3.racket_type = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(20);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(20).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.racket_type = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row3.racket_type = tmpContent_tDBInput_2;
                }
            } else {
                row3.racket_type = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 21) {
								row3.balance = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(21);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(21).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.balance = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row3.balance = tmpContent_tDBInput_2;
                }
            } else {
                row3.balance = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 22) {
								row3.gender = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(22);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(22).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.gender = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row3.gender = tmpContent_tDBInput_2;
                }
            } else {
                row3.gender = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 23) {
								row3.racket_cover = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(23);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(23).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.racket_cover = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row3.racket_cover = tmpContent_tDBInput_2;
                }
            } else {
                row3.racket_cover = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 24) {
								row3.owner = null;
							} else {
	                         		
           		tmpContent_tDBInput_2 = rs_tDBInput_2.getString(24);
            if(tmpContent_tDBInput_2 != null) {
            	if (talendToDBList_tDBInput_2 .contains(rsmd_tDBInput_2.getColumnTypeName(24).toUpperCase(java.util.Locale.ENGLISH))) {
            		row3.owner = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
            	} else {
                	row3.owner = tmpContent_tDBInput_2;
                }
            } else {
                row3.owner = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 25) {
								row3.ratings = null;
							} else {
	                         		
            row3.ratings = rs_tDBInput_2.getDouble(25);
            if(rs_tDBInput_2.wasNull()){
                    row3.ratings = null;
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
		   	   	   
				
				row3_HashRow.equipement_id = row3.equipement_id;
				
				row3_HashRow.title = row3.title;
				
				row3_HashRow.handle = row3.handle;
				
				row3_HashRow.vendor = row3.vendor;
				
				row3_HashRow.product_type = row3.product_type;
				
				row3_HashRow.tags = row3.tags;
				
				row3_HashRow.created_at = row3.created_at;
				
				row3_HashRow.price = row3.price;
				
				row3_HashRow.sku = row3.sku;
				
				row3_HashRow.image = row3.image;
				
				row3_HashRow.weight = row3.weight;
				
				row3_HashRow.shape = row3.shape;
				
				row3_HashRow.foam = row3.foam;
				
				row3_HashRow.collection = row3.collection;
				
				row3_HashRow.game_level = row3.game_level;
				
				row3_HashRow.frame = row3.frame;
				
				row3_HashRow.surface = row3.surface;
				
				row3_HashRow.professional_player = row3.professional_player;
				
				row3_HashRow.color = row3.color;
				
				row3_HashRow.racket_type = row3.racket_type;
				
				row3_HashRow.balance = row3.balance;
				
				row3_HashRow.gender = row3.gender;
				
				row3_HashRow.racket_cover = row3.racket_cover;
				
				row3_HashRow.owner = row3.owner;
				
				row3_HashRow.ratings = row3.ratings;
				
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
        final dim_equipement dim_equipementClass = new dim_equipement();

        int exitCode = dim_equipementClass.runJobInTOS(args);

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
            java.io.InputStream inContext = dim_equipement.class.getClassLoader().getResourceAsStream("dw_padel/dim_equipement_0_1/contexts/" + contextStr + ".properties");
            if (inContext == null) {
                inContext = dim_equipement.class.getClassLoader().getResourceAsStream("config/contexts/" + contextStr + ".properties");
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
            System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : dim_equipement");
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
 *     194039 characters generated by Talend Open Studio for Data Integration 
 *     on the 29 avril 2026 à 03:31:50 WAT
 ************************************************************************************************/