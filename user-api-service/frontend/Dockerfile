# Use an official nginx server as the base image
FROM nginx:alpine

# Copy the static HTML file
COPY index.html /usr/share/nginx/html

# Copy the nginx configuration file
COPY nginx.conf /etc/nginx/templates/default.conf.template

# Expose port 8080
EXPOSE 8080

# The default nginx entrypoint will now use our template