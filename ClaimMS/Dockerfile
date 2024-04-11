# Use an official PHP runtime as a parent image
FROM php:8.0-apache

# Install system dependencies
RUN apt-get update && apt-get install -y  zlib1g-dev
RUN apt-get update && apt-get install -y  libzip-dev
RUN apt-get update && apt-get install -y  libpq-dev
RUN apt-get update && apt-get install -y  git
RUN apt-get update && apt-get install -y  curl

# Clear cache
RUN apt-get clean && rm -rf /var/lib/apt/lists/*

# Install PHP extensions
RUN docker-php-ext-install pdo_mysql zip exif pcntl pdo_pgsql

RUN wget https://getcomposer.org/download/2.0.9/composer.phar \
    && mv composer.phar /usr/bin/composer && chmod +x /usr/bin/compose

# Install Composer
COPY --from=composer:latest /usr/bin/composer /usr/bin/composer

# Set working directory
WORKDIR /var/www

# Remove the default index.html file Apache2 places in the html directory
RUN rm -rf /var/www/html

# Install Symfony CLI
RUN curl -sS https://get.symfony.com/cli/installer | bash
RUN mv /root/.symfony5/bin/symfony /usr/local/bin/symfony

# Copy existing application directory permissions
COPY --chown=www-data:www-data . /var/www

# Change current user to www
USER www-data

# Expose port 80
EXPOSE 80

# Start the server
CMD ["apache2-foreground"]
