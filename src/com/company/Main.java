package com.company;

import de.re.easymodbus.modbusclient.ModbusClient;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

    public static void main(String[] args) {
        ModbusClient modbusClient = new ModbusClient("91.207.249.64",502);
        try
        {
            DatabaseControll databaseControll = new DatabaseControll();
            modbusClient.Connect();

            int temperature = 0;
            int temperatureChange = 0;

            while (true)
            {
                temperature = modbusClient.ReadHoldingRegisters(0,1)[0];
                Thread.sleep(60000);
                temperatureChange = modbusClient.ReadHoldingRegisters(0,1)[0];

                modbusClient.Disconnect();

                if (Math.abs(temperature - temperatureChange) >= 1)
                {
                    databaseControll.WriteToSchema(temperature);
                    System.out.println("Temperature was change on " + Math.abs(temperature - temperatureChange));
                }

                modbusClient.Connect();

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
