#include "Communicate.h"


/**************************Э��**********************************/

char Communicate::mToServerPack[50]={0xA4,0x02,};

		

/////////////////////////
///��¼��ϢID�ţ�������
/////////////////////////
uint16_t Communicate::ToServerGenerateMessageID()
{
	static uint16_t mToServerMessagePackID=0;
	return ++mToServerMessagePackID;
}



/**************************ͨ��***************************************/
////////////////////////////
///����һ���ֽڵ�������
///@param wifi wifi����
///@param ipAddress ������IP��ַ
///@param port �������˿�
///@param data ��Ҫ���͵�һ���ֽڵ�����
///@param �Ƿ��ͳɹ����Է���һ�����յ���ֻ�Ƿ��ͳɹ���
////////////////////////////
bool Communicate::SendBytesToServer(esp8266 & wifi,char* ipAddress,uint32_t port,char* data,uint32_t len)
{
	if(!wifi.createTCPMutipleMode(4,ipAddress,port))//��������ʧ�ܣ��ر�4�����ӣ����½�����ʧ���򷵻�ʧ����Ϣ
	{
		wifi.CloseMulitpleSend(4);
		if(!wifi.createTCPMutipleMode(4,ipAddress,port))
			return false;
	}
	wifi.sendMultipleMode(4,data,len);
	wifi.CloseMulitpleSend(4);
	return false;
}


//////////////////////////////
///����http���󵽷�����
///@param wifi wifi����
///@param ipAddress ������IP��ַ
///@param port �������˿�
///@param data ��������
///@param �Ƿ��ͳɹ����Է���һ�����յ���ֻ�Ƿ��ͳɹ���
/////////////////////////////
bool Communicate::HttpRequest(esp8266 & wifi,char* ipAddress,uint32_t port,char* data)
{
	return false;
}


