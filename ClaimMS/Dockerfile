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

#COPY docker/apache.conf /etc/apache2/sites-enabled/000-default.conf

RUN curl -sS https://getcomposer.org/download/2.0.9/composer.phar -o composer.phar && mv composer.phar /usr/bin/composer && chmod +x /usr/bin/composer

# Install Composer
COPY --from=composer:latest /usr/bin/composer /usr/bin/composer

# Set working directory
WORKDIR /var/www

# Remove the default index.html file Apache2 places in the html directory
RUN rm -rf /var/www/html

# Enable Apache mod_rewrite for URL rewriting
RUN a2enmod rewrite

# Install Symfony CLI
RUN curl -sS https://get.symfony.com/cli/installer | bash
RUN mv /root/.symfony5/bin/symfony /usr/local/bin/symfony

# Copy existing application directory permissions
COPY --chown=www-data:www-data . /var/www

# Copy the Symfony application into the Docker image
COPY . /var/www/html/

# Use the default production configuration
RUN mv "$PHP_INI_DIR/php.ini-production" "$PHP_INI_DIR/php.ini"
RUN symfony server:start -d
# Change current user to www
USER www-data

# Expose port 80
EXPOSE 8079

# Start the server

CMD ["apache2-foreground"]
CMD ["symfony.exe"]

