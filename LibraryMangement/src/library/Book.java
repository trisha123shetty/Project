package library;

public class Book {

		// TODO Auto-generated method stub
		private int bookId;
		private String title;
		private String Author;
		private boolean isAvailable;
		
//		Book(int bookId,String title,String Author,String isAvailabe ){
//			this.bookId= bookId;
//			this.title=title;
//			this.Author= Author;
//			this.isAvailabe= isAvailabe;
//		}
		
		public void setTitle(String title) {
			this.title=title;
		}
		
		public void setAuthor(String Author) {
			this.Author= Author;
		}
		
		public void setIsAvailable(boolean isAvailable) {
			this.isAvailable= isAvailable;
		}
		
		public String getTitle() {
			return title;
		}
		public String getAuthor() {
			return Author;
		}
		
		public boolean getIsAvailable() {
		return isAvailable;	
		}
	

}
