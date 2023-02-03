public class Snake {
    
    
    private int length;
    private String direction;
    private int[] bodyLocation_X;
    private int[] bodyLocation_Y;


    public Snake(){
        int head_x = 20;
        int head_y = 20;
        bodyLocation_X = new int[] {head_x+1, head_x+2, head_x+3};
        bodyLocation_Y = new int[] {head_y, head_y, head_y};
        direction = "north";
        System.out.print("allo");
    }
    
    public void setDirection(String dir) {
    	direction=dir;
    }

    public int head_X(){
        return bodyLocation_X[0]*25;
    }

    public int head_Y(){
        return bodyLocation_Y[0]*25;
    }
    public String direction(){
        return direction;
    }
  
    public void updateX(int x){
    	bodyLocation_X[0]=bodyLocation_X[0]+x;
    }
    public void updateY(int y){
    	bodyLocation_Y[0]=bodyLocation_Y[0]+y;
    }
    public int bodyLength() {
    	return bodyLocation_X.length;
    }
    public int[] bodyCoordinates_X() {
    	return bodyLocation_X;
    }
    public void set_body_X(int[] arr) {
    	bodyLocation_X = arr;
    }
    public void set_body_Y(int[] arr) {
    	bodyLocation_Y = arr;
    }
    public int[] bodyCoordinates_Y() {
    	return bodyLocation_Y;
    }
    public void addTail() {
    	int[] old_arrX = bodyLocation_X;
    	int[] old_arrY = bodyLocation_Y;
    	bodyLocation_X = new int[bodyLocation_X.length+1];
    	bodyLocation_Y = new int[bodyLocation_Y.length+1];
    	
    	for(int i =0; i<old_arrX.length; i++) {
    		bodyLocation_X[i] = old_arrX[i];
    		bodyLocation_Y[i] = old_arrY[i];
    	}
    	bodyLocation_X[bodyLocation_X.length-1] = old_arrX[old_arrX.length-1];
    	bodyLocation_Y[bodyLocation_Y.length-1] = old_arrY[old_arrY.length-1];
    }
}
