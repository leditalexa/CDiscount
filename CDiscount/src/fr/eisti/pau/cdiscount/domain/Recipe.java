package fr.eisti.pau.cdiscount.domain;

import java.util.Set;

public class Recipe {	
	private String author;
	private int cost;
	private int difficulty;
	private String guid;
	private int id;
	private Boolean isTested;
	private Boolean isVegetarian;
	private int rating;
	private int ratingCount;
	private String title;
	private String keyword;
	private Set<RecipePicture> pictures;

	
	public Recipe() {
		super();
	}


	public Recipe(String author, int cost, int difficulty, String guid, int id,
			Boolean isTested, Boolean isVegetarian, int rating,
			int ratingCount, String title, String keyword, Set<RecipePicture> pictures) {
		super();
		this.author = author;
		this.cost = cost;
		this.difficulty = difficulty;
		this.guid = guid;
		this.id = id;
		this.isTested = isTested;
		this.isVegetarian = isVegetarian;
		this.rating = rating;
		this.ratingCount = ratingCount;
		this.title = title;
		this.keyword = keyword;
		this.pictures = pictures;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Boolean getIsTested() {
		return isTested;
	}
	public void setIsTested(Boolean isTested) {
		this.isTested = isTested;
	}
	public Boolean getIsVegetarian() {
		return isVegetarian;
	}
	public void setIsVegetarian(Boolean isVegetarian) {
		this.isVegetarian = isVegetarian;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getRatingCount() {
		return ratingCount;
	}
	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Set<RecipePicture> getPictures() {
		return pictures;
	}
	public void setPictures(Set<RecipePicture> pictures) {
		this.pictures = pictures;
	}
	
	
	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	@Override
	public String toString() {
		return "Recipe [author=" + author + ", cost=" + cost + ", difficulty="
				+ difficulty + ", guid=" + guid + ", id=" + id + ", isTested="
				+ isTested + ", isVegetarian=" + isVegetarian + ", rating="
				+ rating + ", ratingCount=" + ratingCount + ", title=" + title
				+ ", pictures=" + pictures + "]";
	}
}
