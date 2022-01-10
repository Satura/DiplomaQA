FROM node:12-alpine
COPY . /node_js
WORKDIR /node_js
RUN npm install
CMD ["npm", "start"]
EXPOSE 9999

#FROM node:lts
#COPY . /node_js
#WORKDIR /node_js
#ENTRYPOINT ["npm", "start"]
#EXPOSE 9999