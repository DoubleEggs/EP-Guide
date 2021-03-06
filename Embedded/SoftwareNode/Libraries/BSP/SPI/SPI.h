/**
  *@file SPI.h
  *@frief SPI driver lib for stm32f10x
  *@author Neucrack(Neutree)
  *@version v0.1
  *@date create on 2016-01-06
  *      last update on 2016-01-06
  *@copyright CQUT IOT LIB all right reserved
  *@bug only for master mode now
  */

#ifndef __SPI_H_
#define __SPI_H_

#include "stm32f10x.h"
#include "TaskManager.h"



class SPI_simulate{

public:	
	///////////////////////////
	///Constructor for SPI 
	///@param SPI choose which SPI to use SPI1 or SPI2
	///@param if remap pin
	///@param speed speed of SPI default:SPI_SPEED_256(281.25kHz) @see SPI_Speed 
	///@param firstBit the first bit of transfer LSB or MSB @see SPI_FirstBit  default:SPI_FirstBit_MSB_
	//////////////////////////
	SPI_simulate(SPI_TypeDef* spi,bool remap=false);



	///////////////////////////////
	///read a byte from slave or write a byte to slave 
	///@param dataToSend if write: the data will send to slave
	///                  if read : the slave register address to read
	///@dataReturn allow empty   if write: the same data with send data
	///                          if read : the slave register's data
	///@retval if read or write succeed
	///////////////////////////////
	bool ReadOrWriteByte(u8 dataTosend,u8 *dataReturn=0);
	
	

	//////////////////////////
	///使能SPI（使能NSS）
	//////////////////////////
	void EnableSPI(void);

	//////////////////////////
	///失能SPI（使能NSS）
	//////////////////////////
	void DisableSPI(void);
	
private:
	
	//
	SPI_TypeDef* mSPI;
	GPIO_TypeDef* mNSS_GPIO;
	uint16_t      mNSS_Pin;


	///////////////////////////
	///Constructor for SPI 
	///@param SPI choose which SPI to use SPI1(default) or SPI2
	///@param if remap pin
	///@param speed speed of SPI @see SPI_Speed default:SPI_SPEED_16
	///@param firstBit the first bit of transfer LSB or MSB @see SPI_FirstBit  default:SPI_FirstBit_MSB_
	//////////////////////////
	void Init(SPI_TypeDef* spi,bool remap=false,SPI_Speed speed=SPI_SPEED_16,SPI_FirstBit firstBit=SPI_FirstBit_MSB_);
	

};


#endif


