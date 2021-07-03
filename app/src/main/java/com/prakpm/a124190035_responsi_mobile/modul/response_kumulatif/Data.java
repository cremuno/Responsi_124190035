package com.prakpm.a124190035_responsi_mobile.modul.response_kumulatif;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data{

	@SerializedName("metadata")
	private Metadata metadata;

	@SerializedName("content")
	private List<ContentItem> content;

	public void setMetadata(Metadata metadata){
		this.metadata = metadata;
	}

	public Metadata getMetadata(){
		return metadata;
	}

	public void setContent(List<ContentItem> content){
		this.content = content;
	}

	public List<ContentItem> getContent(){
		return content;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"metadata = '" + metadata + '\'' + 
			",content = '" + content + '\'' + 
			"}";
		}
}