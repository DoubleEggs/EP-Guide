# ifndef __APP_H
# define __APP_H

# include "USART.h"
# include "MFRC522.h"
# include "GPIO.h"
# include "LED.h"
# include "esp8266.h"
# include "Buzzer.h"

# include "WIFI.h"
# include "Communicate.h"

#include "MathTool.h"
#include "MD5.h"

#include "string.h"

#define APP_BUFFER_SIZE 50
#define APP_MAX_PARKING_SPACE_SIZE 50      //最多同时50个车位
#define APP_MAX_IP_ADDRESS_BUFFER_SIZE 200 //最多50个车位，每个4个字节的IP地址


typedef enum
{
	NodeStatus_OFF_Line = 0x80, //掉线
	NodeStatus_On_line  = 0x40, //在线
	NodeStatus_Busy     = 0x20, //有车
	NodeStatus_Free     = 0x10, //空闲
	NodeStatus_Disable  = 0x08, //不使用该车位
	
	NodeStatus_ToBusy   = 0x04, //状态改变,从空闲到有车
	NodeStatus_ToFree   = 0x02  //从有车到空闲
}NodeStatus;




class APP
{

	
public:


///////////////////////////
///构造函数，执行片上资源初始化、片外资源初始化（包括片上到片外的装配）
///////////////////////////
	APP();



/**********************************与主控交互**************************************/

//////////////////////////
///超时等待信息到来，收到的信息将有效信息存到mBuffer开头
/////////////////////////
bool WaitReceiveAndDecode(unsigned char timeOut=2);

/////////////////////////
///响应主控的状态请求
/////////////////////////
void AckStatus();


////////////////////////
///响应主控的引导道路的请求
////////////////////////
void AckLead();



////////////////////////
///响应主控的完成引导道路的请求
////////////////////////
void AckCompleteLead();

////////////////////////
///响应主控的禁用节点的请求
////////////////////////
void AckDisableNode();

/***********************************************************************************/





/*************************RFID*******************************************************/
void FindCar();
/***********************************************************************************/







private:
/**************************硬件资源*******************************/
	/////////////////////////////////
	///片上资源分配（初始化在构造函数中）
	//////////////////////////////////
	//GPIO
	GPIO mLedRedGPIO;
	GPIO mLedGreenGPIO;
	//串口1
	USART mCOM1;
	//串口2
	USART mCOM2;


	////////////////////////////////////
	///外设资源分配（在构造中装配（片上资源和外设的联系））
	///////////////////////////////////
	//LED
	LED mLedRed;
	LED mLedGreen;
	//RFID
	MFRC522 mRFID;
	//WIFI
	esp8266 mWIFI;
/*****************************************************************/




/**************************私有常量***********************************/
static const unsigned char mPICCDefaultKey[6];

/*********************************************************************/






/*************************私有变量*************************************/
unsigned char mTagInfo[MFRC522_MaxReceiveLen];
int8_t mToServerConnectionHealth; //标志与服务器的连接情况，由链路请求（心跳）控制 1:健康 -1：失去连接 0：正在检测
int8_t mToServerSignUpStatus; //是否注册到系统中 1:已经注册 -1：未注册 0：正在注册 2：节点被禁用，不再发送注册消息

unsigned char mBuffer[APP_BUFFER_SIZE];
//unsigned char mBufferReceive[APP_BUFFER_SIZE];//用来接收数据的缓冲区

//wifi客户端信息（IP地址保存）
unsigned char mIPBuffer[APP_MAX_PARKING_SPACE_SIZE][4];
unsigned char mMacBuffer[APP_MAX_PARKING_SPACE_SIZE][6];

NodeStatus mStatusNow;
unsigned char mCarIDNow[4];

bool mIsExistCar,mIsExistCarBefor;//标记是否检测到车辆停泊
bool mIsBusyToFree,mIsFreeToBusy;//标记是否进行了状态更改
bool mIsreserved;                //是否被预约了
bool mIsleadNow;//标志是否正在进行引导动作
bool mIsleadDestination;         //标识引导是否是终点

/*******************************************************************/






/***************************系统函数，固定,由系统调用********************/
public:
/////////////////////////////
///硬件初始化
/////////////////////////////
	void InitHardware();

///////////////////////////////
///软件需要的初始化
//////////////////////////////
	void InitSoft();



/////////////////////////////////
///循环执行部分
/////////////////////////////////
	void Loop();


/*******************************其它****************************/


};

extern APP app;

#endif
