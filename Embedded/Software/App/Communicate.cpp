#include "Communicate.h"






////////////////////////////
///����һ���ֽڵ�������
///@param wifi wifi����
///@param ipAddress ������IP��ַ
///@param port �������˿�
///@param data ��Ҫ���͵�һ���ֽڵ�����
///@param �Ƿ��ͳɹ����Է���һ�����յ���ֻ�Ƿ��ͳɹ���
////////////////////////////
bool Communicate::SendBytesToServer(esp8266 & wifi,char* ipAddress,uint32_t port,char data)
{
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


