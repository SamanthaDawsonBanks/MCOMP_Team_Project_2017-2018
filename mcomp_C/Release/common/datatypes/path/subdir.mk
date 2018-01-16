################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
..\common\datatypes\path\Path.cpp \
..\common\datatypes\path\PathItem.cpp 

LINK_OBJ += \
.\common\datatypes\path\Path.cpp.o \
.\common\datatypes\path\PathItem.cpp.o 

CPP_DEPS += \
.\common\datatypes\path\Path.cpp.d \
.\common\datatypes\path\PathItem.cpp.d 


# Each subdirectory must supply rules for building sources it contributes
common\datatypes\path\Path.cpp.o: ..\common\datatypes\path\Path.cpp
	@echo 'Building file: $<'
	@echo 'Starting C++ compile'
	"G:\PortableApps\eclipse\/arduinoPlugin/packages/arduino/tools/avr-gcc/4.9.2-atmel3.5.4-arduino2/bin/avr-g++" -c -g -Os -Wall -Wextra -std=gnu++11 -fpermissive -fno-exceptions -ffunction-sections -fdata-sections -fno-threadsafe-statics -flto -mmcu=atmega2560 -DF_CPU=16000000L -DARDUINO=10802 -DARDUINO_AVR_MEGA2560 -DARDUINO_ARCH_AVR   -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino" -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\variants\mega" -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -D__IN_ECLIPSE__=1 -x c++ "$<"  -o  "$@"
	@echo 'Finished building: $<'
	@echo ' '

common\datatypes\path\PathItem.cpp.o: ..\common\datatypes\path\PathItem.cpp
	@echo 'Building file: $<'
	@echo 'Starting C++ compile'
	"G:\PortableApps\eclipse\/arduinoPlugin/packages/arduino/tools/avr-gcc/4.9.2-atmel3.5.4-arduino2/bin/avr-g++" -c -g -Os -Wall -Wextra -std=gnu++11 -fpermissive -fno-exceptions -ffunction-sections -fdata-sections -fno-threadsafe-statics -flto -mmcu=atmega2560 -DF_CPU=16000000L -DARDUINO=10802 -DARDUINO_AVR_MEGA2560 -DARDUINO_ARCH_AVR   -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\cores\arduino" -I"G:\PortableApps\eclipse\arduinoPlugin\packages\arduino\hardware\avr\1.6.20\variants\mega" -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -D__IN_ECLIPSE__=1 -x c++ "$<"  -o  "$@"
	@echo 'Finished building: $<'
	@echo ' '


