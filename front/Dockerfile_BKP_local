# Folosește o imagine oficială Node ca bază
FROM node:18.15.0 AS builder

# Setează directorul de lucru la /front
WORKDIR /front

# Copiază package.json și package-lock.json în directorul de lucru
COPY package*.json ./

# Instalează dependențele aplicației
RUN npm install

# Copiază codul aplicației în container
COPY . .

# Construiește aplicația frontend
RUN npm run build

# Folosește o imagine nginx ușoară pentru a servi aplicația
FROM nginx:alpine

# Copiază aplicația construită din etapa builder în imaginea nginx
COPY --from=builder /front/dist /usr/share/nginx/html

# Expune portul 4200
EXPOSE 4200

# Pornirea nginx
CMD ["nginx", "-g", "daemon off;"]
