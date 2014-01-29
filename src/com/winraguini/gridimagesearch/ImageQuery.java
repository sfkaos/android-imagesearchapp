package com.winraguini.gridimagesearch;

import android.net.Uri;

public class ImageQuery {
	
	private String prefixUrl;
	private int startParam;
	private String queryParam;
	private String colorParam;
	private String sizeParam;
	private String typeParam;
	private String siteParam;
	
	public ImageQuery(String prefixUrl){
        this.prefixUrl = prefixUrl;
        this.startParam = 0;
    }
	
	public void setFilter(ImageFilter filter) {
		this.colorParam = filter.getImgColorValue();
		this.sizeParam = filter.getImgSizeValue();
		this.typeParam = filter.getImgTypeValue();
		this.siteParam = filter.getImgSite();
	}
	
	public void setStart(int stringParam) {
		this.startParam = stringParam;
	}
	
	public void setQuery(String queryParam) {
		this.queryParam = queryParam;
	}
	
	public String getUrl() {	
		StringBuffer urlBuffer = new StringBuffer();
		urlBuffer.append(this.prefixUrl);
		urlBuffer.append("&start=" + this.startParam);
		if (this.queryParam != null) {
			urlBuffer.append("&q=" + Uri.encode(this.queryParam));			
		}
		if (this.colorParam != null) {
			urlBuffer.append("&imgcolor=" + this.colorParam);
		}
		if (this.sizeParam != null) {
			urlBuffer.append("&imgsz=" + this.sizeParam);
		}		
		if (this.typeParam != null) {
			urlBuffer.append("&imgtype=" + this.typeParam);
		}
		if (this.siteParam != null) {
			urlBuffer.append("&as_sitesearch=" + this.siteParam);
		}		
		return urlBuffer.toString();
	}
	
}
