#ifndef __MATH_TOOL_H
#define __MATH_TOOL_H
class MathToll
{
public:
	/*
	////////////////////////////
	///����CRC16У����(������BUG)
	///@param data ��Ҫ��������CRCУ�����ԭʼ����
	///@param len ���ݳ��ȣ��ֽڣ�
	////////////////////////////
	static unsigned short CRC16(unsigned char *data,int len)
	{
		unsigned short crc = 0xffff;
		int i_byte = 0,i_bit = 0;
		unsigned char t = 0;
		for (i_byte = 0; i_byte < len; i_byte++)
		{
			crc = crc ^ data[i_byte];
			for(i_bit = 0; i_bit < 8; i_bit++)
			{	
				t = crc & 0x0001;
				crc >>= 1;
				crc &=  0x7fff;
				if(t==1)
					crc = crc ^ 0xa001;
			}
		}
		return crc;
	}
	*/
	
	/////////////////////////////
	///��У��
	///@param data ��Ҫ���������У�����ԭʼ����
	///@param len ���ݳ��ȣ��ֽڣ�
	/////////////////////////////
	static unsigned char CheckSum8(unsigned char *data,int len)
	{
		unsigned int sum=0;
		for(int i=0;i<len;++i)
			sum+=data[i];
		return sum&0xff;
	}
	
	
};
#endif
