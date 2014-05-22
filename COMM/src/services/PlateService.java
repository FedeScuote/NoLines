package services;

import entity.Plate;
import entity.Type;

public interface PlateService {
	public int obtainRanking(String idPlate);
	public void removePlate(String idPlate);
	public void editPlate(String name, int price, String description, String picture, int time, Type type);
	public Plate searchplate(String key); //esto seguramente este mal, sera un plateVo o como se pasa con Json? String? Hola? Jotason
}
