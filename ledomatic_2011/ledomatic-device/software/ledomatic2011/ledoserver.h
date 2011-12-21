#ifndef led_h
#define led_h

#if defined(ARDUINO) && ARDUINO >= 100
#include "Arduino.h"

#else
#include "WProgram.h"
#endif

#include <SPI.h>
#include <Ethernet.h>


class LedoServer
{
  private:
    int _channel;

  public:
    LedoServer(int chanel);
};

#endif
