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
		this.colorParam = filter.getImgColor();
		this.sizeParam = filter.getImgSize();
		this.typeParam = filter.getImgType();
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
			
		}
		if (this.sizeParam != null) {
			
		}		
		if (this.typeParam != null) {
			
		}
		if (this.siteParam != null) {
			
		}		
		return urlBuffer.toString();
	}
	
}
