package com.winraguini.gridimagesearch;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ImageFilter implements Serializable {
	/**
	 * 
	 */
	public static final String FILTER_KEY = "filter_object";
	
	private static final long serialVersionUID = 1L;
	private String imgSize;
	private String imgColor;
	private String imgType;
	private String imgSite;
	
	public ImageFilter(String imgSize, String imgColor, String imgType, String imgSite) {
		this.imgSize = imgSize;
		this.imgColor = imgColor;
		this.imgType = imgType;
		this.imgSite = imgSite;		
	}
	
	private static final Map<String, String> sizeMap;
    static
    {
    	
//    	imgsz=icon restricts results to small images
//    			imgsz=small|medium|large|xlarge restricts results to medium-sized images
//    			imgsz=xxlarge restricts results to large images
//    			imgsz=huge restricts resykts to extra-large images
    	
//        <item>small</item>
//        <item>medium</item>
//        <item>large</item>
//        <item>extra-large</item>
    	
        sizeMap = new HashMap<String, String>();
        sizeMap.put("small", "small");
        sizeMap.put("medium", "medium");
        sizeMap.put("large", "large");
        sizeMap.put("extra-large", "xlarge");
    }
    
    private static final Map<String, String> colorMap;
    static
    {
    	
//    	imgcolor=black
//    			imgcolor=blue
//    			imgcolor=brown
//    			imgcolor=gray
//    			imgcolor=green
//    			imgcolor=orange
//    			imgcolor=pink
//    			imgcolor=purple
//    			imgcolor=red
//    			imgcolor=teal
//    			imgcolor=white
//    			imgcolor=yellow
    	
//        <item>black</item>
//        <item>blue</item>
//        <item>brown</item>
//        <item>gray</item>
//        <item>green</item>
//        <item>orange</item>
//        <item>pink</item>
//        <item>purple</item>
//        <item>red</item>
//        <item>teal</item>
//        <item>white</item>
//        <item>yellow</item>
    			
    	colorMap = new HashMap<String, String>();
    	colorMap.put("black", "black");
    	colorMap.put("blue", "blue");
    	colorMap.put("brown", "brown");
    	colorMap.put("gray", "gray");
    	colorMap.put("green", "green");
    	colorMap.put("orange", "orange");
    	colorMap.put("pink", "pink");
    	colorMap.put("purple", "purple");
    	colorMap.put("red", "red");
    	colorMap.put("teal", "teal");
    	colorMap.put("white", "white");
    	colorMap.put("yello", "yellow");
    }
    
    private static final Map<String, String> typeMap;
    static
    {
    	
//    	imgtype=face restricts results to images of faces.
//    			imgtype=photo restricts results to photographic images.
//    			imgtype=clipart restricts results to clipart images.
//    			imgtype=lineart restricts results to line drawing images.
    	
//        <item>faces</item>
//        <item>photo</item>
//        <item>clip art</item>
//        <item>line art</item>
    	
    	typeMap = new HashMap<String, String>();
    	typeMap.put("faces", "face");
    	typeMap.put("photo", "photo");
    	typeMap.put("clip art", "clipart");
    	typeMap.put("line art", "lineart");
    }
    
//    as_sitesearch=photobucket.com.
    
	public String getImgSize() {
		return imgSize;
	}
	
	public String getImgColor() {
		return imgColor;
	}
	
	public String getImgType() {
		return imgType;
	}
	
	public String getImgSite() {
		return imgSite;
	}
	
	
	
}
