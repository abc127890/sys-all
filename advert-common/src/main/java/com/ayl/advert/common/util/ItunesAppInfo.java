package com.ayl.advert.common.util;

import java.util.List;

public class ItunesAppInfo {

	private Integer resultCount;

	private List<Info> results;

	public Integer getResultCount() {
		return resultCount;
	}

	public void setResultCount(Integer resultCount) {
		this.resultCount = resultCount;
	}

	public List<Info> getResults() {
		return results;
	}

	public void setResults(List<Info> results) {
		this.results = results;
	}

	public class Info {

		private Long fileSizeBytes;

		private String bundleId;

		private String trackId;

		private String trackName;
		
		private String artworkUrl100;
		
		private String trackViewUrl;
		
		private Double price;

		public Long getFileSizeBytes() {
			return fileSizeBytes;
		}

		public void setFileSizeBytes(Long fileSizeBytes) {
			this.fileSizeBytes = fileSizeBytes;
		}

		public String getBundleId() {
			return bundleId;
		}

		public void setBundleId(String bundleId) {
			this.bundleId = bundleId;
		}

		public String getTrackId() {
			return trackId;
		}

		public void setTrackId(String trackId) {
			this.trackId = trackId;
		}

		public String getTrackName() {
			return trackName;
		}

		public void setTrackName(String trackName) {
			this.trackName = trackName;
		}

		public String getArtworkUrl100() {
			return artworkUrl100;
		}

		public void setArtworkUrl100(String artworkUrl100) {
			this.artworkUrl100 = artworkUrl100;
		}

		public String getTrackViewUrl() {
			return trackViewUrl;
		}

		public void setTrackViewUrl(String trackViewUrl) {
			this.trackViewUrl = trackViewUrl;
		}
		
		
		
		public Double getPrice() {
			return price==null?0:price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		public String getAppName(){
			int index=trackName.indexOf("，");
			if(index>-1){
				return trackName.substring(0,index);
			}
			return trackName;
		}
		
		public String getSizeName(){
			return FileSizeUtil.getFormatSize(this.fileSizeBytes);
		}
		
	}

}
