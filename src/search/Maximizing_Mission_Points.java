package search;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
class city{
	private int latitude, longitude, height, points;
	public city(int latitude, int longitude, int height, int points) {
		// TODO Auto-generated constructor stub
		this.latitude = latitude;
		this.longitude = longitude;
		this.height = height;
		this.points = points;
	}
	public int getLatitude() {
		return this.latitude;
	}
	public int getLongitude() {
		return this.longitude;
	}
	public int getHeight() {
		return this.height;
	}
	public int getPoint() {
		return this.points;
	}
}


public class Maximizing_Mission_Points {
	
	static boolean canVisite(city before, city after, int d_lat, int d_long) {
		if(before.getHeight() > after.getHeight() 
		   || Math.abs(before.getLatitude() - after.getLatitude()) > d_lat 
		   || Math.abs(before.getLongitude() - after.getLongitude()) > d_long) return false;
		else return true;
	}
	
	
	private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] nD_latD_long = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nD_latD_long[0]);

        int d_lat = Integer.parseInt(nD_latD_long[1]);

        int d_long = Integer.parseInt(nD_latD_long[2]);
        
        int maxP = -2 * 100000;
        int cnt = 0;
        
        int[] pointSum = new int[n];
        
        city[] cities = new city[n];

        for (int nItr = 0; nItr < n; nItr++) {
            String[] latitudeLongitude = scanner.nextLine().split(" ");

            int latitude = Integer.parseInt(latitudeLongitude[0]);

            int longitude = Integer.parseInt(latitudeLongitude[1]);

            int height = Integer.parseInt(latitudeLongitude[2]);

            int points = Integer.parseInt(latitudeLongitude[3]);

            city tmpCity = new city(latitude, longitude, height, points);
            
            for(int i = 0; i < cnt; i++) {
            	if(canVisite(cities[i], tmpCity, d_lat, d_long)) {
            		pointSum[i] += tmpCity.getPoint();
            		cities[i] = tmpCity;
            		if(pointSum[i] > maxP) maxP = pointSum[i];
            	}
            }
            if(tmpCity.getPoint() > 0) {
            	cities[cnt] = tmpCity;
            	pointSum[cnt] = tmpCity.getPoint();
            	cnt++;
            }          
        }
        
        System.out.println(maxP);
        scanner.close();
    }
}
