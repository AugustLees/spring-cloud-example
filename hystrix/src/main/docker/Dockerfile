


#基础镜像,基于父镜像构建其他docker镜像，父镜像：可以通过docker pull 命令获得，也可以自己制作
#FROM 一定是首个非注释指令 Dockerfile.
#FROM 可以在一个 Dockerfile 中出现多次，以便于创建混合的images
FROM java:8

#Dockerfile镜像制作者的信息
MAINTAINER August,C.F.Lees <723554691@qq.com>

#把宿主目录删除还可以保留volume后的文件
#宿主机文件目录和docker容器文件目录做映射
VOLUME /tmp

#ARG是Docker1.9 版本才新加入的指令。
# ARG 定义的变量只在建立 image 时有效，建立完成后变量就失效消失
#ARG finalName api-gateway

#拷贝本地文件到docker容器里，还可以拷贝URL链接地址下的文件，ADD还具有解压软件包的功能(支持gzip, bzip2 or xz)
ADD hystrix*.jar hystrix.jar

#拷贝本地文件到docker容器
#COPY

#向外开放的端口
#端口映射 EXPOSE <host_port>:<container_port>
#推荐使用docker run -p <host_port>:<container_port> 来固化端口
#EXPOSE

#运行指令，可被docker run的参数覆盖
# RUN命令，非常类似linux下的shell命令 (the command is run in a shell - /bin/sh -c - shell form)
# 在Dockerfile中每执行一条指令（ENV、ADD、RUN等命令），都会生成一个docker image layer
RUN bash -c 'touch /hystrix.jar'

EXPOSE 8761
#容器启动后执行的命令，不可被dockerrun的参数覆盖，如要调试错误的ENTRYPOINT指令，可通过“--entrypoint=  ”实现
#一个Dockerfile中只有最后一条ENTRYPOINT生效，并且每次启动docker容器，都会执行ENTRYPOINT
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/hystrix.jar"]

#容器启动后运行的程序
#CMD

#叠加构建
#ONBUILD 在生成当前Docker镜像的时候不生效，在子镜像生效；ONBUILD在产品发布时起着非常重要的作用！举例
#A镜像中有ONBUILD指令，在构建A镜像时ONBUILD指令不执行；B镜像FROM A,在构建B镜像时ONBUILD指令开始执行；
#ONBUILD

#配置环境变量,一个Dockerfile中可以写多个.以下例子是：设置docker容器的时区为Shanghai
#ENV TZ "Asia/Shanghai"


#定义一个 image 标签 Owner，并赋值，其值为变量 Name 的值。(LABEL Owner=$Name )
#LABEL

#容器退出的信号值
#STOPSIGNAL

#USER 用来切换运行属主身份的。Docker 默认是使用 root，但若不需要，
#建议切换使用者身分，毕竟 root 权限太大了，使用上有安全的风险。
#USER

#WORKDIR 用来切换工作目录的。
#Docker 默认的工作目录是/，只有 RUN 能执行 cd 命令切换目录，
#而且还只作用在当下下的 RUN，也就是说每一个 RUN 都是独立进行的。
#如果想让其他指令在指定的目录下执行，就得靠 WORKDIR。
#WORKDIR 动作的目录改变是持久的，不用每个指令前都使用一次 WORKDIR。
#WORKDIR