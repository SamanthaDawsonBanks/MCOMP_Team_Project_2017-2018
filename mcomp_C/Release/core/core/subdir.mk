################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\CDC.cpp \
G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\HardwareSerial.cpp \
G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\HardwareSerial0.cpp \
G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\HardwareSerial1.cpp \
G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\HardwareSerial2.cpp \
G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\HardwareSerial3.cpp \
G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\IPAddress.cpp \
G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\PluggableUSB.cpp \
G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\Print.cpp \
G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\Stream.cpp \
G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\Tone.cpp \
G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\USBCore.cpp \
G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\WMath.cpp \
G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\WString.cpp \
G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\abi.cpp \
G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\main.cpp \
G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\new.cpp 

S_UPPER_SRCS += \
G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\wiring_pulse.S 

C_SRCS += \
G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\WInterrupts.c \
G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\hooks.c \
G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\wiring.c \
G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\wiring_analog.c \
G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\wiring_digital.c \
G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\wiring_pulse.c \
G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\wiring_shift.c 

C_DEPS += \
.\core\core\WInterrupts.c.d \
.\core\core\hooks.c.d \
.\core\core\wiring.c.d \
.\core\core\wiring_analog.c.d \
.\core\core\wiring_digital.c.d \
.\core\core\wiring_pulse.c.d \
.\core\core\wiring_shift.c.d 

AR_OBJ += \
.\core\core\CDC.cpp.o \
.\core\core\HardwareSerial.cpp.o \
.\core\core\HardwareSerial0.cpp.o \
.\core\core\HardwareSerial1.cpp.o \
.\core\core\HardwareSerial2.cpp.o \
.\core\core\HardwareSerial3.cpp.o \
.\core\core\IPAddress.cpp.o \
.\core\core\PluggableUSB.cpp.o \
.\core\core\Print.cpp.o \
.\core\core\Stream.cpp.o \
.\core\core\Tone.cpp.o \
.\core\core\USBCore.cpp.o \
.\core\core\WInterrupts.c.o \
.\core\core\WMath.cpp.o \
.\core\core\WString.cpp.o \
.\core\core\abi.cpp.o \
.\core\core\hooks.c.o \
.\core\core\main.cpp.o \
.\core\core\new.cpp.o \
.\core\core\wiring.c.o \
.\core\core\wiring_analog.c.o \
.\core\core\wiring_digital.c.o \
.\core\core\wiring_pulse.S.o \
.\core\core\wiring_pulse.c.o \
.\core\core\wiring_shift.c.o 

S_UPPER_DEPS += \
.\core\core\wiring_pulse.S.d 

CPP_DEPS += \
.\core\core\CDC.cpp.d \
.\core\core\HardwareSerial.cpp.d \
.\core\core\HardwareSerial0.cpp.d \
.\core\core\HardwareSerial1.cpp.d \
.\core\core\HardwareSerial2.cpp.d \
.\core\core\HardwareSerial3.cpp.d \
.\core\core\IPAddress.cpp.d \
.\core\core\PluggableUSB.cpp.d \
.\core\core\Print.cpp.d \
.\core\core\Stream.cpp.d \
.\core\core\Tone.cpp.d \
.\core\core\USBCore.cpp.d \
.\core\core\WMath.cpp.d \
.\core\core\WString.cpp.d \
.\core\core\abi.cpp.d \
.\core\core\main.cpp.d \
.\core\core\new.cpp.d 


# Each subdirectory must supply rules for building sources it contributes
core\core\CDC.cpp.o: G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\CDC.cpp
	@echo 'Building file: $<'
	@echo 'Starting C++ compile'
	"G:\PortableApps\eclipse\/arduinoPlugin/packages/arduino/tools/avr-gcc/4.9.2-atmel3.5.4-arduino2/bin/avr-g++" -c -g -Os -Wall -Wextra -std=gnu++11 -fpermissive -fno-exceptions -ffunction-sections -fdata-sections -fno-threadsafe-statics -flto -mmcu=atmega2560 -DF_CPU=16000000L -DARDUINO=10802 -DARDUINO_AVR_MEGA2560 -DARDUINO_ARCH_AVR   -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino" -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\variants\mega" -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -D__IN_ECLIPSE__=1 -x c++ "$<"  -o  "$@"
	@echo 'Finished building: $<'
	@echo ' '

core\core\HardwareSerial.cpp.o: G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\HardwareSerial.cpp
	@echo 'Building file: $<'
	@echo 'Starting C++ compile'
	"G:\PortableApps\eclipse\/arduinoPlugin/packages/arduino/tools/avr-gcc/4.9.2-atmel3.5.4-arduino2/bin/avr-g++" -c -g -Os -Wall -Wextra -std=gnu++11 -fpermissive -fno-exceptions -ffunction-sections -fdata-sections -fno-threadsafe-statics -flto -mmcu=atmega2560 -DF_CPU=16000000L -DARDUINO=10802 -DARDUINO_AVR_MEGA2560 -DARDUINO_ARCH_AVR   -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino" -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\variants\mega" -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -D__IN_ECLIPSE__=1 -x c++ "$<"  -o  "$@"
	@echo 'Finished building: $<'
	@echo ' '

core\core\HardwareSerial0.cpp.o: G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\HardwareSerial0.cpp
	@echo 'Building file: $<'
	@echo 'Starting C++ compile'
	"G:\PortableApps\eclipse\/arduinoPlugin/packages/arduino/tools/avr-gcc/4.9.2-atmel3.5.4-arduino2/bin/avr-g++" -c -g -Os -Wall -Wextra -std=gnu++11 -fpermissive -fno-exceptions -ffunction-sections -fdata-sections -fno-threadsafe-statics -flto -mmcu=atmega2560 -DF_CPU=16000000L -DARDUINO=10802 -DARDUINO_AVR_MEGA2560 -DARDUINO_ARCH_AVR   -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino" -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\variants\mega" -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -D__IN_ECLIPSE__=1 -x c++ "$<"  -o  "$@"
	@echo 'Finished building: $<'
	@echo ' '

core\core\HardwareSerial1.cpp.o: G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\HardwareSerial1.cpp
	@echo 'Building file: $<'
	@echo 'Starting C++ compile'
	"G:\PortableApps\eclipse\/arduinoPlugin/packages/arduino/tools/avr-gcc/4.9.2-atmel3.5.4-arduino2/bin/avr-g++" -c -g -Os -Wall -Wextra -std=gnu++11 -fpermissive -fno-exceptions -ffunction-sections -fdata-sections -fno-threadsafe-statics -flto -mmcu=atmega2560 -DF_CPU=16000000L -DARDUINO=10802 -DARDUINO_AVR_MEGA2560 -DARDUINO_ARCH_AVR   -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino" -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\variants\mega" -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -D__IN_ECLIPSE__=1 -x c++ "$<"  -o  "$@"
	@echo 'Finished building: $<'
	@echo ' '

core\core\HardwareSerial2.cpp.o: G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\HardwareSerial2.cpp
	@echo 'Building file: $<'
	@echo 'Starting C++ compile'
	"G:\PortableApps\eclipse\/arduinoPlugin/packages/arduino/tools/avr-gcc/4.9.2-atmel3.5.4-arduino2/bin/avr-g++" -c -g -Os -Wall -Wextra -std=gnu++11 -fpermissive -fno-exceptions -ffunction-sections -fdata-sections -fno-threadsafe-statics -flto -mmcu=atmega2560 -DF_CPU=16000000L -DARDUINO=10802 -DARDUINO_AVR_MEGA2560 -DARDUINO_ARCH_AVR   -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino" -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\variants\mega" -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -D__IN_ECLIPSE__=1 -x c++ "$<"  -o  "$@"
	@echo 'Finished building: $<'
	@echo ' '

core\core\HardwareSerial3.cpp.o: G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\HardwareSerial3.cpp
	@echo 'Building file: $<'
	@echo 'Starting C++ compile'
	"G:\PortableApps\eclipse\/arduinoPlugin/packages/arduino/tools/avr-gcc/4.9.2-atmel3.5.4-arduino2/bin/avr-g++" -c -g -Os -Wall -Wextra -std=gnu++11 -fpermissive -fno-exceptions -ffunction-sections -fdata-sections -fno-threadsafe-statics -flto -mmcu=atmega2560 -DF_CPU=16000000L -DARDUINO=10802 -DARDUINO_AVR_MEGA2560 -DARDUINO_ARCH_AVR   -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino" -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\variants\mega" -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -D__IN_ECLIPSE__=1 -x c++ "$<"  -o  "$@"
	@echo 'Finished building: $<'
	@echo ' '

core\core\IPAddress.cpp.o: G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\IPAddress.cpp
	@echo 'Building file: $<'
	@echo 'Starting C++ compile'
	"G:\PortableApps\eclipse\/arduinoPlugin/packages/arduino/tools/avr-gcc/4.9.2-atmel3.5.4-arduino2/bin/avr-g++" -c -g -Os -Wall -Wextra -std=gnu++11 -fpermissive -fno-exceptions -ffunction-sections -fdata-sections -fno-threadsafe-statics -flto -mmcu=atmega2560 -DF_CPU=16000000L -DARDUINO=10802 -DARDUINO_AVR_MEGA2560 -DARDUINO_ARCH_AVR   -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino" -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\variants\mega" -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -D__IN_ECLIPSE__=1 -x c++ "$<"  -o  "$@"
	@echo 'Finished building: $<'
	@echo ' '

core\core\PluggableUSB.cpp.o: G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\PluggableUSB.cpp
	@echo 'Building file: $<'
	@echo 'Starting C++ compile'
	"G:\PortableApps\eclipse\/arduinoPlugin/packages/arduino/tools/avr-gcc/4.9.2-atmel3.5.4-arduino2/bin/avr-g++" -c -g -Os -Wall -Wextra -std=gnu++11 -fpermissive -fno-exceptions -ffunction-sections -fdata-sections -fno-threadsafe-statics -flto -mmcu=atmega2560 -DF_CPU=16000000L -DARDUINO=10802 -DARDUINO_AVR_MEGA2560 -DARDUINO_ARCH_AVR   -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino" -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\variants\mega" -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -D__IN_ECLIPSE__=1 -x c++ "$<"  -o  "$@"
	@echo 'Finished building: $<'
	@echo ' '

core\core\Print.cpp.o: G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\Print.cpp
	@echo 'Building file: $<'
	@echo 'Starting C++ compile'
	"G:\PortableApps\eclipse\/arduinoPlugin/packages/arduino/tools/avr-gcc/4.9.2-atmel3.5.4-arduino2/bin/avr-g++" -c -g -Os -Wall -Wextra -std=gnu++11 -fpermissive -fno-exceptions -ffunction-sections -fdata-sections -fno-threadsafe-statics -flto -mmcu=atmega2560 -DF_CPU=16000000L -DARDUINO=10802 -DARDUINO_AVR_MEGA2560 -DARDUINO_ARCH_AVR   -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino" -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\variants\mega" -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -D__IN_ECLIPSE__=1 -x c++ "$<"  -o  "$@"
	@echo 'Finished building: $<'
	@echo ' '

core\core\Stream.cpp.o: G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\Stream.cpp
	@echo 'Building file: $<'
	@echo 'Starting C++ compile'
	"G:\PortableApps\eclipse\/arduinoPlugin/packages/arduino/tools/avr-gcc/4.9.2-atmel3.5.4-arduino2/bin/avr-g++" -c -g -Os -Wall -Wextra -std=gnu++11 -fpermissive -fno-exceptions -ffunction-sections -fdata-sections -fno-threadsafe-statics -flto -mmcu=atmega2560 -DF_CPU=16000000L -DARDUINO=10802 -DARDUINO_AVR_MEGA2560 -DARDUINO_ARCH_AVR   -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino" -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\variants\mega" -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -D__IN_ECLIPSE__=1 -x c++ "$<"  -o  "$@"
	@echo 'Finished building: $<'
	@echo ' '

core\core\Tone.cpp.o: G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\Tone.cpp
	@echo 'Building file: $<'
	@echo 'Starting C++ compile'
	"G:\PortableApps\eclipse\/arduinoPlugin/packages/arduino/tools/avr-gcc/4.9.2-atmel3.5.4-arduino2/bin/avr-g++" -c -g -Os -Wall -Wextra -std=gnu++11 -fpermissive -fno-exceptions -ffunction-sections -fdata-sections -fno-threadsafe-statics -flto -mmcu=atmega2560 -DF_CPU=16000000L -DARDUINO=10802 -DARDUINO_AVR_MEGA2560 -DARDUINO_ARCH_AVR   -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino" -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\variants\mega" -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -D__IN_ECLIPSE__=1 -x c++ "$<"  -o  "$@"
	@echo 'Finished building: $<'
	@echo ' '

core\core\USBCore.cpp.o: G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\USBCore.cpp
	@echo 'Building file: $<'
	@echo 'Starting C++ compile'
	"G:\PortableApps\eclipse\/arduinoPlugin/packages/arduino/tools/avr-gcc/4.9.2-atmel3.5.4-arduino2/bin/avr-g++" -c -g -Os -Wall -Wextra -std=gnu++11 -fpermissive -fno-exceptions -ffunction-sections -fdata-sections -fno-threadsafe-statics -flto -mmcu=atmega2560 -DF_CPU=16000000L -DARDUINO=10802 -DARDUINO_AVR_MEGA2560 -DARDUINO_ARCH_AVR   -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino" -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\variants\mega" -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -D__IN_ECLIPSE__=1 -x c++ "$<"  -o  "$@"
	@echo 'Finished building: $<'
	@echo ' '

core\core\WInterrupts.c.o: G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\WInterrupts.c
	@echo 'Building file: $<'
	@echo 'Starting C compile'
	"G:\PortableApps\eclipse\/arduinoPlugin/packages/arduino/tools/avr-gcc/4.9.2-atmel3.5.4-arduino2/bin/avr-gcc" -c -g -Os -Wall -Wextra -std=gnu11 -ffunction-sections -fdata-sections -flto -fno-fat-lto-objects -mmcu=atmega2560 -DF_CPU=16000000L -DARDUINO=10802 -DARDUINO_AVR_MEGA2560 -DARDUINO_ARCH_AVR   -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino" -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\variants\mega" -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -D__IN_ECLIPSE__=1 "$<"  -o  "$@"
	@echo 'Finished building: $<'
	@echo ' '

core\core\WMath.cpp.o: G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\WMath.cpp
	@echo 'Building file: $<'
	@echo 'Starting C++ compile'
	"G:\PortableApps\eclipse\/arduinoPlugin/packages/arduino/tools/avr-gcc/4.9.2-atmel3.5.4-arduino2/bin/avr-g++" -c -g -Os -Wall -Wextra -std=gnu++11 -fpermissive -fno-exceptions -ffunction-sections -fdata-sections -fno-threadsafe-statics -flto -mmcu=atmega2560 -DF_CPU=16000000L -DARDUINO=10802 -DARDUINO_AVR_MEGA2560 -DARDUINO_ARCH_AVR   -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino" -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\variants\mega" -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -D__IN_ECLIPSE__=1 -x c++ "$<"  -o  "$@"
	@echo 'Finished building: $<'
	@echo ' '

core\core\WString.cpp.o: G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\WString.cpp
	@echo 'Building file: $<'
	@echo 'Starting C++ compile'
	"G:\PortableApps\eclipse\/arduinoPlugin/packages/arduino/tools/avr-gcc/4.9.2-atmel3.5.4-arduino2/bin/avr-g++" -c -g -Os -Wall -Wextra -std=gnu++11 -fpermissive -fno-exceptions -ffunction-sections -fdata-sections -fno-threadsafe-statics -flto -mmcu=atmega2560 -DF_CPU=16000000L -DARDUINO=10802 -DARDUINO_AVR_MEGA2560 -DARDUINO_ARCH_AVR   -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino" -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\variants\mega" -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -D__IN_ECLIPSE__=1 -x c++ "$<"  -o  "$@"
	@echo 'Finished building: $<'
	@echo ' '

core\core\abi.cpp.o: G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\abi.cpp
	@echo 'Building file: $<'
	@echo 'Starting C++ compile'
	"G:\PortableApps\eclipse\/arduinoPlugin/packages/arduino/tools/avr-gcc/4.9.2-atmel3.5.4-arduino2/bin/avr-g++" -c -g -Os -Wall -Wextra -std=gnu++11 -fpermissive -fno-exceptions -ffunction-sections -fdata-sections -fno-threadsafe-statics -flto -mmcu=atmega2560 -DF_CPU=16000000L -DARDUINO=10802 -DARDUINO_AVR_MEGA2560 -DARDUINO_ARCH_AVR   -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino" -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\variants\mega" -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -D__IN_ECLIPSE__=1 -x c++ "$<"  -o  "$@"
	@echo 'Finished building: $<'
	@echo ' '

core\core\hooks.c.o: G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\hooks.c
	@echo 'Building file: $<'
	@echo 'Starting C compile'
	"G:\PortableApps\eclipse\/arduinoPlugin/packages/arduino/tools/avr-gcc/4.9.2-atmel3.5.4-arduino2/bin/avr-gcc" -c -g -Os -Wall -Wextra -std=gnu11 -ffunction-sections -fdata-sections -flto -fno-fat-lto-objects -mmcu=atmega2560 -DF_CPU=16000000L -DARDUINO=10802 -DARDUINO_AVR_MEGA2560 -DARDUINO_ARCH_AVR   -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino" -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\variants\mega" -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -D__IN_ECLIPSE__=1 "$<"  -o  "$@"
	@echo 'Finished building: $<'
	@echo ' '

core\core\main.cpp.o: G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\main.cpp
	@echo 'Building file: $<'
	@echo 'Starting C++ compile'
	"G:\PortableApps\eclipse\/arduinoPlugin/packages/arduino/tools/avr-gcc/4.9.2-atmel3.5.4-arduino2/bin/avr-g++" -c -g -Os -Wall -Wextra -std=gnu++11 -fpermissive -fno-exceptions -ffunction-sections -fdata-sections -fno-threadsafe-statics -flto -mmcu=atmega2560 -DF_CPU=16000000L -DARDUINO=10802 -DARDUINO_AVR_MEGA2560 -DARDUINO_ARCH_AVR   -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino" -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\variants\mega" -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -D__IN_ECLIPSE__=1 -x c++ "$<"  -o  "$@"
	@echo 'Finished building: $<'
	@echo ' '

core\core\new.cpp.o: G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\new.cpp
	@echo 'Building file: $<'
	@echo 'Starting C++ compile'
	"G:\PortableApps\eclipse\/arduinoPlugin/packages/arduino/tools/avr-gcc/4.9.2-atmel3.5.4-arduino2/bin/avr-g++" -c -g -Os -Wall -Wextra -std=gnu++11 -fpermissive -fno-exceptions -ffunction-sections -fdata-sections -fno-threadsafe-statics -flto -mmcu=atmega2560 -DF_CPU=16000000L -DARDUINO=10802 -DARDUINO_AVR_MEGA2560 -DARDUINO_ARCH_AVR   -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino" -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\variants\mega" -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -D__IN_ECLIPSE__=1 -x c++ "$<"  -o  "$@"
	@echo 'Finished building: $<'
	@echo ' '

core\core\wiring.c.o: G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\wiring.c
	@echo 'Building file: $<'
	@echo 'Starting C compile'
	"G:\PortableApps\eclipse\/arduinoPlugin/packages/arduino/tools/avr-gcc/4.9.2-atmel3.5.4-arduino2/bin/avr-gcc" -c -g -Os -Wall -Wextra -std=gnu11 -ffunction-sections -fdata-sections -flto -fno-fat-lto-objects -mmcu=atmega2560 -DF_CPU=16000000L -DARDUINO=10802 -DARDUINO_AVR_MEGA2560 -DARDUINO_ARCH_AVR   -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino" -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\variants\mega" -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -D__IN_ECLIPSE__=1 "$<"  -o  "$@"
	@echo 'Finished building: $<'
	@echo ' '

core\core\wiring_analog.c.o: G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\wiring_analog.c
	@echo 'Building file: $<'
	@echo 'Starting C compile'
	"G:\PortableApps\eclipse\/arduinoPlugin/packages/arduino/tools/avr-gcc/4.9.2-atmel3.5.4-arduino2/bin/avr-gcc" -c -g -Os -Wall -Wextra -std=gnu11 -ffunction-sections -fdata-sections -flto -fno-fat-lto-objects -mmcu=atmega2560 -DF_CPU=16000000L -DARDUINO=10802 -DARDUINO_AVR_MEGA2560 -DARDUINO_ARCH_AVR   -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino" -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\variants\mega" -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -D__IN_ECLIPSE__=1 "$<"  -o  "$@"
	@echo 'Finished building: $<'
	@echo ' '

core\core\wiring_digital.c.o: G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\wiring_digital.c
	@echo 'Building file: $<'
	@echo 'Starting C compile'
	"G:\PortableApps\eclipse\/arduinoPlugin/packages/arduino/tools/avr-gcc/4.9.2-atmel3.5.4-arduino2/bin/avr-gcc" -c -g -Os -Wall -Wextra -std=gnu11 -ffunction-sections -fdata-sections -flto -fno-fat-lto-objects -mmcu=atmega2560 -DF_CPU=16000000L -DARDUINO=10802 -DARDUINO_AVR_MEGA2560 -DARDUINO_ARCH_AVR   -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino" -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\variants\mega" -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -D__IN_ECLIPSE__=1 "$<"  -o  "$@"
	@echo 'Finished building: $<'
	@echo ' '

core\core\wiring_pulse.S.o: G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\wiring_pulse.S
	@echo 'Building file: $<'
	@echo 'Starting S compile'
	"G:\PortableApps\eclipse\/arduinoPlugin/packages/arduino/tools/avr-gcc/4.9.2-atmel3.5.4-arduino2/bin/avr-gcc" -c -g -x assembler-with-cpp -flto -mmcu=atmega2560 -DF_CPU=16000000L -DARDUINO=10802 -DARDUINO_AVR_MEGA2560 -DARDUINO_ARCH_AVR   -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino" -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\variants\mega" -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@"  "$<"  -o  "$@"
	@echo 'Finished building: $<'
	@echo ' '

core\core\wiring_pulse.c.o: G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\wiring_pulse.c
	@echo 'Building file: $<'
	@echo 'Starting C compile'
	"G:\PortableApps\eclipse\/arduinoPlugin/packages/arduino/tools/avr-gcc/4.9.2-atmel3.5.4-arduino2/bin/avr-gcc" -c -g -Os -Wall -Wextra -std=gnu11 -ffunction-sections -fdata-sections -flto -fno-fat-lto-objects -mmcu=atmega2560 -DF_CPU=16000000L -DARDUINO=10802 -DARDUINO_AVR_MEGA2560 -DARDUINO_ARCH_AVR   -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino" -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\variants\mega" -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -D__IN_ECLIPSE__=1 "$<"  -o  "$@"
	@echo 'Finished building: $<'
	@echo ' '

core\core\wiring_shift.c.o: G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino\wiring_shift.c
	@echo 'Building file: $<'
	@echo 'Starting C compile'
	"G:\PortableApps\eclipse\/arduinoPlugin/packages/arduino/tools/avr-gcc/4.9.2-atmel3.5.4-arduino2/bin/avr-gcc" -c -g -Os -Wall -Wextra -std=gnu11 -ffunction-sections -fdata-sections -flto -fno-fat-lto-objects -mmcu=atmega2560 -DF_CPU=16000000L -DARDUINO=10802 -DARDUINO_AVR_MEGA2560 -DARDUINO_ARCH_AVR   -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino" -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\variants\mega" -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -D__IN_ECLIPSE__=1 "$<"  -o  "$@"
	@echo 'Finished building: $<'
	@echo ' '


