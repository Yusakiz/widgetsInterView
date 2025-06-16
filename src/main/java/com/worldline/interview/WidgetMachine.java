package com.worldline.interview;

public class WidgetMachine {
    private Engine engine;
    private int batchSize;

    public int produceWidgets(FuelType fuel, int quantity) {
    	
    	//判斷使用燃料，決定實作哪一個引擎
    	if (fuel == FuelType.PETROL || fuel == DIESEL) {
    		engine = new InternalCombustionEngine(fuel);
    		batchSize = 8; // The batch size of an internal combustion engine is 8
        } else if (fuel == FuelType.WOOD || fuel == COAL) {
        	engine = new SteamEngine(fuel);
        	batchSize = 2; // The batch size of a steam engine is 2
        }
    	
    	//添加燃料
    	//不確定原方法內的fuelLevel的意思，所以帶入數量
    	engine.fill(fuel , quantity);
    	
    	//啟動引擎
        engine.start();
        
        int cost = 0;

        if (engine.isRunning()) {
            cost = produce(quantity);
        }

        //關閉
        engine.stop();

        return cost;
    }

    private int produce(int quantity) {
        int batch = 0;
        int batchCount = 0;
        BigDecimal costPerBatch; //因為新材料有小數點，改用BigDecimal

        if (engine.getFuelType() == FuelType.PETROL) {
            costPerBatch = new BigDecimal("9");
        } else if (engine.getFuelType() == FuelType.DIESEL) {
            costPerBatch = new BigDecimal("12");
        } else if (engine.getFuelType() == FuelType.WOOD) {
            costPerBatch = new BigDecimal("4.35");
        } else if (engine.getFuelType() == FuelType.COAL) {
            costPerBatch = new BigDecimal("5.65");
        }

        while (batch < quantity) {
            batch = batch + batchSize;
            batchCount++;
        }

        // 修改BigDecimal的乘法
        return costPerBatch.multiply(BigDecimal.valueOf(batchCount));
    }


}
