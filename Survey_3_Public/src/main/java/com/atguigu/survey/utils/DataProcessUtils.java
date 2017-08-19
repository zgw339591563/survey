package com.atguigu.survey.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.jstl.core.LoopTagStatus;

import com.atguigu.survey.entities.guest.Bag;
import com.atguigu.survey.entities.guest.Question;
import com.atguigu.survey.entities.manager.Auth;
import com.atguigu.survey.entities.manager.Res;
import com.atguigu.survey.entities.manager.Role;
import com.google.gson.Gson;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class DataProcessUtils {
	
	private static Gson gson = new Gson();
	public static String MD5(String source) {
		if (source == null || source.length() == 0) {
			return null;
		}
		// 1.准备工作
		StringBuilder builder = new StringBuilder();
		char[] c = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		
		//2.获取源字符串的字节数组
		byte[] bytes = source.getBytes();
		MessageDigest messageDigest=null;
		try {
			 messageDigest = MessageDigest.getInstance("md5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		//执行加密操作
		byte[] targetBytes = messageDigest.digest(bytes);
		//5.遍历字节数组
				for (int i = 0; i < targetBytes.length; i++) {
					byte b = targetBytes[i];
					
					//6.取当前字节的高四位值
					int highValue = (b >> 4) & 15;
					
					//7.取当前字节的低四位值
					int lowValue = b & 15;
					
					//8.以上面计算的结果为下标从字符数组中取值
					char highChar = c[highValue];
					char lowChar = c[lowValue];
					
					//9.拼装字符串
					builder.append(highChar).append(lowChar);
					
				}
				return builder.toString();
	}
	
	
	/**
	 * 将图片压缩按本来的长宽比例压缩为100宽度的jpg图片
	 * @param inputStream
	 * @param realPath /surveyLogos目录的真实路径，后面没有斜杠
	 * @return 将生成的文件路径返回 surveyLogos/4198393905112.jpg
	 */
	@SuppressWarnings("restriction")
	public static String resizeImages(InputStream inputStream, String realPath) {
		
		OutputStream out = null;
		
		try {
			//1.构造原始图片对应的Image对象
			BufferedImage sourceImage = ImageIO.read(inputStream);

			//2.获取原始图片的宽高值
			int sourceWidth = sourceImage.getWidth();
			int sourceHeight = sourceImage.getHeight();
			
			//3.计算目标图片的宽高值
			int targetWidth = sourceWidth;
			int targetHeight = sourceHeight;
			
			if(sourceWidth > 100) {
				//按比例压缩目标图片的尺寸
				targetWidth = 100;
				targetHeight = sourceHeight/(sourceWidth/100);
				
			}
			
			//4.创建压缩后的目标图片对应的Image对象
			BufferedImage targetImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
			
			//5.绘制目标图片
			targetImage.getGraphics().drawImage(sourceImage, 0, 0, targetWidth, targetHeight, null);
			
			//6.构造目标图片文件名
			String targetFileName = System.nanoTime() + ".jpg";
			
			//7.创建目标图片对应的输出流
			out = new FileOutputStream(realPath+"/"+targetFileName);
			
			//8.获取JPEG图片编码器
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			
			//9.JPEG编码
			encoder.encode(targetImage);
			
			//10.返回文件名
			return "surveyLogos/"+targetFileName;
			
		} catch (Exception e) {
				
			e.printStackTrace();
				
			return null;
		} finally {
			//10.关闭流
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	
	public static void processOptionTojson(Question question){
		
		if(question==null){
			return;
		}
		
		Integer questionType = question.getQuestionType();
		if(questionType==3){
			return;
		}
		String questionOptions = question.getQuestionOptions();
		if(questionOptions==null){
			return;
		}
		String Options = questionOptions.trim();
		 String[] splitOptions = Options.split("\r\n");
		 ArrayList<Object> list = new ArrayList<>();
		 for(String option: splitOptions){
			 String trim = option.trim();
			 list.add(trim);
		 }
		
		 String json = gson.toJson(list);
		 question.setQuestionOptions(json);
	}
	
	public static void processOptionFromJson(Question question){
		
		
		if(question==null){
			return;
		}
		if(question.getQuestionType()==3){
			return;
		}
		String json = question.getQuestionOptions();
		if(json==null||json.length()==0){
			return;
		}
		
		// List<String> Optionlist = gson.fromJson(json, List.class);
		 List<String> Optionlist = convertJSONToList( json);
		 StringBuffer stringBuffer = new StringBuffer();
		 for(String option:Optionlist){
			 stringBuffer.append(option).append("\r\n");
		 }
		 String options= stringBuffer.toString().trim();
		 question.setQuestionOptions(options);
	}


	public static List<String> convertJSONToList(String json) {
		List<String> Optionlist = gson.fromJson(json, List.class);
		return Optionlist;
	}
	
	
	/**
	 * 通过序列化、反序列化方式对对象进行深度复制
	 */
	
	public static Object deeplyCopy(Serializable sourceObject){
		
		ByteArrayOutputStream byteArrayOutputStream =null;
		ObjectOutputStream objectOutputStream = null;
		ByteArrayInputStream byteArrayInputStream =null;
		ObjectInputStream objectInputStream = null;
		Object targetobj=null;
		try {
			 byteArrayOutputStream = new ByteArrayOutputStream();
			 objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(sourceObject);
			byte[] byteArray = byteArrayOutputStream.toByteArray();
			 byteArrayInputStream = new ByteArrayInputStream(byteArray);
			 objectInputStream = new ObjectInputStream(byteArrayInputStream);
			 targetobj = objectInputStream.readObject();
			
		} catch (Exception e) {

			e.printStackTrace();
			
		}finally{
			if(objectInputStream!=null){
				try {
					objectInputStream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if(objectOutputStream!=null){
				try {
					objectOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return targetobj;
		
	}
	
	public static String checkedReDisplay(PageContext pageContext){
		ServletRequest request = pageContext.getRequest();//得到当前页面的请求
		HttpSession httpSession = pageContext.getSession();
		Bag bag =(Bag) request.getAttribute(GlobalSettings.CURRENT_BAG);//得到请求中传入的bag
		Integer bagId = bag.getBagId();
		//获取全局bagmap
		Map<Integer,Map<String,String[]>> allBagMap = (Map<Integer,Map<String,String[]>>)httpSession.getAttribute(GlobalSettings.ALL_BAGMAP);
		
		Map<String, String[]> parameterMap = allBagMap.get(bagId);//找到相应参数的map
		if(parameterMap==null||parameterMap.isEmpty()){
			return "";
		}
		Question question =(Question) pageContext.findAttribute("question");//获取当前正在被遍历到的question
		Integer questionId = question.getQuestionId();
		String paramName="q"+questionId;//拼接得到name属性的值
		String[] paramValues = parameterMap.get(paramName);//得到name属性相应的value值
		if(paramValues==null||paramValues.length==0){
			return "";
		}
		if(question.getQuestionType()==1||question.getQuestionType()==2){
			LoopTagStatus optionStatus = (LoopTagStatus) pageContext.findAttribute("MyvarStatus");//得到optionStatus用于获取当前循环的信息
			int currentIndex = optionStatus.getIndex();//得到当前循环的索引值
			String currentValue=currentIndex+"";
			List<String> list = Arrays.asList(paramValues);
			if(list.contains(currentValue)){//进行比较找出之前被选过的值使其被选中（通过checked来选中）
				return "checked='checked'";
			}
		}
		
		if(question.getQuestionType()==3){
			return  "value='"+paramValues[0]+"'";
		}
		
		return "";
	}


	public static Integer parseQuestionId(String paramName) {
		String questionId = paramName.substring(1);
		int qId = Integer.parseInt(questionId);
		return qId;
	}


	public static String joinAnswerContent(String[] paramVals) {
		List<String> paramValList = Arrays.asList(paramVals);
		StringBuffer stringBuffer = new StringBuffer();
		for(String paramVal:paramValList){
			stringBuffer.append(",").append(paramVal);
		}
		String answerContent = stringBuffer.substring(1);
		return answerContent;
	}
	
	
	public static String calculateCodeArr(List<Role> roleList, Integer maxPos){
		
		int[] codeArr =new int[maxPos+1];
		
		for(Role role:roleList){
			
			
			List<Auth> authList = role.getAuths();
			if(authList==null||authList.size()==0){
				continue;
			}
			for(Auth auth:authList){
				
				
				List<Res> resList = auth.getResList();
					if(resList==null||resList.size()==0){
						continue;
					}
				for(Res res:resList){
					Integer resPos = res.getResPos();
					Integer resCode = res.getResCode();
					int oldRescode = codeArr[resPos];
					int newRescode=oldRescode|resCode;
					codeArr[resPos]=newRescode;
				}
			}
		}
		
		StringBuffer codeArrstringBuffer = new StringBuffer();
		for(int i=0;i<codeArr.length;i++){
			codeArrstringBuffer.append(",").append(codeArr[i]);
		}
		String codeArrstring = codeArrstringBuffer.toString().substring(1);
		
		return codeArrstring;
	}
	
	
	public static Boolean checkAuthority(Res res,String codeArr){
		
		
		if(res==null||codeArr==null||codeArr.length()==0){
			return false;
		}
		String[] splitCodeArr = codeArr.split(",");
		Integer resCode = res.getResCode();
		Integer resPos = res.getResPos();
		
		int code = Integer.parseInt(splitCodeArr[resPos]);
		
		return (resCode&code)>0;
	}
	
	public static String createLogTableName(int offset){
		
		 Calendar calendar = Calendar.getInstance();
		 calendar.add(Calendar.MONTH, offset);
		 Date time = calendar.getTime();
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM");
		 String date = dateFormat.format(time);
		 
		return "AUTO_LOG_TABLE_"+date;
	}
}
